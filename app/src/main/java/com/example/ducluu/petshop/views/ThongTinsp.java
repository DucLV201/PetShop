package com.example.ducluu.petshop.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.example.ducluu.petshop.Helper.ManagementCard;
import com.example.ducluu.petshop.databinding.ActivityThongTinspBinding;
import com.example.ducluu.petshop.model.MonAn;
import com.example.ducluu.petshop.R;


import java.text.NumberFormat;
import java.util.Locale;


public class ThongTinsp extends AppCompatActivity {

    ActivityThongTinspBinding binding;
    String gia_format;
    MonAn object;
    ManagementCard managementCard;
    private int numberOrder = 1;

    TextView btnBack;
    CardView addCart;
    FrameLayout frameLayout;
    Toolbar toolbar;
    RatingBar ratingBar;

    ImageView foodPic;
    TextView foodTxt, caloriesTxt, txtmota,txttuoi,txtgiong;
    TextView txtSao, txtGia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityThongTinspBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        toolbar = findViewById(R.id.toolbarDetail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        managementCard = new ManagementCard(this);

        AnhXa();
        cainaylahome();
        cainaylaNut();

    }

    private void cainaylahome() {

        object = (MonAn) getIntent().getSerializableExtra("detail");
        Glide.with(getApplicationContext()).load(object.getHinhMon()).into(foodPic);

        object.getMaSP();
        foodTxt.setText(object.getTenMon());
        txtmota.setText(object.getMoTa());

        txttuoi.setText(String.valueOf(object.getTuoi()));
        txtgiong.setText(object.getGiong());


        gia_format = NumberFormat.getNumberInstance(Locale.US).format(object.getGia());
        txtGia.setText(gia_format + "đ");

        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(ShowDetail.this, String.valueOf(numberOrder), Toast.LENGTH_SHORT).show();
                object.setNumberInCard(numberOrder);
                managementCard.insertFood(object);
            }
        });
//        }
    }


    private void cainaylaNut() {

        addCart = (CardView) findViewById(R.id.addCart);
        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThongTinsp.this, GioHang.class));
            }
        });
    }

    private void AnhXa() {
        foodPic = (ImageView) findViewById(R.id.foodPic);
        foodTxt = (TextView) findViewById(R.id.foodTxt);
        txtgiong = (TextView) findViewById(R.id.txtgiong);
        txtmota = (TextView) findViewById(R.id.txtmota);

        txttuoi = (TextView) findViewById(R.id.txtTuoi);
        txtGia = (TextView) findViewById(R.id.txtGia);
        frameLayout = (FrameLayout) findViewById(R.id.frameAdd);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}