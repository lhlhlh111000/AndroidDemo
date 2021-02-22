package com.pig.android.demo

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnSystemUiVisibilityChangeListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pig.android.demo.case.*
import com.pig.android.demo.case.camera.CameraFragment
import com.pig.android.demo.case.clazzloader.ClazzLoadFragment
import com.pig.android.demo.case.conditionvariable.ConditionVariableFragment
import com.pig.android.demo.case.delegation.DelegateTestFragment
import com.pig.android.demo.case.image.build.ImageBuildFragment
import com.pig.android.demo.case.plugin.PluginFragment
import com.pig.android.demo.case.preference.SettingFragment
import com.pig.android.demo.case.proxy.ProxyFragment
import com.pig.android.demo.extends.go
import com.pig.android.demo.extends.goFragment
import com.pig.android.demo.model.Item
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : Activity() {

    companion object {
        private val CAMERA_PERMISSION = arrayOf(Manifest.permission.CAMERA)
    }

    private lateinit var items: ArrayList<Item>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView
            .systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        window.decorView
            .setOnSystemUiVisibilityChangeListener(OnSystemUiVisibilityChangeListener {
                var uiOptions =
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or  //布局位于状态栏下方
                            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or  //全屏
                            View.SYSTEM_UI_FLAG_FULLSCREEN or  //隐藏导航栏
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                uiOptions = if (Build.VERSION.SDK_INT >= 19) {
                    uiOptions or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                } else {
                    uiOptions or View.SYSTEM_UI_FLAG_LOW_PROFILE
                }
                window.decorView.systemUiVisibility = uiOptions
            })
        setContentView(R.layout.activity_main)
        initItems()

        rcv_main.layoutManager = LinearLayoutManager(this)
        rcv_main.adapter = MainAdapter()
    }

    private fun initItems() {
        items = ArrayList<Item>()

        items.add(Item("Plugin") {
            goFragment<PluginFragment>()
        })

        items.add(Item("Notification") {
            go<NotificationActivity>()
        })

        items.add(Item("Android11") {
            go<Android11Activity>()
        })

        items.add(Item("AutoCompleteTextView_Bug") {
            go<AutoCompleteInputActivity>()
        })

        items.add(Item("AutoCompleteTextView_Bug_2") {
            val dialog = AutoCompleteInputDialog(this)
            dialog.show();
        })
        items.add(Item("ContainerActivity") {
            val bundle = Bundle()
            bundle.putString(TestContainerFragment.TEXT, "Text from bundle")
            goFragment<TestContainerFragment>(bundle)
        })
        items.add(Item("Coordinatorlayout") {
            goFragment<CoordinatorlayoutFragment>()
        })
        items.add(Item("Proxy") {
            goFragment<ProxyFragment>()
        })
        items.add(Item("MetaValue") {
            goFragment<MetaFragment>()
        })
        items.add(Item("Setting") {
            goFragment<SettingFragment>()
        })
        items.add(Item("InstallCheck") {
            goFragment<InstallAppFragment>()
        })
        items.add(Item("Channel") {
            goFragment<ChannelFragment>()
        })
        items.add(Item("Lifecycle") {
            goFragment<LifecycleFragment>()
        })
        items.add(Item("ConditionVariableFragment") {
            goFragment<ConditionVariableFragment>()
        })
        items.add(Item("Image Build") {2
            goFragment<ImageBuildFragment>()
        })
        items.add(Item("Lint Check") {
            goFragment<LintCheckFragment>()
        })
        items.add(Item("Type") {
            goFragment<TypeFragment>()
        })
        items.add(Item("Native") {
            goFragment<NativeFragment>()
        })
        items.add(Item("Camera") {
            if(cameraPermissionGranted()) {
                goFragment<CameraFragment>()
            }else {
                ActivityCompat.requestPermissions(this,
                    CAMERA_PERMISSION, 1000)
            }
        })
        items.add(Item("FullScreen") {
            val build = AlertDialog.Builder(this)
                .setTitle("标题")
                .setMessage("这是内容")
                .show()
        })

        items.add(Item("Clazzload") {
            goFragment<ClazzLoadFragment>()
        })

        items.add(Item("Delegation") {
            goFragment<DelegateTestFragment>()
        })
    }

    private fun cameraPermissionGranted() = CAMERA_PERMISSION.all {
        ContextCompat.checkSelfPermission(
            this, it) == PackageManager.PERMISSION_GRANTED
    }

    inner class MainAdapter: RecyclerView.Adapter<MainViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val itemView = LayoutInflater.from(this@MainActivity)
                .inflate(R.layout.item_main, parent, false)
            return MainViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            holder.setText(items[position].name)
            items[position].action?.let {
                holder.doAction(it)
            }
        }
    }

    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setText(text: String) {
            itemView.findViewById<TextView>(R.id.tv_main_item).text = text
        }

        fun doAction(action: () -> Unit) {
            itemView.findViewById<TextView>(R.id.tv_main_item).setOnClickListener {
                action.invoke()
            }
        }
    }
}