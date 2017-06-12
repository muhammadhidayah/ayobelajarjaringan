package com.muhammadhidayah.apps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.muhammadhidayah.apps.helper.DataHelper;
import com.muhammadhidayah.apps.model.kontenModel;

import java.util.List;

public class Cari extends AppCompatActivity implements TextWatcher, AdapterView.OnItemClickListener{

    private EditText txtCari;
    private ListView lsCari;
    private DataHelper DB;
    private ArrayAdapter<kontenModel> adapter;
    private List<kontenModel> kontenList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari);

        getSupportActionBar().setTitle(getIntent().getStringExtra("kategori"));

        txtCari = (EditText) findViewById(R.id.txtCari);
        lsCari = (ListView) findViewById(R.id.lsCari);
        lsCari.setEmptyView(findViewById(R.id.empty));

        DB = new DataHelper(getApplicationContext());

        setData();

        txtCari.addTextChangedListener(this);

        lsCari.setOnItemClickListener(this);
    }
    private void setData() {
        kontenList = DB.getDataSimplisia(getIntent().getStringExtra("idkategori"));

        adapter = new ArrayAdapter<kontenModel>(this, android.R.layout.simple_list_item_1, kontenList);

        lsCari.setAdapter(adapter);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        adapter.getFilter().filter(s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        kontenModel budaya = (kontenModel) parent.getItemAtPosition(position);
        Intent i = new Intent(Cari.this, Detail_Konten.class);
        i.putExtra("namaMateri", budaya.getNamaMateri());
        i.putExtra("isi", budaya.getIsi());
        i.putExtra("gambar", budaya.getGambar());
        i.putExtra("idkategori", getIntent().getStringExtra("idkategori"));
        i.putExtra("kategori", getIntent().getStringExtra("kategori"));
        startActivity(i);
        finish();
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
