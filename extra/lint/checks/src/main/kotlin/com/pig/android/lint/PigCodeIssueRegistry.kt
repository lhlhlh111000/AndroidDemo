package com.pig.android.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.Issue

/**
 * Title:
 * Description:
 * Copyright © 2001-2020 17173. All rights reserved.
 *
 * @author cqt
 * @version 2020/10/22
 */
class PigCodeIssueRegistry : IssueRegistry() {

    override val issues: List<Issue>
        get() = listOf(
            PigPrintDetector.ISSUE
        )

    override val api: Int
        get() = com.android.tools.lint.detector.api.CURRENT_API
}