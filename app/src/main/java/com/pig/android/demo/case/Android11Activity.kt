package com.pig.android.demo.case

import android.Manifest
import android.annotation.TargetApi
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.telephony.TelephonyManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.pig.android.demo.R
import kotlinx.android.synthetic.main.activity_android11.*

/**
 * Title:
 * Description:
 * Copyright © 2001-2020 17173. All rights reserved.
 *
 * @author cqt
 * @version 2020/8/18
 */
class Android11Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android11)

        btn_phone_number.setOnClickListener {
            getPhoneNumber()
        }
    }

    @TargetApi(Build.VERSION_CODES.R)
    private fun getPhoneNumber() {
        if(PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this,
            Manifest.permission.READ_PHONE_NUMBERS)) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.READ_PHONE_NUMBERS), 1000)
            return
        }

        val tm = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val phoneNumber1 = tm.line1Number
        tv_info.text = "Phone number is: $phoneNumber1 \n"
    }
}