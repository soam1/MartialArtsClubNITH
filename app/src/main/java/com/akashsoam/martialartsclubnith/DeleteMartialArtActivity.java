package com.akashsoam.martialartsclubnith;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.akashsoam.martialartsclubnith.model.DatabaseHandler;
import com.akashsoam.martialartsclubnith.model.MartialArt;

import java.util.ArrayList;

public class DeleteMartialArtActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    private DatabaseHandler mDatabaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_martial_art);

        mDatabaseHandler = new DatabaseHandler(DeleteMartialArtActivity.this);
        updateTheUserInterface();//this method will update the user interface according to how many martial art objects are there in db.(since we don't know how many there are, we will create almost all the ui layout using java classes only not with xml layout file).


    }

    private void updateTheUserInterface() {
        ArrayList<MartialArt> allMartialArtObjects = mDatabaseHandler.returnAllMartialArtObjects();
        RelativeLayout relativeLayout = new RelativeLayout(DeleteMartialArtActivity.this);
        ScrollView scrollView = new ScrollView(DeleteMartialArtActivity.this);
        RadioGroup radioGroup = new RadioGroup(DeleteMartialArtActivity.this);
        for (MartialArt ma : allMartialArtObjects) {
            RadioButton currentRadioButton = new RadioButton(DeleteMartialArtActivity.this);
            currentRadioButton.setId(ma.getMartialArtID());
            currentRadioButton.setText(ma.toString());
            radioGroup.addView(currentRadioButton);
        }
        radioGroup.setOnCheckedChangeListener(DeleteMartialArtActivity.this);
        Button btnBack = new Button(DeleteMartialArtActivity.this);
        btnBack.setText(R.string.go_back);
        scrollView.addView(radioGroup);

        RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        buttonParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        buttonParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        buttonParams.setMargins(0, 0, 0, 70);

        relativeLayout.addView(btnBack, buttonParams);

        ViewGroup.LayoutParams scrollViewParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        relativeLayout.addView(scrollView, scrollViewParams);
        setContentView(relativeLayout);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        mDatabaseHandler.deleteMartialArtFromDatabaseByID(checkedId);
        Toast.makeText(DeleteMartialArtActivity.this, "the martial art object is deleted", Toast.LENGTH_SHORT).show();
        updateTheUserInterface();
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(DeleteMartialArtActivity.this, "going back", Toast.LENGTH_SHORT).show();
        finish();
    }
}