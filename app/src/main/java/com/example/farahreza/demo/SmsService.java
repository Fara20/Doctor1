package com.example.farahreza.demo;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static android.content.ContentValues.TAG;

public class SmsService extends FirebaseMessagingService {
    private static final int PERMISSION_REQUEST_CODE = 1;
    Session session;
    String phn,msg;
    public SmsService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.

            } else {
                // Handle message within 10 seconds
            }

        }
        if (remoteMessage.getNotification() != null) {
            Toast.makeText(getApplicationContext(),remoteMessage.getNotification().getTitle(),Toast.LENGTH_SHORT).show();
            if(session.gethospital().compareTo(remoteMessage.getNotification().getTitle())==0)
            { Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());

            phn=remoteMessage.getNotification().getBody();
            msg="Your Appoinment Has Been Scheduled";
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {

                if (checkSelfPermission(Manifest.permission.SEND_SMS)
                        == PackageManager.PERMISSION_DENIED) {

                    Log.d("permission", "permission denied to SEND_SMS - requesting it");
                    String[] permissions = {Manifest.permission.SEND_SMS};
                   // requestPermissions(permissions, PERMISSION_REQUEST_CODE);

                }
            }
            SmsManager sms=SmsManager.getDefault();
            sms.sendTextMessage(phn,null,msg,null,null);}
        }

    }

    @Override
    public void onDeletedMessages() {
        Toast.makeText(getApplicationContext(),"Delete", Toast.LENGTH_LONG).show();
        super.onDeletedMessages();
    }
}
