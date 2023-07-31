package com.example.myelectronics.RecyclerViews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myelectronics.OrmProduct;
import com.example.myelectronics.R;

import java.util.List;

public class SubProductAdapter extends RecyclerView.Adapter<SubProductAdapter.ViewHolder> {

    Context context;
    List<OrmProduct> data;
    String categoryTitle;

    public SubProductAdapter(Context context, List<OrmProduct> data, String categoryTitle) {
        this.context = context;
        this.data = data;
        this.categoryTitle = categoryTitle;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_product_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubProductAdapter.ViewHolder holder, int position) {
        holder.productImage.setImageResource(data.get(position).getImage());
        holder.productName.setText(data.get(position).getProductName());
        holder.productPrice.setText(String.valueOf(data.get(position).getProductPrice()) + " OMR");
        holder.productDescription.setText(data.get(position).getProductDescription());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView productPrice;
        TextView productDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.ProductImage);
            productName = itemView.findViewById(R.id.ProductTitle);
            productPrice = itemView.findViewById(R.id.ProductPrice);
            productDescription = itemView.findViewById(R.id.ProductDesc);
        }
    }


}
