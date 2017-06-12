package com.muhammadhidayah.apps.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

import com.muhammadhidayah.apps.model.kategoriModel;
import com.muhammadhidayah.apps.model.kontenModel;

/**
 * Created by sontoloyo on 5/29/17.
 */

public class DataHelper extends SQLiteAssetHelper {
    public static final String DATABASE_NAME = "DBAplikasi.db";
    //    public static final String DATABASE_NAME = "dbaplikasi.db";
    public static final int DATABASE_VERSION = 1;
    private SQLiteDatabase mDb;
    Context mContext;

    // untuk memanggil database dbaplikasi.db
    public DataHelper(Context context) {
        super(context, DATABASE_NAME,  null, DATABASE_VERSION);
        this.mContext = context;
        setForcedUpgrade(DATABASE_VERSION);
    }

    // digunakan untuk mengakses database
    public DataHelper open() throws SQLException {
        try {
            mDb = this.getReadableDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    // menutup akses database
    @Override
    public void close() {
        mDb.close();
    }

    public List<kontenModel> getDataSimplisia(String id){
        kontenModel tanaman = null;
        List<kontenModel> tanamanList = new ArrayList<kontenModel>();
        open();
        Cursor cursor = mDb.rawQuery("SELECT * FROM konten WHERE idkategori = " + id + " ORDER BY namaMateri ASC", null);
//        Cursor cursor = mDb.rawQuery("SELECT a.*, b.nama FROM konten a JOIN gambar b ON a.idKonten = b.idKonten WHERE a.idKategori = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            tanaman = new kontenModel(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
            tanamanList.add(tanaman);
            cursor.moveToNext();
        }

        cursor.close();
        close();
        return tanamanList;
    }

    public List<kategoriModel> dataKategori(String id){
        kategoriModel kategori = null;
        List<kategoriModel> kategoriList = new ArrayList<kategoriModel>();
        open();
        Cursor cursor = mDb.rawQuery("SELECT * FROM kategori WHERE idkategori = " + id, null);
        cursor.moveToFirst();
        while (cursor.moveToNext()){
            kategori = new kategoriModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            kategoriList.add(kategori);
            cursor.moveToNext();
        }

        cursor.close();
        close();
        return kategoriList;
    }

}