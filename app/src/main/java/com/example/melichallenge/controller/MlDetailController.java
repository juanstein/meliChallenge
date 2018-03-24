package com.example.melichallenge.controller;

import android.content.Context;

import com.example.melichallenge.dao.MLDescriptionDAO;
import com.example.melichallenge.dao.MLProductDAO;
import com.example.melichallenge.model.MLDescription;
import com.example.melichallenge.model.MlProdDetail;
import com.example.melichallenge.util.ResultListener;

/**
 * Created by Juan on 23/03/2018.
 */

public class MlDetailController {
    private Context context;

    public MlDetailController(Context context) {
        this.context = context;
    }

    public void getDescriptionWithID(String ID, final ResultListener<MLDescription> resultListener){
        MLDescriptionDAO mlDescriptionDAO = new MLDescriptionDAO(context);
        mlDescriptionDAO.getDescriptionWithID(ID, new ResultListener<MLDescription>() {
            @Override
            public void finish(MLDescription resultado) {
                resultListener.finish(resultado);
            }
        });
    }
}
