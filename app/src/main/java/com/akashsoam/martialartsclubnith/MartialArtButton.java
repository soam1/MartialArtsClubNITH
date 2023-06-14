package com.akashsoam.martialartsclubnith;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

import com.akashsoam.martialartsclubnith.model.MartialArt;

public class MartialArtButton extends AppCompatButton {
    private MartialArt mMartialArtObject;

    public MartialArtButton(@NonNull Context context, MartialArt martialArtObject) {
        super(context);
        mMartialArtObject = martialArtObject;

    }
    public String getMartialArtColor(){
        return mMartialArtObject.getMartialArtColor();
    }public double getMartialArtPrice(){
        return mMartialArtObject.getMartialArtPrice();
    }
}
