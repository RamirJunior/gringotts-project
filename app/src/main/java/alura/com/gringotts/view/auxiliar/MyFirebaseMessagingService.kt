package alura.com.gringotts.view.auxiliar

import alura.com.gringotts.R
import alura.com.gringotts.view.initial.InitialActivity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlin.random.Random

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        remoteMessage.notification?.let {
            notificatioReceived(applicationContext, it.title, it.body) }
    }

    override fun onNewToken(remoteMessage: String) {
        super.onNewToken(remoteMessage)
    }

    fun notificatioReceived(context: Context, title: String?, message: String?) {

        val notificationChannel: NotificationChannel
        val builder: NotificationCompat.Builder
        val channelId = TXT_CHANNEL
        val notificationManager: NotificationManager = applicationContext
            .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val id = Random.nextInt(1, 100)
        val intent = Intent(this, InitialActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel =
                NotificationChannel(channelId, message, NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = NotificationCompat.Builder(this, channelId)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(message)
                .setStyle(NotificationCompat.BigTextStyle()
                    .bigText(message)
                    .setSummaryText(TXT_MESSAGE))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
        } else {
            builder = NotificationCompat.Builder(this)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
        }
        notificationManager.notify(id, builder.build())
    }

    companion object {
        private const val TXT_CHANNEL = "Pix"
        private const val TXT_MESSAGE = "Mensagem Teste"
    }

}
