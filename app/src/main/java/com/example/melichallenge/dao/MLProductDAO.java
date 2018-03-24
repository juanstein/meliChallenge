package com.example.melichallenge.dao;

import android.content.Context;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.melichallenge.model.MLProduct;
import com.example.melichallenge.model.MLProductsContainer;
import com.example.melichallenge.model.MlProdDetail;
import com.example.melichallenge.util.ResultListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juan on 20/03/2018.
 */

public class MLProductDAO {
    private Context context;

    public MLProductDAO(Context context) {
        this.context = context;
    }

    public void getProductListWithQuery(String query, final ResultListener<List<MLProduct>> listener){

        AndroidNetworking.initialize(context);

        final Gson gson = new Gson();

        AndroidNetworking.get("https://api.mercadolibre.com/sites/MLA/search?q=" + query)
                .build().getAsString(new StringRequestListener() {
            @Override
            public void onResponse(String response) {
                MLProductsContainer MLProductsContainer = gson.fromJson(response, MLProductsContainer.class);
                listener.finish(MLProductsContainer.getResults());
            }

            @Override
            public void onError(ANError anError) {
            }
        });

    }

    public void getProductWithID(String id, final ResultListener<MlProdDetail> listener){

        AndroidNetworking.initialize(context);

        final Gson gson = new Gson();

        AndroidNetworking.get("https://api.mercadolibre.com/items/" + id)
                .build().getAsString(new StringRequestListener() {
            @Override
            public void onResponse(String response) {
                MlProdDetail mlProdDetail = gson.fromJson(response, MlProdDetail.class);
                listener.finish(mlProdDetail);
            }

            @Override
            public void onError(ANError anError) {
            }
        });

    }
}
