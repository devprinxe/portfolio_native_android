package com.webexpertbd.portfolio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Button phone,email,stopwatch;
    private TextView age;
    private Handler handler;
    private String birthdateStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "onCreate called", Toast.LENGTH_SHORT).show();
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        stopwatch = findViewById(R.id.button4);
        age = findViewById(R.id.ageCountDown);
        phone.setOnClickListener(view -> {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + getString(R.string.my_phone)));
            startActivity(callIntent);
        });

        email.setOnClickListener(view -> {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:" + getString(R.string.my_email)));
            startActivity(emailIntent);
        });

        stopwatch.setOnClickListener(view -> {
            Intent stopwatchIntent = new Intent(MainActivity.this, StopWatchActivity.class);
            startActivity(stopwatchIntent);
        });

        handler = new Handler();

        birthdateStr = "2000-07-01";

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthdate = null;

        try {
            birthdate = dateFormat.parse(birthdateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final Date finalBirthdate = birthdate;
        final Runnable updateTimeRunnable = countAge(finalBirthdate);

        updateTimeRunnable.run();
    }

    public void contactFormRouting(View view) {
        Intent intent = new Intent(this, ContactActivity.class);
        intent.putExtra("service", getResources().getString(R.string.contact_form));
        startActivity(intent);
    }

    private  Runnable countAge(Date finalBirthdate){
        return new Runnable() {
            @Override
            public void run() {
                long currentTime = System.currentTimeMillis();
                assert finalBirthdate != null;
                long birthdateTime = finalBirthdate.getTime();
                long timeDifference = currentTime - birthdateTime;

                // Calculate years, months, minutes, and seconds
                int years = (int) (timeDifference / 31536000000L); // 1000 ms * 60 s * 60 min * 24 hours * 365 days
                int months = (int) ((timeDifference % 31536000000L) / 2628000000L); // Average month duration
                int minutes = (int) ((timeDifference / 60000) % 60);
                int seconds = (int) ((timeDifference / 1000) % 60);

                String countdown = "Age: " + years + " years, " + months + " months, " + minutes + " minutes, " + seconds + " seconds";
                age.setText(countdown);

                // Refresh every second
                handler.postDelayed(this, 1000);
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart called", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume called", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause called", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop called", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy called", Toast.LENGTH_SHORT).show();
    }
}