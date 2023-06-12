package com.akashsoam.martialartsclubnith.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "martialArtsDatabase";
    private static final int DATABASE_VERSION = 1;
    private static final String MARTIAL_ARTS_TABLE = "MartialArts";

    private static final String ID_KEY = "id";
    private static final String NAME_KEY = "name";
    private static final String PRICE_KEY = "price";
    private static final String COLOR_KEY = "color";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //text is string in sqlite
//        real is float in sqlite
        String createDatabaseSQL = "create table " + MARTIAL_ARTS_TABLE +
                "( " + ID_KEY + " integer primary key autoincrement" +
                ", " + NAME_KEY + " text" +
                ", " + PRICE_KEY + " real" +
                ", " + COLOR_KEY + " text" +
                " )";
        db.execSQL(createDatabaseSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + MARTIAL_ARTS_TABLE);
        onCreate(db);
    }

    public void addMartialArt(MartialArt martialArtObject) {
        SQLiteDatabase database = getWritableDatabase();
        String addMartialArtSQLCommand = "insert into " + MARTIAL_ARTS_TABLE + " values(null, '" + martialArtObject.getMartialArtName() + "', '" + martialArtObject.getMartialArtPrice() + "', '" + martialArtObject.getMartialArtColor() + "')";
        database.execSQL(addMartialArtSQLCommand);
        database.close();

    }
}

