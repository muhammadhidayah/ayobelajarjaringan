package com.muhammadhidayah.apps;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Shaktie on 26-Apr-17.
 */

public class Detail_Konten extends AppCompatActivity {

    TextView lblDIsi, txt_result_materi;
    ImageView imgKonten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_konten);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(getIntent().getStringExtra("kategori"));

        collapsingToolbarLayout.setCollapsedTitleTextColor(
                ContextCompat.getColor(this, R.color.white));
        collapsingToolbarLayout.setExpandedTitleColor(
                ContextCompat.getColor(this, R.color.colorPrimary));


        imgKonten = (ImageView) findViewById(R.id.imgKonten);
        lblDIsi = (TextView) findViewById(R.id.lblDIsi);
        txt_result_materi = (TextView) findViewById(R.id.txt_result_materi);

        txt_result_materi.setText(getIntent().getStringExtra("namaMateri"));
        lblDIsi.setText(getIntent().getStringExtra("isi"));

        int id = getResources().getIdentifier("com.muhammadhidayah.apps:drawable/" + getIntent().getStringExtra("gambar"), null, null);
        imgKonten.setImageResource(id);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), Konten.class);
        i.putExtra("idkategori", getIntent().getStringExtra("idkategori"));
        i.putExtra("kategori", getIntent().getStringExtra("kategori"));
        startActivity(i);
        finish();
    }

    public boolean onSupportNavigateUp() {
        Intent i = new Intent(getApplicationContext(), Konten.class);
        i.putExtra("idkategori", getIntent().getStringExtra("idkategori"));
        i.putExtra("kategori", getIntent().getStringExtra("kategori"));
        startActivity(i);
        finish();
        return true;
    }
}
