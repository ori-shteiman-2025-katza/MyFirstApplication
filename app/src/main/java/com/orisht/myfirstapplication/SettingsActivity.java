package com.orisht.myfirstapplication;

import android.media.MediaPlayer;
import android.os.Bundle;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private Switch musicSwitch;
    private Switch notificationSwitch;

    private EditText usernameEditText;
    private SeekBar textSizeSeekBar;
    private Button saveButton;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        notificationSwitch = findViewById(R.id.notification_switch);
        usernameEditText = findViewById(R.id.username_edit_text);
        textSizeSeekBar = findViewById(R.id.text_size_seekbar);
        saveButton = findViewById(R.id.save_button);

        // קריאה להעדפות משותפות
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        // הגדרת הערכים מה-SharedPreferences
        notificationSwitch.setChecked(sharedPreferences.getBoolean("notificationsEnabled", false));
        usernameEditText.setText(sharedPreferences.getString("username", ""));
        textSizeSeekBar.setProgress(sharedPreferences.getInt("textSize", 14));

        // שמירת ההגדרות כאשר הכפתור נלחץ
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("notificationsEnabled", notificationSwitch.isChecked());
                editor.putString("username", usernameEditText.getText().toString());
                editor.putInt("textSize", textSizeSeekBar.getProgress());
                editor.apply(); // שמירה אסינכרונית

                Toast.makeText(SettingsActivity.this, "Settings Saved", Toast.LENGTH_SHORT).show();
            }
        });

        Switch musicSwitch = findViewById(R.id.music_switch);
        musicSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // הפעל מוזיקה
                    playMusic();
                } else {
                    // עצור את המוזיקה
                    stopMusic();
                }
            }
        });
    }
        private void playMusic() {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this, R.raw.lebron); // השתמש בשם הקובץ שלך
            }
            mediaPlayer.start();
        }

        private void stopMusic() {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.pause();  // או stop()
            }
        }

    }

