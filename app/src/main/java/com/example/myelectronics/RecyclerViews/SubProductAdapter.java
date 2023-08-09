package com.example.myelectronics.RecyclerViews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myelectronics.MyApp;
import com.example.myelectronics.R;
import com.example.myelectronics.database.ORMDatabase;
import com.example.myelectronics.database.OrmBasket;
import com.example.myelectronics.database.OrmProduct;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

public class SubProductAdapter extends RecyclerView.Adapter<SubProductAdapter.ViewHolder> {

    Context context;
    List<OrmProduct> data;
    int counter = 1;
    FragmentActivity fragmentActivity;
    int type;
    private int userID;
    private ORMDatabase db;

    public SubProductAdapter(Context context, List<OrmProduct> data, FragmentActivity fragmentActivity, int userID, int type) {
        this.context = context;
        this.data = data;
        this.fragmentActivity = fragmentActivity;
        this.userID = userID;
        this.type = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (type == 1) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_filter_items, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_product_list_item, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubProductAdapter.ViewHolder holder, int position) {
        holder.productImage.setImageResource(data.get(position).getImage());
        holder.productName.setText(data.get(position).getProductName());
        holder.productPrice.setText(String.valueOf(data.get(position).getProductPrice()) + " OMR");
        holder.productDescription.setText(data.get(position).getProductDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Example of how to show the bottom sheet
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
                bottomSheetDialog.setContentView(R.layout.bottom_sheet_layout);
                ImageView productImg = bottomSheetDialog.findViewById(R.id.DetailsProductImage);
                TextView productTitle = bottomSheetDialog.findViewById(R.id.DetailsProductTitle);
                TextView productDesc = bottomSheetDialog.findViewById(R.id.DetailsProductDescription);
                TextView productPrice = bottomSheetDialog.findViewById(R.id.DetailsProductPrice);
                EditText quantity = bottomSheetDialog.findViewById(R.id.QuantityEditText);
                productImg.setImageResource(data.get(position).getImage());
                productTitle.setText(data.get(position).getProductName());
                productDesc.setText(data.get(position).getProductDescription());
                productPrice.setText(String.valueOf(data.get(position).getProductPrice()) + " OMR");
                quantity.setText("1");
                db = ((MyApp) fragmentActivity.getApplication()).getORMDatabase();


                Button plusBtn = bottomSheetDialog.findViewById(R.id.PlusBtn);
                Button minusBtn = bottomSheetDialog.findViewById(R.id.MinusBtn);
                Button purchaseBtn = bottomSheetDialog.findViewById(R.id.PurchaseBtn);

                plusBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        counter++;
                        quantity.setText(String.valueOf(counter));
                    }
                });

                minusBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (counter != 1) {
                            counter--;
                            quantity.setText(String.valueOf(counter));
                        }
                    }
                });

                purchaseBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                db.BasketDao().AddBasket(new OrmBasket(data.get(position).getProductId(), counter, (data.get(position).getProductPrice() * counter), userID));
                                bottomSheetDialog.dismiss();
                            }
                        });
                        thread.start();
                    }
                });

                bottomSheetDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void updateData(List<OrmProduct> newData) {
        data = newData;
        notifyDataSetChanged();
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
