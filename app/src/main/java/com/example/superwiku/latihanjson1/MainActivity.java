package com.example.superwiku.latihanjson1;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AlamatAdapter alamatAdapter;
    private ApiInterface apiInterface;
    private Biodata biodata;
    private ListView lvHobi,lvPekerjaan;
    private List<String> hobies;
    private List<String> kerjaan;
    private ImageView imageView;
    private TextView txtNama;
    private List<Alamat> alamatItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.list_alamat);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        lvHobi=(ListView)findViewById(R.id.list_hobi);
        lvPekerjaan=(ListView)findViewById(R.id.list_kerja);
        txtNama=(TextView)findViewById(R.id.txt_nama);
        imageView=(ImageView)findViewById(R.id.img_biodata);
        apiInterface=ApiClient.getApiClient().create(ApiInterface.class);
        Call<Biodata> call=apiInterface.getBiodata();
        call.enqueue(new Callback<Biodata>() {
            @Override
            public void onResponse(Call<Biodata> call, Response<Biodata> response) {
                biodata=response.body();
                txtNama.setText(biodata.getNama());
                Picasso.with(MainActivity.this).load(biodata.getFoto()).into(imageView);
                hobies=biodata.getHobi();
                kerjaan=biodata.getPekerjaan();
                alamatItem=biodata.getAlamat();
                alamatAdapter=new AlamatAdapter(alamatItem,MainActivity.this);
                recyclerView.setAdapter(alamatAdapter);
                ArrayAdapter<String> adapterHobi=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,hobies);
                lvHobi.setAdapter(adapterHobi);
                ArrayAdapter<String> adapterKerja=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,kerjaan);
                lvPekerjaan.setAdapter(adapterKerja);
            }

            @Override
            public void onFailure(Call<Biodata> call, Throwable t) {

            }
        });
    }
}
