package com.example.myelectronics.RecyclerViews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myelectronics.R;
import com.example.myelectronics.database.OrmProduct;

import java.util.List;
import java.util.stream.Collectors;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    Context context;
    private List<String> categoriesTitles;
    private List<OrmProduct> productData;

    public ProductAdapter(Context context, List<String> categoriesTitles, List<OrmProduct> productData) {
        this.context = context;
        this.categoriesTitles = categoriesTitles;
        this.productData = productData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        List<OrmProduct> filteredArray;
        filteredArray = productData.stream()
                .filter(product -> product.getCategory().toLowerCase().contains(categoriesTitles.get(position).toLowerCase()))
                .collect(Collectors.toList());
        SubProductAdapter subProductAdapter = new SubProductAdapter(context, filteredArray, categoriesTitles.get(position));
        holder.subRecyclerView.setAdapter(subProductAdapter);
        holder.category.setText(categoriesTitles.get(position).toUpperCase());
    }

    @Override
    public int getItemCount() {
        return categoriesTitles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView subRecyclerView;
        ImageView arrow;
        TextView category;

        public ViewHolder(View itemView) {
            super(itemView);
            subRecyclerView = itemView.findViewById(R.id.subRecyclerView);
            arrow = itemView.findViewById(R.id.ForwardArrow);
            category = itemView.findViewById(R.id.CategoryTitle);
            LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext(),
                    LinearLayoutManager.HORIZONTAL, false);
            subRecyclerView.setLayoutManager(layoutManager);
        }
    }

}
