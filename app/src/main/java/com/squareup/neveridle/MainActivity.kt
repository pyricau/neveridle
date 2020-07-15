package com.squareup.neveridle

import android.app.Activity
import android.os.Handler
import android.widget.Toast

class MainActivity : Activity() {

  override fun onResume() {
    super.onResume()

    keepMainThreadBusy()
    finish()

    val toastMessage =
      "Resumed & Finished activity. Main thread not idling now causing activity leak."
    Toast.makeText(applicationContext, toastMessage, Toast.LENGTH_LONG)
        .show()
  }

  private fun keepMainThreadBusy() {
    val handler = Handler()

    val neverIdle = object : Runnable {
      override fun run() {
        handler.post(this)
      }
    }
    handler.post(neverIdle)
  }

}