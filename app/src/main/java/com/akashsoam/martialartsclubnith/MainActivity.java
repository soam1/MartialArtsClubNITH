package com.akashsoam.martialartsclubnith;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;

import com.akashsoam.martialartsclubnith.model.DatabaseHandler;
import com.akashsoam.martialartsclubnith.model.MartialArt;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.akashsoam.martialartsclubnith.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;
    private DatabaseHandler mDatabaseHandler;
    private double totalMartialArtsPrice;
    private ScrollView mScrollView;
    private int martialArtButtonWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        mDatabaseHandler = new DatabaseHandler(MainActivity.this);
        totalMartialArtsPrice = 0.0;
        mScrollView = (ScrollView) findViewById(R.id.scrollView);
        Point screenSize = new Point();
        getWindowManager().getDefaultDisplay().getSize(screenSize);
        martialArtButtonWidth = screenSize.x / 2;
        modifyUserInterface();


//        binding.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAnchorView(R.id.fab)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    private void modifyUserInterface() {

        ArrayList<MartialArt> allMartialArtObjects = mDatabaseHandler.returnAllMartialArtObjects();
        mScrollView.removeAllViewsInLayout();

        if (allMartialArtObjects.size() > 0) {
            GridLayout gridLayout = new GridLayout(MainActivity.this);
            gridLayout.setRowCount((allMartialArtObjects.size() + 1) / 2);
            gridLayout.setColumnCount(2);

            MartialArtButton[] martialArtButtons = new MartialArtButton[allMartialArtObjects.size()];
            int index = 0;
            for (MartialArt martialArtObj : allMartialArtObjects) {
                martialArtButtons[index] = new MartialArtButton(MainActivity.this, martialArtObj);
                martialArtButtons[index].setText(martialArtObj.getMartialArtID() + "\n" + martialArtObj.getMartialArtName() + "\n" + martialArtObj.getMartialArtPrice() + "\n" + martialArtObj.getMartialArtColor());

                switch (martialArtObj.getMartialArtColor().toUpperCase()) {
                    case "RED":
                        martialArtButtons[index].setBackgroundColor(Color.RED);
                        break;
                    case "BLUE":
                        martialArtButtons[index].setBackgroundColor(Color.BLUE);
                        break;
                    case "GREEN":
                        martialArtButtons[index].setBackgroundColor(Color.GREEN);
                        break;
                    case "BLACK":
                        martialArtButtons[index].setBackgroundColor(Color.BLACK);
                        break;
                    case "YELLOW":
                        martialArtButtons[index].setBackgroundColor(Color.YELLOW);
                        break;
                    case "WHITE":
                        martialArtButtons[index].setBackgroundColor(Color.WHITE);
                        break;
                    default:
                        martialArtButtons[index].setBackgroundColor(Color.GRAY);

                }
                martialArtButtons[index].setOnClickListener(MainActivity.this);
                gridLayout.addView(martialArtButtons[index], martialArtButtonWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
            mScrollView.addView(gridLayout);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.add_martial_art) {
            Intent intent = new Intent(MainActivity.this, AddMartialArtActivity.class);
            startActivity(intent);
            return true;
        }
        //noinspection SimplifiableIfStatement
        else if (id == R.id.delete_martial_art) {
            Intent intent = new Intent(MainActivity.this, DeleteMartialArtActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.update_martial_art) {
            Intent intent = new Intent(MainActivity.this, UpdateMartialArtActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.reset_martial_art) {
            totalMartialArtsPrice = 0;
            Toast.makeText(this, totalMartialArtsPrice + "", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        MartialArtButton martialArtButton = (MartialArtButton) v;
        totalMartialArtsPrice = totalMartialArtsPrice + martialArtButton.getMartialArtPrice();
        String martialArtPriceFormatted = NumberFormat.getCurrencyInstance().format(totalMartialArtsPrice);
        Toast.makeText(MainActivity.this, martialArtPriceFormatted, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        modifyUserInterface();
    }
}