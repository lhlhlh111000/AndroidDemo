package com.pig.android.res

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import kotlinx.android.synthetic.main.dialog_test.*

/**
 * Title:
 * Description:
 * Copyright © 2001-2020 17173. All rights reserved.
 *
 * @author cqt
 * @version 2020/12/31
 */
public class ADialog(context: Context) : Dialog(context) {

    companion object {

        public fun newInstance(context: Context) : ADialog {
            return ADialog(context)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_test)

        tv_dialog_hint.text = context.getString(R.string.hint_text)
    }
}