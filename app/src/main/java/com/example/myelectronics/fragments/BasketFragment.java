package com.example.myelectronics.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myelectronics.MyApp;
import com.example.myelectronics.R;
import com.example.myelectronics.RecyclerViews.BasketAdapter;
import com.example.myelectronics.database.ORMDatabase;
import com.example.myelectronics.database.OrmBasket;
import com.example.myelectronics.database.OrmUser;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BasketFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BasketFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View rootView;
    private ORMDatabase db;
    private List<OrmBasket> basketsList;
    private double total = 0;

    public BasketFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BasketFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BasketFragment newInstance(String param1, String param2) {
        BasketFragment fragment = new BasketFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_basket, container, false);
        // Inflate the layout for this fragment
        db = ((MyApp) getActivity().getApplication()).getORMDatabase();
        setup();
        return rootView;
    }

    void setup() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("UserData", "");
        OrmUser userData = gson.fromJson(json, OrmUser.class);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                basketsList = db.BasketDao().GetBasketById(userData.getId());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (basketsList.size() == 0) {
                            MaterialCardView materialCardView = (MaterialCardView) rootView.findViewById(R.id.BasketPurchaseCardView);
                            materialCardView.setVisibility(View.INVISIBLE);
                        }
                        TextView totalPrice = (TextView) rootView.findViewById(R.id.BasketTotalPriceTextView);
                        for (OrmBasket item : basketsList) {
                            total += item.getTotal_price();
                        }
                        totalPrice.setText("Total Price " + String.valueOf(total) + " OMR");
                        RecyclerView basketRecyclerView = (RecyclerView) rootView.findViewById(R.id.BasketRecyclerView);
                        BasketAdapter basketAdapter = new BasketAdapter(rootView.getContext(), getActivity(), userData.getId(), basketsList, total);
                        basketRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        basketRecyclerView.setAdapter(basketAdapter);
                    }
                });
            }
        });
        thread.start();
    }


}