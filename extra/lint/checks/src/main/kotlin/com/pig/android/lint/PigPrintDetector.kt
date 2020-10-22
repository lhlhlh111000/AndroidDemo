package com.pig.android.lint

import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import com.intellij.psi.impl.source.PsiClassReferenceType
import org.jetbrains.uast.UCallExpression
import org.jetbrains.uast.UElement

/**
 * Title:
 * Description:
 * Copyright © 2001-2020 17173. All rights reserved.
 *
 * @author cqt
 * @version 2020/10/22
 */
class PigPrintDetector : Detector(), Detector.UastScanner {

    companion object {
        val ISSUE : Issue = Issue.create(
            "pigPrint",
            "禁止调用printStackTrace()",
            "禁止调用java.lang.Throwable.printStackTrace()方法。",
            Category.USABILITY,
            6,
            Severity.ERROR,
            Implementation(PigPrintDetector::class.java, Scope.JAVA_FILE_SCOPE)
        )
    }

    override fun getApplicableUastTypes(): List<Class<out UElement>>? {
        return listOf(UCallExpression::class.java)
    }

    override fun createUastHandler(context: JavaContext): UElementHandler? {
        return object : UElementHandler() {

            override fun visitCallExpression(node: UCallExpression) {
                if(node.methodName == "printStackTrace") {
                    val receiverType = node.receiverType
                    if (receiverType is PsiClassReferenceType) {
                        val clazz = receiverType.resolve()
                        if (clazz != null)
                            if (context.evaluator.inheritsFrom(
                                    clazz,
                                    "java.lang.Throwable",
                                    true
                                )) {
                                context.report(
                                    ISSUE,
                                    node,
                                    context.getLocation(node),
                                    "本项目禁止使用该方法"
                                )
                            }
                    }
                }
            }
        }
    }
}