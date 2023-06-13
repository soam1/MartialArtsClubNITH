package com.akashsoam.martialartsclubnith;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.akashsoam.martialartsclubnith.model.DatabaseHandler;
import com.akashsoam.martialartsclubnith.model.MartialArt;

public class AddMartialArtActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtName, edtPrice, edtColor;
    Button btnAddMartialArt, btnBack;

    DatabaseHandler mDatabaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_martial_art);
        Toast.makeText(this, "This is add martial arts activity", Toast.LENGTH_SHORT).show();

        edtName = findViewById(R.id.edtName);
        edtPrice = findViewById(R.id.edtPrice);
        edtColor = findViewById(R.id.edtColor);

        btnBack = findViewById(R.id.btnGoBack);
        btnAddMartialArt = findViewById(R.id.btnAddMartialArt);

        mDatabaseHandler = new DatabaseHandler(AddMartialArtActivity.this);

        btnAddMartialArt.setOnClickListener(AddMartialArtActivity.this);
        btnBack.setOnClickListener(AddMartialArtActivity.this);
    }

    private void addMartialArtsObjectToDatabase() {
        String nameValue = edtName.getText().toString();
        String priceValue = edtPrice.getText().toString();
        String colorValue = edtColor.getText().toString();

        try {
            double priceDoubleValue = Double.parseDouble(priceValue);
            MartialArt martialArtObject = new MartialArt(0, nameValue, priceDoubleValue, colorValue);
            mDatabaseHandler.addMartialArt(martialArtObject);
            Toast.makeText(AddMartialArtActivity.this, "This martial art object\n" + martialArtObject.toString() + " is saved to SQLite database", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAddMartialArt) {
            addMartialArtsObjectToDatabase();
            return;
        } else if (v.getId() == R.id.btnGoBack) {
            this.finish();
            return;
        }
    }
}