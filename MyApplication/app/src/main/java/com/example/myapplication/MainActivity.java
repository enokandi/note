package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        final ImageView pashmali = findViewById(R.id.pashmali);
        Button heydari = findViewById(R.id.ahmad);

        heydari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pashmali.getVisibility() == View.INVISIBLE) {
                    pashmali.setVisibility(View.VISIBLE);
                }
                else {
                    pashmali.setVisibility(View.INVISIBLE);
                }
            }
        });


    }


}
