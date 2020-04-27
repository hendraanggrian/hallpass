@file:JvmMultifileClass
@file:JvmName("LaunchyKt")
@file:Suppress("DEPRECATION", "UNUSED")

package com.hendraanggrian.appcompat.launchy

import android.app.Activity
import android.app.Fragment
import android.os.Build
import androidx.core.app.ActivityCompat
import com.hendraanggrian.appcompat.launchy.internal.isAllGranted

/**
 * Request permissions with auto-generated request code.
 * The result will then have to be handled in [callback]
 * instead of [Activity.onRequestPermissionsResult].
 *
 * @param permissions the permissions to request.
 * @param callback permission result callback.
 *
 * @see Activity.launchPermission
 */
fun <T : Activity> T.launchPermission(
    vararg permissions: String,
    callback: T.(isGranted: Boolean) -> Unit
) = when {
    Build.VERSION.SDK_INT >= 23 && !isAllGranted(*permissions) ->
        ActivityCompat.requestPermissions(this, permissions, Launchy.appendPermission(callback))
    else -> callback.invoke(this, true)
}

/**
 * Request permissions with auto-generated request code.
 * The result will then have to be handled in [callback]
 * instead of [Fragment.onRequestPermissionsResult].
 *
 * @param permissions the permissions to request.
 * @param callback permission result callback.
 *
 * @see Fragment.launchPermission
 */
fun <T : Fragment> T.launchPermission(
    vararg permissions: String,
    callback: T.(isGranted: Boolean) -> Unit
) = when {
    Build.VERSION.SDK_INT >= 23 && !activity.isAllGranted(*permissions) ->
        requestPermissions(permissions, Launchy.appendPermission(callback))
    else -> callback.invoke(this, true)
}

/**
 * Request permissions with auto-generated request code.
 * The result will then have to be handled in [callback]
 * instead of [android.support.v4.app.Fragment.onRequestPermissionsResult].
 *
 * @param permissions the permissions to request.
 * @param callback permission result callback.
 *
 * @see android.support.v4.app.Fragment.requestPermissions
 */
fun <T : androidx.fragment.app.Fragment> T.launchPermission(
    vararg permissions: String,
    callback: T.(isGranted: Boolean) -> Unit
) = when {
    Build.VERSION.SDK_INT >= 23 && !context!!.isAllGranted(*permissions) ->
        requestPermissions(permissions, Launchy.appendPermission(callback))
    else -> callback.invoke(this, true)
}