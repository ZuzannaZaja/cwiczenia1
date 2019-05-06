package com.example.cwiczenia;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeScreen extends AppCompatActivity {

    private TextView email;
    private Button btn_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        email = findViewById(R.id.email);
        btn_location = findViewById(R.id.location);
        //TODO: ZADANIE 3
        /*Intent intent = getIntent();
        String extraEmail = intent.getStringExtra("email");
        email.setText(extraEmail);*/



        btn_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        //TODO: ZADANIE 1
                /*Uri url = Uri.parse("https://www.google.pl/maps/place/Katedra+Telekomunikacji+AGH" +
                        "/@50.066878,19.9120969,17z/data=!4m8!1m2!2m1!1sagh+B9!3m4!1s0x47165ba468f4f7b3:0x6841cc5eba9179ff" +
                        "!8m2!3d50.0668633!4d19.9149519");
                Intent intent = new Intent(Intent.ACTION_VIEW, url);
                startActivity(intent);*/

            }
        });
    }
}
