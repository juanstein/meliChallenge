package com.example.melichallenge.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.example.melichallenge.R;
import com.example.melichallenge.controller.MLProductController;
import com.example.melichallenge.dao.MLProductDAO;
import com.example.melichallenge.model.MLProduct;
import com.example.melichallenge.util.ResultListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ProductAdapter.OnProductAdapterCalls {
    FloatingSearchView floatingSearchView;
    List<MLProduct> productList;
    RecyclerView recyclerView;
    MLProductController mlProductController;
    GridLayoutManager gridLayoutManager;
    ProductAdapter productAdapter;
    LinearLayout linearLayoutPlaceholder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycleViewProducts);
        linearLayoutPlaceholder = (LinearLayout) findViewById(R.id.placeHolderNoSearch);
        productList = new ArrayList<>();
        mlProductController = new MLProductController(getApplicationContext());
        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        productAdapter = new ProductAdapter(productList,getApplicationContext(), this);
        recyclerView.setAdapter(productAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);
        floatingSearchView = (FloatingSearchView) findViewById(R.id.floating_search_view);
        floatingSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {

            }

            @Override
            public void onSearchAction(String currentQuery) {
                gridLayoutManager.scrollToPositionWithOffset(0,0);
                if (currentQuery.length() > 0 ){
                    mlProductController.getProductsWithQuery(currentQuery, new ResultListener<List<MLProduct>>() {
                        @Override
                        public void finish(List<MLProduct> resultado) {
                            productList.clear();
                            productList.addAll(resultado);
                            productAdapter.notifyDataSetChanged();
                            linearLayoutPlaceholder.setVisibility(View.INVISIBLE);
                        }
                    });
                }
            }
        });

        MLProductDAO mlProductDAO = new MLProductDAO(getApplicationContext());

        mlProductDAO.getProductListWithQuery("iphone", new ResultListener<List<MLProduct>>() {
            @Override
            public void finish(List<MLProduct> resultado) {
            }
        });
    }

    @Override
    public void getNotifications(MLProduct product) {
        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
        Bundle unBundle = new Bundle();
        unBundle.putString(DetailActivity.PRODUCTID, product.getID());
        intent.putExtras(unBundle);
        startActivity(intent);
    }
}
