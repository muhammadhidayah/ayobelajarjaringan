package com.muhammadhidayah.apps.model;

/**
 * Created by sontoloyo on 5/29/17.
 */

public class kategoriModel {
    private int idKategori;
    private String kategori;

    public kategoriModel(int idKategori, String kategori, String diskripsi) {
        this.idKategori = idKategori;
        this.kategori = kategori;
    }

    public int getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(int idKategori) {
        this.idKategori = idKategori;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

}