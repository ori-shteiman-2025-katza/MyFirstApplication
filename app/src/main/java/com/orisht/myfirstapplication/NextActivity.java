package com.orisht.myfirstapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NextActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefs"; // שם של ה-SharedPreferences
    private EditText editText1, editText2;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_next);

        // הגדרת SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // הגדרת רכיבים UI
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);

        // קריאה לערכים ששמרנו ב-SharedPreferences
        String savedText1 = sharedPreferences.getString("text1", ""); // אם אין טקסט נשמור טקסט ריק
        String savedText2 = sharedPreferences.getString("text2", "");

        // הצגת הטקסטים שהוזנו קודם בשדות
        editText1.setText(savedText1);
        editText2.setText(savedText2);

        // פעולה עבור כפתור 1
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1 = editText1.getText().toString();
                String text2 = editText2.getText().toString();

                // שמירת הערכים ב-SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("text1", text1); // שמירה של טקסט 1
                editor.putString("text2", text2); // שמירה של טקסט 2
                editor.apply(); // שמירה

                // הצגת הודעת Toast עם הערכים
                Toast.makeText(NextActivity.this, "הכפתור 1 נלחץ! טקסט 1: " + text1 + ", טקסט 2: " + text2, Toast.LENGTH_SHORT).show();
            }
        });

        // פעולה עבור כפתור 2
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1 = editText1.getText().toString();
                String text2 = editText2.getText().toString();

                // שמירת הערכים ב-SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("text1", text1); // שמירה של טקסט 1
                editor.putString("text2", text2); // שמירה של טקסט 2
                editor.apply(); // שמירה

                // הצגת הודעת Toast עם הערכים
                Toast.makeText(NextActivity.this, "הכפתור 2 נלחץ! טקסט 1: " + text1 + ", טקסט 2: " + text2, Toast.LENGTH_SHORT).show();
            }
        });

        // טיפול ב-Insets עבור סורגים עליונים
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}