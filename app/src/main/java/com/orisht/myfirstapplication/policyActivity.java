package com.orisht.myfirstapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;


public class policyActivity extends AppCompatActivity {

    Button ok;
    Context context;
    CheckBox checkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy);
        initViews();
    }

    private void initViews() {
        ok = findViewById(R.id.buttonOK);
        checkbox = findViewById(R.id.checked);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                if (checkbox.isChecked()) {
                    resultIntent.putExtra("checkbox_checked", true);
                } else {
                    resultIntent.putExtra("checkbox_checked", false);
                }
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}




