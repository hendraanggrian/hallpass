package com.example.hallpass

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.AppBarLayout
import com.hendraanggrian.appcompat.hallpass.withPermission
import com.priyankvasa.android.cameraviewex.CameraView

class MainActivity : AppCompatActivity() {
    lateinit var appbarLayout: AppBarLayout
    lateinit var toolbar: Toolbar
    lateinit var cameraView: CameraView

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appbarLayout = findViewById(R.id.appbarLayout)
        toolbar = findViewById(R.id.toolbar)
        cameraView = findViewById(R.id.cameraView)
        setSupportActionBar(toolbar)

        withPermission(Manifest.permission.CAMERA, { isGranted ->
            when {
                isGranted -> cameraView.start()
                else -> finish()
            }
        }) { settingsIntent ->
            AlertDialog.Builder(this)
                .setTitle("Permission Denied")
                .setMessage("You need to manually enable it.")
                .setPositiveButton("Go to Settings") { _, _ ->
                    startActivity(settingsIntent)
                    finish()
                }
                .setNegativeButton(android.R.string.no) { _, _ ->
                    finish()
                }
                .show()
        }
    }

    override fun onDestroy() {
        cameraView.destroy()
        super.onDestroy()
    }
}
