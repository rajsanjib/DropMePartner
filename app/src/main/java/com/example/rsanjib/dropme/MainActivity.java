package com.example.rsanjib.dropme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button giveRide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find View-Elements
        giveRide = (Button) findViewById(R.id.giveRide);

        // Create Button(GetRide) Click Listener
        giveRide.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Invoke Map Activity
                Intent mapIntent = new Intent(MainActivity.this, MapsActivity.class);
                mapIntent.putExtra("key", 1);
                MainActivity.this.startActivity(mapIntent);
            }
        });

    }

}
