package com.example.melichallenge.view;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.melichallenge.R;
import com.example.melichallenge.model.MLProduct;

import java.util.List;

/**
 * Created by Juan on 20/03/2018.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{


    private List<MLProduct> productList;

    private Context mContext;

    private OnProductAdapterCalls container;



    public ProductAdapter(List<MLProduct> productList, Context mContext, OnProductAdapterCalls container) {
        this.productList = productList;
        this.mContext = mContext;
        this.container = container;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setProductList(List<MLProduct> productList) {
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.prod_cell, parent, false);

        ProductViewHolder productViewHolder = new ProductViewHolder(itemView);
        return productViewHolder;
    }


    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        MLProduct product = productList.get(position);
        ProductViewHolder productViewHolder = (ProductViewHolder) holder;
        productViewHolder.bindProduct(product, mContext);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    public interface OnProductAdapterCalls{
        public void getNotifications(MLProduct product);
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewProductName;

        private ImageView imageViewProduct;
        private View productView;
        private TextView TextViewProductCost;

        public ProductViewHolder(View itemView) {
            super(itemView);
            textViewProductName = (TextView) itemView.findViewById(R.id.product_name);
            imageViewProduct = (ImageView) itemView.findViewById(R.id.product_image_view);
            TextViewProductCost = (TextView) itemView.findViewById(R.id.product_price);
            productView = itemView;


        }

        public void bindProduct(final MLProduct product, final Context context){

            textViewProductName.setText(product.getTitle());
            int a = (int) Math.round(product.getPrice());
            Integer price = new Integer(a);

            TextViewProductCost.setText(price.toString());


            String orSizeThumb = product.getThumbnail().replaceAll("I","O");


            Glide.with(context).load(orSizeThumb).into(imageViewProduct);

            productView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    container.getNotifications(product);
                }
            });



        }
    }

}
