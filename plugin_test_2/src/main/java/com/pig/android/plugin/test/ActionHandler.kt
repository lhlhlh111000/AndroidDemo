package com.pig.android.plugin.test

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.view.Window
import androidx.appcompat.app.AlertDialog

/**
 * Title:
 * Description:
 * Copyright © 2001-2021 17173. All rights reserved.
 *
 * @author cqt
 * @version 2021/2/1
 */
class ActionHandler {

    companion object {

        fun process(activity: Activity) {

            val listener = DialogInterface.OnClickListener { dialog, _ ->
                dialog.dismiss()

                val aDialog = Dialog(activity)
                aDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                aDialog.setContentView(R.layout.dialog_plugin)
                aDialog.show()
            }

            AlertDialog.Builder(activity)
                .setTitle("这是标题啊1")
                .setMessage("这是提示内容啊！")
                .setPositiveButton("确定", listener)
                .show()
        }
    }
}