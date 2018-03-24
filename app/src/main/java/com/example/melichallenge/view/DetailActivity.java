package com.example.melichallenge.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.melichallenge.R;
import com.example.melichallenge.controller.MLProductController;
import com.example.melichallenge.controller.MlDetailController;
import com.example.melichallenge.model.MLDescription;
import com.example.melichallenge.model.MlProdDetail;
import com.example.melichallenge.model.Picture;
import com.example.melichallenge.util.ResultListener;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener,
        ViewPagerEx.OnPageChangeListener {

    public static final String PRODUCTID = "productId";

    private String productId;

    private MlProdDetail prodDetail;

    private MLProductController mlProductController;

    MlDetailController mlDetailController;
    AVLoadingIndicatorView avi;

    TextView prodNameTextView;
    TextView prodPriceTextView;
    TextView prodDescTextView;
    BaseSliderView.OnSliderClickListener onSliderClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarDetail);
        prodNameTextView = (TextView) findViewById(R.id.product_name_detail);
        prodPriceTextView = (TextView) findViewById(R.id.detail_price);
        /*prodDescTextView = (TextView) findViewById(R.id.detail_desc);*/

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        toolbar.getNavigationIcon().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        final SliderLayout sliderLayout;
        /*avi = (AVLoadingIndicatorView) findViewById(R.id.aviDetail);*/
        sliderLayout = (SliderLayout) findViewById(R.id.slider);
        final List<String> picUrl = new ArrayList<>();

        onSliderClickListener = this;


        Intent unIntent = getIntent();


        Bundle unBundle = unIntent.getExtras();


        productId = unBundle.getString(PRODUCTID);
        mlProductController = new MLProductController(getApplicationContext());
        mlDetailController = new MlDetailController(getApplicationContext());
        mlProductController.getProductWithID(productId, new ResultListener<MlProdDetail>() {
            @Override
            public void finish(MlProdDetail resultado) {

                prodNameTextView.setText(resultado.getTitle());

                int a = (int) Math.round(resultado.getPrice());
                Integer price = new Integer(a);

                prodPriceTextView.setText(price.toString());

                addImagesUrlOnline(picUrl, resultado);
                for (String url : picUrl) {
                    DefaultSliderView defaultSliderView = new DefaultSliderView(getApplicationContext());


                    defaultSliderView
                            .image(url)
                            .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                            .setOnSliderClickListener(onSliderClickListener);

                    defaultSliderView.bundle(new Bundle());

                    defaultSliderView.getBundle()
                            .putString("extra", url);

                    sliderLayout.addSlider(defaultSliderView);
                }


                /*avi.hide();*/
            }
        });

        prodNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(DetailActivity.this);
                dialog.setContentView(R.layout.popup_desc);
                dialog.show();
                prodDescTextView = (TextView) dialog.findViewById(R.id.detail_desc);
                mlDetailController.getDescriptionWithID(productId, new ResultListener<MLDescription>() {
                    @Override
                    public void finish(MLDescription resultado) {
                        prodDescTextView.setText(resultado.getPlainText());
                    }
                });
            }
        });


    }

    public void addImagesUrlOnline(List<String> picsUrl, MlProdDetail prodDetail) {

        for (Picture picture : prodDetail.getPictures()) {
            picsUrl.add(picture.getUrl());
        }
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
