package com.pig.android.demo.model

/**
 * Title:
 * Description:
 * Copyright © 2001-2020 17173. All rights reserved.
 *
 * @author cqt
 * @version 2020/8/18
 */

data class Item(val name: String, val action: (() -> Unit)? = null)