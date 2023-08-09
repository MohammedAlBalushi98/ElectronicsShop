package com.example.myelectronics.RecyclerViews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myelectronics.MyApp;
import com.example.myelectronics.R;
import com.example.myelectronics.database.ORMDatabase;
import com.example.myelectronics.database.OrmBasket;
import com.example.myelectronics.database.OrmProduct;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ViewHolder> {


    List<OrmBasket> basketItems;
    FragmentActivity fragmentActivity;
    int userID;
    int counter = 1;
    Context context;
    List<OrmProduct> productList = new ArrayList<>();
    double changeInPrice = 0;
    double total;
    private ORMDatabase db;

    public BasketAdapter(Context context, FragmentActivity fragmentActivity, int userID, List<OrmBasket> basketItems, double total) {
        this.fragmentActivity = fragmentActivity;
        this.userID = userID;
        this.context = context;
        this.basketItems = basketItems;
        this.total = total;
    }

    @NonNull
    @Override
    public BasketAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.basket_list_item, parent, false);
        db = ((MyApp) fragmentActivity.getApplication()).getORMDatabase();
//        basketItems = db.BasketDao().GetBasketById(userID);
        return new BasketAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BasketAdapter.ViewHolder holder, int position) {
        TextView totalPrice = (TextView) fragmentActivity.findViewById(R.id.BasketTotalPriceTextView);
        new Thread(new Runnable() {
            @Override
            public void run() {
                OrmProduct product = db.ProductDao().GetProductById(basketItems.get(position).getProductId());
                productList.add(product);
                fragmentActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        double productPrice = productList.get(position).getProductPrice();
                        int quantity = basketItems.get(position).getQuantity();
                        changeInPrice = productPrice * quantity;
                        holder.productImage.setImageResource(productList.get(position).getImage());
                        holder.productQuantity.setText(String.valueOf(basketItems.get(position).getQuantity()));
                        holder.productPrice.setText(String.format("%.2f", basketItems.get(position).getTotal_price()) + " OMR");
                        holder.productName.setText(productList.get(position).getProductName());
                        counter = basketItems.get(position).getQuantity();
                    }
                });
            }
        }).start();


        holder.plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                total = total + productList.get(position).getProductPrice();
                holder.productQuantity.setText(String.valueOf(counter));
                holder.productPrice.setText(String.format("%.2f", counter * productList.get(position).getProductPrice()) + " OMR");
                totalPrice.setText("Total Price " + String.format("%.2f", total) + " OMR");
            }
        });

        holder.minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter--;
                total = total - productList.get(position).getProductPrice();
                holder.productQuantity.setText(String.valueOf(counter));
                holder.productPrice.setText(String.format("%.2f", counter * productList.get(position).getProductPrice()) + " OMR");
                totalPrice.setText("Total Price " + String.format("%.2f", total) + " OMR");
                if (counter == 0) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            int msgflag = db.BasketDao().RemoveBasket(basketItems.get(position));
                            fragmentActivity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (basketItems.size() == 0) {
                                        MaterialCardView materialCardView = (MaterialCardView) fragmentActivity.findViewById(R.id.BasketPurchaseCardView);
                                        materialCardView.setVisibility(View.INVISIBLE);
                                    }
                                    basketItems.remove(position);
                                    notifyItemRemoved(position);
                                    if (msgflag == 1) {
                                        Toast.makeText(context, "Removed Successfully", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }).start();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return basketItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        Button minusBtn, plusBtn;
        TextView productName, productPrice, productQuantity;
        ImageView productImage;

        public ViewHolder(View itemView) {
            super(itemView);
            minusBtn = itemView.findViewById(R.id.BasketMinusButton);
            plusBtn = itemView.findViewById(R.id.BasketPlusButton);
            productName = itemView.findViewById(R.id.BasketProductNameTextView);
            productPrice = itemView.findViewById(R.id.BasketPriceTextView);
            productQuantity = itemView.findViewById(R.id.BasketQuantityTextView);
            productImage = itemView.findViewById(R.id.BasketProductImageView);
        }
    }


}
