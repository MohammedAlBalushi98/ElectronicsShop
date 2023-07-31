package com.example.myelectronics.RecyclerViews;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainProductModel {
    private int arrowIcon;
    private ArrayList<RecyclerView> recyclerViewsList;
    private String category;

    public MainProductModel(int arrowIcon, ArrayList<RecyclerView> recyclerViewsList, String category) {
        this.arrowIcon = arrowIcon;
        this.recyclerViewsList = recyclerViewsList;
        this.category = category;
    }

    public int getArrowIcon() {
        return arrowIcon;
    }

    public void setArrowIcon(int arrowIcon) {
        this.arrowIcon = arrowIcon;
    }

    public ArrayList<RecyclerView> getRecyclerViewsList() {
        return recyclerViewsList;
    }

    public void setRecyclerViewsList(ArrayList<RecyclerView> recyclerViewsList) {
        this.recyclerViewsList = recyclerViewsList;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
