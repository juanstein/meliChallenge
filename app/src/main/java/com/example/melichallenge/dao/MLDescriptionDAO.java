package com.example.melichallenge.dao;

import android.content.Context;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.melichallenge.model.MLDescription;
import com.example.melichallenge.model.MlProdDetail;
import com.example.melichallenge.util.ResultListener;
import com.google.gson.Gson;

/**
 * Created by Juan on 22/03/2018.
 */

public class MLDescriptionDAO {
    private Context context;

    public MLDescriptionDAO(Context context) {
        this.context = context;
    }

    public void getDescriptionWithID(String id, final ResultListener<MLDescription> listener){

        AndroidNetworking.initialize(context);

        final Gson gson = new Gson();

        AndroidNetworking.get("https://api.mercadolibre.com/items/"+ id +"/description")
                .build().getAsString(new StringRequestListener() {
            @Override
            public void onResponse(String response) {
                MLDescription mlDescription = gson.fromJson(response, MLDescription.class);
                listener.finish(mlDescription);
            }

            @Override
            public void onError(ANError anError) {
            }
        });

    }
}