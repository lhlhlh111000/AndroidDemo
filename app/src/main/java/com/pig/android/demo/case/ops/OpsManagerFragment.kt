package com.pig.android.demo.case.ops

import android.app.AppOpsManager
import android.app.AsyncNotedAppOp
import android.app.SyncNotedAppOp
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.pig.android.demo.R
import kotlinx.android.synthetic.main.fragment_button.*
import java.util.concurrent.Executors

class OpsManagerFragment : Fragment() {

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initOps()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_button, container, false)

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_click.setOnClickListener {
            val locationManager = activity?.getSystemService(LocationManager::class.java) as LocationManager
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun initOps() {
        var attributionContext = activity?.createAttributionContext("OpsManager");

        val appOpsCallback = object : AppOpsManager.OnOpNotedCallback() {

            override fun onNoted(op: SyncNotedAppOp) {
                logPrivateDataAccess(op.op, Throwable().stackTrace.toString())
            }

            override fun onSelfNoted(op: SyncNotedAppOp) {
                logPrivateDataAccess(op.op, Throwable().stackTrace.toString())
            }

            override fun onAsyncNoted(asyncOp: AsyncNotedAppOp) {
                logPrivateDataAccess(asyncOp.op, asyncOp.message)
            }

            private fun logPrivateDataAccess(opCode: String, trace: String) {
                Log.e(OpsManagerFragment::class.java.simpleName, "Private data accessed. " +
                        "Operation: $opCode\nStack Trace:\n$trace")
            }
        }

        val appOpsManager = activity?.getSystemService(AppOpsManager::class.java) as AppOpsManager
        val mainExecutor = Executors.newSingleThreadExecutor()
        appOpsManager.setOnOpNotedCallback(mainExecutor, appOpsCallback)
    }
}