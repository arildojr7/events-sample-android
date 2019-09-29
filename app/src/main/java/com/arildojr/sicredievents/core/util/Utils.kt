package com.arildojr.sicredievents.core.util

import android.content.Context
import android.content.Intent
import com.arildojr.sicredievents.R

fun shareContent(title: String?, description: String?, context: Context) {
    val shareIntent = Intent(Intent.ACTION_SEND)

    shareIntent.type = "text/plain"

    shareIntent.putExtra(
        Intent.EXTRA_SUBJECT,
        title
    )

    shareIntent.putExtra(
        Intent.EXTRA_TEXT,
        description
    )

    context.startActivity(
        Intent.createChooser(
            shareIntent,
            context.getString(R.string.share_with)
        )
    )
}