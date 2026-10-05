package com.example.notification;

// Import NotificationChannel to create a notification channel
import android.app.NotificationChannel;

// Import NotificationManager to display notifications
import android.app.NotificationManager;

// Import Build to check the Android version
import android.os.Build;

// Import Bundle for Activity lifecycle
import android.os.Bundle;

// Import View to handle button click events
import android.view.View;

// Import Button widget
import android.widget.Button;

// Import AppCompatActivity for creating the Activity
import androidx.appcompat.app.AppCompatActivity;

// Import NotificationCompat to build notifications
import androidx.core.app.NotificationCompat;


public class MainActivity extends AppCompatActivity {

    // Declare Button variable
    // This button will be used to place the order
    Button btnOrder;


    // Create a unique ID for the notification channel
    // The same channel ID will be used while creating the notification
    String channelId = "order_channel";


    // onCreate() method is called when the Activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect Java file with activity_main.xml
        setContentView(R.layout.activity_main);


        // Find the Order button from XML using its ID
        btnOrder = findViewById(R.id.btnOrder);


        // Create the notification channel
        // Required for Android 8.0 (API 26) and higher
        createNotificationChannel();


        // Set click event on the Order button
        // This code executes when the user clicks the button
        btnOrder.setOnClickListener(new View.OnClickListener() {

            // onClick() method is called when the button is clicked
            @Override
            public void onClick(View v) {


                // Create a Notification Builder
                // Builder is used to prepare the notification
                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(
                                MainActivity.this,
                                channelId
                        );


                // Set a small icon for the notification
                // This icon appears in the notification area
                builder.setSmallIcon(
                        android.R.drawable.ic_dialog_info
                );


                // Set the title of the notification
                builder.setContentTitle("Order Confirmed");


                // Set the message/content of the notification
                builder.setContentText(
                        "Your pizza order has been placed successfully."
                );


                // Set the notification priority
                // DEFAULT means normal notification priority
                builder.setPriority(
                        NotificationCompat.PRIORITY_DEFAULT
                );


                // Automatically remove the notification
                // when the user taps on it
                builder.setAutoCancel(true);


                // Get the NotificationManager system service
                // NotificationManager is responsible for displaying
                notifications
                NotificationManager manager =
                        (NotificationManager)
                                getSystemService(
                                        NOTIFICATION_SERVICE
                                );


                // Display the notification
                // 1 is the unique notification ID
                // builder.build() creates the final notification object
                manager.notify(1, builder.build());
            }
        });
    }


    // Method used to create the Notification Channel
    private void createNotificationChannel() {


        // Check whether the Android version is 8.0 or higher
        // Notification channels are required from Android 8.0 onwards
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {


            // Create a NotificationChannel object
            NotificationChannel channel =
                    new NotificationChannel(
                            channelId,                       // Channel ID
                            "Order Notifications",           // Channel name
                            NotificationManager.IMPORTANCE_DEFAULT //
                            Importance
                    );


            // Get the NotificationManager system service
            NotificationManager manager =
                    getSystemService(NotificationManager.class);


            // Register the notification channel with Android
            manager.createNotificationChannel(channel);
        }
    }
}