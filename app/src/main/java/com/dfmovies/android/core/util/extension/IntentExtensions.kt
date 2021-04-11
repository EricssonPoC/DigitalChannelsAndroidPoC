package com.dfmovies.android.core.util.extension

import android.content.Context
import android.content.Intent
import android.content.pm.LabeledIntent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.provider.Settings
import java.io.ByteArrayOutputStream

fun emailAppIntent(context: Context): Intent? {
    val emailIntent = Intent(Intent.ACTION_VIEW, Uri.parse("mailto:"))
    val packageManager = context.packageManager

    val activitiesHandlingEmails = packageManager.queryIntentActivities(emailIntent, 0)
    if (activitiesHandlingEmails.isNotEmpty()) {
        // use the first email package to create the chooserIntent
        val firstEmailPackageName = activitiesHandlingEmails.first().activityInfo.packageName
        val firstEmailInboxIntent = packageManager.getLaunchIntentForPackage(firstEmailPackageName)
        val emailAppChooserIntent = Intent.createChooser(firstEmailInboxIntent, "")

        // created UI for other email packages and add them to the chooser
        val emailInboxIntents = mutableListOf<LabeledIntent>()
        for (i in 1 until activitiesHandlingEmails.size) {
            val activityHandlingEmail = activitiesHandlingEmails[i]
            val packageName = activityHandlingEmail.activityInfo.packageName
            val intent = packageManager.getLaunchIntentForPackage(packageName)
            emailInboxIntents.add(
                LabeledIntent(
                    intent,
                    packageName,
                    activityHandlingEmail.loadLabel(packageManager),
                    activityHandlingEmail.icon
                )
            )
        }
        val extraEmailInboxIntents = emailInboxIntents.toTypedArray()
        return emailAppChooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, extraEmailInboxIntents)
    } else {
        return null
    }
}

fun generalSettingsIntent(context: Context): Intent {
    val uri: Uri =
        Uri.fromParts("package", context.packageName, null)
    return Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        .setData(uri)
}

fun notificationSettingsIntent(context: Context): Intent {
    return Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        .putExtra(
            Settings.EXTRA_APP_PACKAGE,
            context.packageName
        )
}

fun callIntent(phoneNumber: String): Intent {
    return Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null))
}

fun sendEmailIntent(mail: String): Intent {
    return Intent(Intent.ACTION_VIEW, Uri.parse("mailto:$mail"))
}

fun Context.showShareIntent(bitmap: Bitmap): Intent {
    val bytes = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
    val path = MediaStore.Images.Media.insertImage(contentResolver, bitmap, "tempimage", null)
    val uri = Uri.parse(path)

    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_STREAM, uri)
        type = "image/*"
    }
    return Intent.createChooser(sendIntent, null)
}