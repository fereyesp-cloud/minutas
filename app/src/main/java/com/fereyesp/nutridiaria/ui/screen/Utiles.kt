package com.fereyesp.nutridiaria.ui.screen

import android.content.Context
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.VibrationEffect
import android.os.Vibrator

/**
 * Reproduce un tono de exito
 *
 */
fun reproducirTonoExito() {
    try {
        val tono = ToneGenerator(AudioManager.STREAM_NOTIFICATION, 70)
        tono.startTone(ToneGenerator.TONE_PROP_ACK, 200)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

/**
 * Activa una vibración cuando falla
 *
 */
fun vibrarError(context: Context) {
    try {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(VibrationEffect.createOneShot(300, VibrationEffect.DEFAULT_AMPLITUDE))
    } catch (e: Exception) {
        e.printStackTrace()
    }
}