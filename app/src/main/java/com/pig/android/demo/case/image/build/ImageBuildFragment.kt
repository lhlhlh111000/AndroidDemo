package com.pig.android.demo.case.image.build

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pig.android.demo.R
import kotlinx.android.synthetic.main.fragment_image_build.*
import kotlinx.android.synthetic.main.view_to_build_image.view.*

/**
 * Title:
 * Description:
 * Copyright © 2001-2020 17173. All rights reserved.
 *
 * @author cqt
 * @version 2020/10/21
 */
class ImageBuildFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image_build, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_build_image.setOnClickListener {
            iv_build_image_show.setImageBitmap(buildImage(view))
        }
    }

    private fun buildImage(viewContainer: View) : Bitmap {
        val view = generateView(viewContainer as ViewGroup)

        return bitmapCreate(view)
    }

    private fun generateView(viewContainer: ViewGroup) : View {
        val view = LayoutInflater.from(activity).inflate(R.layout.view_to_build_image, viewContainer, false)

        view.iv_build_image_avatar.setImageResource(R.mipmap.ic_launcher)
        view.tv_build_image_name.text = "爱吃鱼呀"
        view.tv_build_image_desc.text = "这是一个个很无聊无聊无聊无聊无聊无聊无聊无聊无聊的自我描述啊"

        return view
    }

    private fun bitmapCreate(view: View): Bitmap {
        val widthSpec = View.MeasureSpec.makeMeasureSpec(
            view.layoutParams.width, View.MeasureSpec.EXACTLY)
        val heightSpec = View.MeasureSpec.makeMeasureSpec(
            view.layoutParams.height, View.MeasureSpec.EXACTLY)
        view.measure(widthSpec, heightSpec)
        val measureWidth = view.measuredWidth
        val measureHeight = view.measuredHeight
        view.layout(0, 0, measureWidth, measureHeight)

        val width = view.width
        val height = view.height
        val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        view.draw(Canvas(bmp))

        return bmp
    }
}