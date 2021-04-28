package com.pig.android.plugin.PigDemo.library.trans

import com.android.SdkConstants
import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import javassist.ClassPool
import javassist.CtClass
import javassist.NotFoundException
import org.apache.commons.io.FileUtils
import org.gradle.api.Project

import java.util.regex.Matcher

class PluginRenameTransform extends Transform {

    private Project mProject

    private List<File> replaceClassesPath = new ArrayList<>()

    PluginRenameTransform(Project project) {
        this.mProject = project
    }

    @Override
    String getName() {
        return "RenameClass"
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.PROJECT_ONLY
    }

    @Override
    boolean isIncremental() {
        return false
    }

    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation)

        ClassPool classPool = ClassPool.getDefault()
        mProject.android.bootClasspath.each {
            classPool.appendClassPath((String) it.absolutePath)
        }

        Map<String, String> replaceClasses = initReplaceCollection(transformInvocation)

        List<String> renameClasses = new ArrayList<>()
        List<String> originClasses = new ArrayList<>()
        List<File> deleteFiles = new ArrayList<>()
        transformInvocation.inputs.each {
            it.directoryInputs.each {DirectoryInput input ->
                String path = input.file.absolutePath
                classPool.insertClassPath(path)

                FileUtils.listFiles(input.file, null, true).each {File file ->
                    if (file.absolutePath.endsWith(SdkConstants.DOT_CLASS)) {
                        def className = file.absolutePath.substring(path.length() + 1, file.absolutePath.length() - SdkConstants.DOT_CLASS.length()).replaceAll(Matcher.quoteReplacement(File.separator), '.')
                        try {
                            CtClass ctClass = classPool.get(className)
                            String name = ctClass.getName()
                            println("class name:  $className")
                            if(!renameClasses.contains(name) && !originClasses.contains(name) && !name.contains("BuildConfig")) {
                                String rename = name + "Plugin"
                                ctClass.setName(rename)
                                replaceClasses.entrySet().iterator().each {
                                    if(it.getKey() != className) {
                                        ctClass.replaceClassName(it.getKey(), it.getValue())
                                    }
                                }
                                ctClass.writeFile(path)
                                ctClass.detach()
                                originClasses.add(name)
                                renameClasses.add(rename)
                                deleteFiles.add(file)
                            }
                        }catch (NotFoundException e) {
                            println("class not found exception class name:  $className")
                        }
                    }
                }
            }

            if(null != transformInvocation.outputProvider) {
                deleteOriginClasses(deleteFiles)
                it.directoryInputs.each {DirectoryInput directoryInput ->
                    File dest = transformInvocation.outputProvider.getContentLocation(
                            directoryInput.name, directoryInput.contentTypes,
                            directoryInput.scopes, Format.DIRECTORY)
                    FileUtils.copyDirectory(directoryInput.file, dest)
                }

                it.jarInputs.each {JarInput jarInput ->
                    String jarName = jarInput.name
                    String md5Name = System.currentTimeMillis().toString()
                    if (jarName.endsWith(".jar")) {
                        jarName = jarName.substring(0, jarName.length() - 4)
                    }
                    def dest = transformInvocation.outputProvider.getContentLocation(jarName + md5Name, jarInput.contentTypes, jarInput.scopes, Format.JAR)
                    FileUtils.copyFile(jarInput.file, dest)
                }
            }
        }
    }

    Map<String, String> initReplaceCollection(TransformInvocation transformInvocation) {
        Map<String, String> replaceClasses = new HashMap<>()
        replaceClassesPath.clear()

        transformInvocation.inputs.each {
            it.directoryInputs.each { DirectoryInput input ->
                String path = input.file.absolutePath
                FileUtils.listFiles(input.file, null, true).each { File file ->
                    if (file.absolutePath.endsWith(SdkConstants.DOT_CLASS)) {
                        def className = file.absolutePath.substring(path.length() + 1, file.absolutePath.length() - SdkConstants.DOT_CLASS.length()).replaceAll(Matcher.quoteReplacement(File.separator), '.')
                        replaceClasses.put(className, className + "Plugin")
                        replaceClassesPath.add(file)
                    }
                }
            }
        }

        return replaceClasses
    }

    void deleteOriginClasses(List<File> files) {
        files.each {
            it.delete()
        }
    }
}