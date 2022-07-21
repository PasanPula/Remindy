package com.usj.remindy;
import android.app.NotificationChannel ;
import android.app.NotificationManager ;
import android.app.PendingIntent ;
import android.app.Service ;
import android.content.Intent ;
import android.os.IBinder ;


import androidx.core.app.NotificationCompat;

public class NotificationService extends Service {

        public static final String NOTIFICATION_CHANNEL_ID = "usj.remindy.ch02" ;
        private final static String default_notification_channel_id = "usj.remindy.ch0" ;
        private static String title ="empty";
        private static String description ="empty";
        public  NotificationService () {
        }

        public static void  setNotifi(String ti,String descrip)
        {
            description = descrip;
            title = ti;
        }

        @Override
        public IBinder onBind (Intent intent) {

            Intent notificationIntent = new Intent(getApplicationContext() ,  MainActivity.class ) ;

            notificationIntent.putExtra( "fromNotification" , true ) ;
            notificationIntent.setFlags(Intent. FLAG_ACTIVITY_CLEAR_TOP | Intent. FLAG_ACTIVITY_SINGLE_TOP ) ;
            PendingIntent pendingIntent = PendingIntent. getActivity ( this, 0 , notificationIntent , 0 ) ;

            NotificationManager mNotificationManager = (NotificationManager) getSystemService( NOTIFICATION_SERVICE ) ;
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext() , default_notification_channel_id ) ;
            mBuilder.setContentTitle(  title ) ;
            mBuilder.setContentIntent(pendingIntent) ;
            mBuilder.setContentText( description ) ;
            mBuilder.setSmallIcon(R.drawable. ic_launcher_foreground ) ;
            mBuilder.setAutoCancel( true ) ;

            if (android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES. O ) {
                int importance = NotificationManager.IMPORTANCE_HIGH ;
                NotificationChannel notificationChannel = new
                        NotificationChannel( NOTIFICATION_CHANNEL_ID , "Remindy" , importance) ;
                mBuilder.setChannelId( NOTIFICATION_CHANNEL_ID ) ;
                assert mNotificationManager != null;
                mNotificationManager.createNotificationChannel(notificationChannel) ;
            }

            assert mNotificationManager != null;
            mNotificationManager.notify(( int ) System. currentTimeMillis () , mBuilder.build()) ;
            throw new UnsupportedOperationException( "Not yet implemented" ) ;
        }




    }

