package com.muhammadhidayah.apps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.List;
import com.muhammadhidayah.apps.adapter.kontenAdapter;
import com.muhammadhidayah.apps.helper.DataHelper;
import com.muhammadhidayah.apps.model.kontenModel;


public class Konten extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lsMateri;
    private kontenAdapter adapter;
    private List<kontenModel> tumbuhanlist;
    private DataHelper DBHelper;
    private EditText EtCari;
    private RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_konten);

        getSupportActionBar().setTitle(getIntent().getStringExtra("kategori"));

// init
        lsMateri = (ListView) findViewById(R.id.lsMateri);
        rl = (RelativeLayout) findViewById(R.id.rL);

        DBHelper = new DataHelper(Konten.this);

        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Cari.class);
                i.putExtra("idkategori", getIntent().getStringExtra("idkategori"));
                i.putExtra("kategori", getIntent().getStringExtra("kategori"));
                startActivity(i);
                finish();
            }
        });


        lsMateri.setOnItemClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        // getData
        DBHelper.open();
        tumbuhanlist = DBHelper.getDataSimplisia(getIntent().getStringExtra("idkategori"));
        DBHelper.close();

        // adapter
        adapter = new kontenAdapter(this, tumbuhanlist);
        adapter.notifyDataSetChanged();
        lsMateri.setAdapter(adapter);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        kontenModel tanaman = (kontenModel) parent.getItemAtPosition(position);
        Intent i = new Intent(Konten.this, Detail_Konten.class);
        i.putExtra("namaMateri", tanaman.getNamaMateri());
        i.putExtra("isi", tanaman.getIsi());
        i.putExtra("gambar", tanaman.getGambar());
        i.putExtra("idkategori", getIntent().getStringExtra("idkategori"));
        i.putExtra("kategori", getIntent().getStringExtra("kategori"));
        startActivity(i);
        finish();
    }


    public boolean onSupportNavigateUp() {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
        return true;
    }
}
