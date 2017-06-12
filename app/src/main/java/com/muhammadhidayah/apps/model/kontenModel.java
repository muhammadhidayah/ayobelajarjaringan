package com.muhammadhidayah.apps.model;

/**
 * Created by sontoloyo on 5/29/17.
 */

public class kontenModel {
    int idKonten, kategori;
    String namaMateri, isi, gambar;

    public kontenModel(int idKonten, int kategori, String namaMateri, String isi, String gambar) {
        this.idKonten = idKonten;
        this.kategori = kategori;
        this.namaMateri = namaMateri;
        this.isi = isi;
        this.gambar = gambar;
    }

    public int getIdKonten() {
        return idKonten;
    }

    public void setIdKonten(int idKonten) {
        this.idKonten = idKonten;
    }

    public int getKategori() {
        return kategori;
    }

    public void setKategori(int kategori) {
        this.kategori = kategori;
    }

    public String getNamaMateri() {
        return namaMateri;
    }

    public void setNamaMateri(String namaMateri) {
        this.namaMateri = namaMateri;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    @Override
    public String toString() {
        return getNamaMateri();
    }
}
