package com.example.vjflighttracker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class FlightCheckWorker(
    private val context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override doWork(): Result {
        // Danh sách chuyến bay cần theo dõi (SGN/HAN -> PXU)
        val targetFlights = listOf("VJ392", "VJ394", "VJ396", "VJ421", "VJ423", "VJ425")
        
        // TODO: Viết logic gọi API/Cào dữ liệu trạng thái chuyến bay tại đây
        val flightToNotify = "VJ392" // Giả lập phát hiện chuyến VJ392 cất cánh

        sendNotification(
            "Cập nhật chuyến bay Vietjet",
            "Chuyến bay $flightToNotify đã cất cánh hướng về Pleiku (PXU)!"
        )

        return Result.success()
    }

    private fun sendNotification(title: String, message: String) {
        val channelId = "vj_flight_channel"
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Chuyến bay Vietjet PXU",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(1001, notification)
    }
}
