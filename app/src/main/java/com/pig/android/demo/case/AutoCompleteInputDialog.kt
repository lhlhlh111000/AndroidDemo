package com.pig.android.demo.case

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.InputType
import android.view.inputmethod.EditorInfo
import com.pig.android.demo.R
import kotlinx.android.synthetic.main.activity_auto_complete_input.*

/**
 * Title:
 * Description:
 * Copyright © 2001-2020 17173. All rights reserved.
 *
 * @author cqt
 * @version 2020/8/27
 */
class AutoCompleteInputDialog(context: Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_complete_input)

        et_input.imeOptions = EditorInfo.IME_ACTION_NEXT or EditorInfo.IME_FLAG_NO_FULLSCREEN
        et_input.inputType = InputType.TYPE_CLASS_TEXT
        et_password.imeOptions = EditorInfo.IME_ACTION_DONE or EditorInfo.IME_FLAG_NO_FULLSCREEN

        et_input.setDropDownBackgroundDrawable(ColorDrawable(context.resources.getColor(android.R.color.white)))

        et_input.setOnFocusChangeListener { _, b ->
            if(b) {
                et_input.showDropDown()
            }
        }
        et_input.showDropDown()
    }
}