package com.example.myelectronics.RecyclerViews;

import static android.content.Context.MODE_PRIVATE;
import static androidx.core.app.ActivityCompat.recreate;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myelectronics.R;

import java.util.List;

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.ViewHolder> {

    private static final String THEME_PREFERENCES = "themePrefs";
    private static final String THEME_KEY = "currentTheme";
    FragmentActivity fragmentActivity;
    List<String> settingsTitles;

    public SettingsAdapter(List<String> settingsTitles, FragmentActivity fragmentActivity) {
        this.settingsTitles = settingsTitles;
        this.fragmentActivity = fragmentActivity;
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
                        toggleTheme();
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

    private void toggleTheme() {
        SharedPreferences sharedPreferences = fragmentActivity.getSharedPreferences(THEME_PREFERENCES, MODE_PRIVATE);
        int currentTheme = sharedPreferences.getInt(THEME_KEY, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

        if (currentTheme == AppCompatDelegate.MODE_NIGHT_YES) {
            currentTheme = AppCompatDelegate.MODE_NIGHT_NO; // Light mode
        } else {
            currentTheme = AppCompatDelegate.MODE_NIGHT_YES; // Dark mode
        }

        AppCompatDelegate.setDefaultNightMode(currentTheme);
        sharedPreferences.edit().putInt(THEME_KEY, currentTheme).apply();

        recreate(fragmentActivity); // Apply the new theme without restarting the activity
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        Button settingsBtn;

        public ViewHolder(View itemView) {
            super(itemView);
            settingsBtn = itemView.findViewById(R.id.settingsTitleTextButton);
        }
    }

}
