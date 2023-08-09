package com.example.myelectronics.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myelectronics.MyApp;
import com.example.myelectronics.R;
import com.example.myelectronics.RecyclerViews.ProductAdapter;
import com.example.myelectronics.RecyclerViews.SubProductAdapter;
import com.example.myelectronics.database.ORMDatabase;
import com.example.myelectronics.database.OrmProduct;
import com.example.myelectronics.database.OrmUser;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    List<OrmProduct> products = new ArrayList<>();
    List<OrmProduct> filteredProducts = new ArrayList<>();


    List<String> Titles = new ArrayList<>();
    Context context;
    View rootView;
    EditText searchEditText;
    RecyclerView productRecyclerView;
    private DrawerLayout drawerLayout;
    private ImageButton btnToggleDrawer;
    private ORMDatabase db;
    private ViewGroup viewGroup;
    private OrmUser userData;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        context = rootView.getContext();
        viewGroup = container;
        searchEditText = rootView.findViewById(R.id.HomeSearch);
        productRecyclerView = (RecyclerView) rootView.findViewById(R.id.HomeParentRecyclerView);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("UserData", "");
        userData = gson.fromJson(json, OrmUser.class);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String newText = editable.toString();
                filterDataBasedOnText(newText);
                if (newText.isEmpty()) {
                    ProductAdapter productAdapter = new ProductAdapter(context, Titles, products, getActivity(), userData.getId());
                    productRecyclerView.setAdapter(productAdapter);
                    productRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                } else {
                    SubProductAdapter subProductAdapter = new SubProductAdapter(context, filteredProducts, getActivity(), userData.getId(), 1);
                    subProductAdapter.updateData(filteredProducts);
                    productRecyclerView.setAdapter(subProductAdapter);
                    productRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
                }
            }
        });
        // Inflate the layout for this fragment
        setup();
        return rootView;
    }

    void setup() {
        db = ((MyApp) getActivity().getApplication()).getORMDatabase();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                products = db.ProductDao().GetAllProducts();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        ProductAdapter productAdapter = new ProductAdapter(context, Titles, products, getActivity(), userData.getId());
                        productRecyclerView.setAdapter(productAdapter);
                        productRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    }
                });
            }
        });
        thread.start();


        drawerLayout = rootView.findViewById(R.id.drawer_layout);
        View customDrawerView = LayoutInflater.from(context).inflate(R.layout.custom_drawer_layout, viewGroup, false);
        NavigationView navView = rootView.findViewById(R.id.navigation_view);
        navView.addHeaderView(customDrawerView);
        btnToggleDrawer = rootView.findViewById(R.id.btn_toggle_drawer);
        navView.getMenu().findItem(R.id.drawer_menu_profile).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                Toast.makeText(context, "Profile clicked", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        navView.getMenu().findItem(R.id.drawer_menu_logout).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                Toast.makeText(context, "Logout clicked", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        btnToggleDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if the drawer is open and toggle it accordingly
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
    }


    void initData() {

        for (OrmProduct product : products) {
            if (!Titles.contains(product.getCategory())) {
                Titles.add(product.getCategory());
            }
        }
    }

    private void filterDataBasedOnText(String newText) {
        filteredProducts.clear();
        for (OrmProduct item : products) {
            if (item.getProductName().toLowerCase().contains(newText.toLowerCase())) {
                filteredProducts.add(item);
            }
        }
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
//        menuInflater.inflate(R.menu.drawer_menu, menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        // Handle menu item clicks
//        if (R.id.drawer_menu_profile == id) {
//            // Perform the action for the Home menu item
//            Toast.makeText(context, "Profile clicked", Toast.LENGTH_SHORT).show();
//            return true;
//        } else if (R.id.drawer_menu_logout == id) {
//            // Perform the action for the Profile menu item
//            Toast.makeText(context, "Logout clicked", Toast.LENGTH_SHORT).show();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

}