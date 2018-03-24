package com.example.melichallenge.controller;

import android.content.Context;

import com.example.melichallenge.dao.MLProductDAO;
import com.example.melichallenge.model.MLProduct;
import com.example.melichallenge.model.MlProdDetail;
import com.example.melichallenge.util.ResultListener;

import java.util.List;

/**
 * Created by Juan on 21/03/2018.
 */

public class MLProductController {
    private Context context;

    public MLProductController(Context context) {
        this.context = context;
    }

    public void getProductsWithQuery(String query, final ResultListener<List<MLProduct>> resultListener){
        MLProductDAO mlProductDAO = new MLProductDAO(context);
        mlProductDAO.getProductListWithQuery(query, new ResultListener<List<MLProduct>>() {
            @Override
            public void finish(List<MLProduct> resultado) {
                resultListener.finish(resultado);
            }
        });
    }
    public void getProductWithID(String ID, final ResultListener<MlProdDetail> resultListener){
        MLProductDAO mlProductDAO = new MLProductDAO(context);
        mlProductDAO.getProductWithID(ID, new ResultListener<MlProdDetail>() {
            @Override
            public void finish(MlProdDetail resultado) {
                resultListener.finish(resultado);
            }
        });
    }
}
