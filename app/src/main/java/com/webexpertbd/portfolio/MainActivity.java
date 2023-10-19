package com.webexpertbd.portfolio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button phone = findViewById(R.id.phone);
        Button email = findViewById(R.id.email);
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
    }

    public void contactFormRouting(View view) {
        Intent intent = new Intent(this, ContactActivity.class);
        intent.putExtra("service", getResources().getString(R.string.contact_form));
        startActivity(intent);
    }
}