package com.akashsoam.martialartsclubnith.model;

import androidx.annotation.NonNull;

public class MartialArt {
    private int martialArtID;
    private String martialArtName;
    private double martialArtPrice;
    private String martialArtColor;

    public MartialArt(int martialArtID, String martialArtName, double martialArtPrice, String martialArtColor) {
//        this.martialArtID = martialArtID;
//        this.martialArtName = martialArtName;
//        this.martialArtPrice = martialArtPrice;
//        this.martialArtColor = martialArtColor;
        setMartialArtColor(martialArtColor);
        setMartialArtID(martialArtID);
        setMartialArtName(martialArtName);
        setMartialArtPrice(martialArtPrice);
    }

    public int getMartialArtID() {
        return martialArtID;
    }

    public String getMartialArtName() {
        return martialArtName;
    }

    public double getMartialArtPrice() {
        return martialArtPrice;
    }

    public String getMartialArtColor() {
        return martialArtColor;
    }

    public void setMartialArtID(int martialArtID) {
        this.martialArtID = martialArtID;
    }

    public void setMartialArtName(String martialArtName) {
        this.martialArtName = martialArtName;
    }

    public void setMartialArtPrice(double martialArtPrice) {
        this.martialArtPrice = martialArtPrice;
    }

    public void setMartialArtColor(String martialArtColor) {
        this.martialArtColor = martialArtColor;
    }

    @NonNull
    @Override
    public String toString() {
        return martialArtID + "\n" + getMartialArtName() + "\n" + getMartialArtPrice() + "\n" + getMartialArtColor();
    }
}
