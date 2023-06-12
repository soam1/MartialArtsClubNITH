package com.akashsoam.martialartsclubnith;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class AddMartialArtActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_martial_art);
        Toast.makeText(this, "This is add martial arts activity", Toast.LENGTH_SHORT).show();
    }
}