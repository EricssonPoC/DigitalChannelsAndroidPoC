package com.dfmovies.android.core.util.extension

import android.app.Activity
import android.content.Intent
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import java.io.File

fun Fragment.shareText(text: String) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, null)
    startActivity(shareIntent)
}

fun Activity.shareText(text: String) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, null)
    startActivity(shareIntent)
}

fun Activity.sharePDF(pdfFile: File, title: String) {
    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.type = "application/pdf"
    val fileUri = FileProvider.getUriForFile(
        this,
        this.applicationContext
            .packageName + ".provider",
        pdfFile
    )
    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    shareIntent.putExtra(Intent.EXTRA_STREAM, fileUri)
    startActivity(Intent.createChooser(shareIntent, title))
}