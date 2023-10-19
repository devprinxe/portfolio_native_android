package com.webexpertbd.portfolio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ContactActivity extends AppCompatActivity {
    String service,technology,emailAddress,phoneNumber,fullName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        EditText name = (EditText) findViewById(R.id.name);
        EditText email = (EditText) findViewById(R.id.emailAddress);
        EditText phone = (EditText) findViewById(R.id.phoneNumber);
        Button button = (Button) findViewById(R.id.sendButton);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.service_type,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedItem = spinner.getSelectedItem().toString();
                service = spinner.getSelectedItem().toString();
                // Update the items for the second spinner based on the selection
                ArrayAdapter<CharSequence> secondSpinnerAdapter;
                if (selectedItem.equals("Web Development")) {
                    secondSpinnerAdapter = ArrayAdapter.createFromResource(ContactActivity.this, R.array.web_dev,android.R.layout.simple_spinner_item);
                } else if (selectedItem.equals("Mobile App Development")) {
                    secondSpinnerAdapter = ArrayAdapter.createFromResource(ContactActivity.this, R.array.mobile_dev,android.R.layout.simple_spinner_item);
                } else if (selectedItem.equals("Digital Marketing")) {
                    secondSpinnerAdapter = ArrayAdapter.createFromResource(ContactActivity.this, R.array.digital_marketing,android.R.layout.simple_spinner_item);
                } else if (selectedItem.equals("Search Engine Optimization")) {
                    secondSpinnerAdapter = ArrayAdapter.createFromResource(ContactActivity.this, R.array.seo,android.R.layout.simple_spinner_item);
                } else {
                    // Handle other options or set a default list
                    secondSpinnerAdapter = ArrayAdapter.createFromResource(ContactActivity.this, R.array.web_dev,android.R.layout.simple_spinner_item);
                }
                secondSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(secondSpinnerAdapter);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                technology = spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        button.setOnClickListener(view -> {
            emailAddress = email.getText().toString();
            phoneNumber = phone.getText().toString();
            fullName = name.getText().toString();
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"princey01767@gmail.com"});
            intent.putExtra(Intent.EXTRA_SUBJECT, "Need " + service + " with " + technology);
            intent.putExtra(Intent.EXTRA_TEXT, "Name: "+ fullName + "\nEmail: "+ emailAddress + "\nPhone: "+phoneNumber);

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });
    }
}