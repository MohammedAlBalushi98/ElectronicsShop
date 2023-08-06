package com.example.myelectronics.RecyclerViews;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myelectronics.R;

import java.util.List;

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.ViewHolder> {

    List<String> settingsTitles;

    public SettingsAdapter(List<String> settingsTitles) {
        this.settingsTitles = settingsTitles;
    }


    @NonNull
    @Override
    public SettingsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_list_item, parent, false);
        return new SettingsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingsAdapter.ViewHolder holder, int position) {
        holder.settingsBtn.setText(settingsTitles.get(position));
        switch (settingsTitles.get(position)) {
            case "Change Language":
                holder.settingsBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("Settings", "Change Language");
                    }
                });
                break;
            case "Change Theme":
                holder.settingsBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("Settings", "Change Theme");
                    }
                });
                break;
            default:
                holder.settingsBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("Settings", "This Button is not available ");
                    }
                });
                break;
        }
    }


    @Override
    public int getItemCount() {
        return settingsTitles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        Button settingsBtn;

        public ViewHolder(View itemView) {
            super(itemView);
            settingsBtn = itemView.findViewById(R.id.settingsTitleTextButton);
        }
    }
}
