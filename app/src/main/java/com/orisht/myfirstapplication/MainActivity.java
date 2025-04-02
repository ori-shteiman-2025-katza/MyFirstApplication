package com.orisht.myfirstapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener { //implements View.OnClickListener

   Button b1,b2,LinearBtn,LinerBtn2,policy,settings;
   TextView et1;
   Context context;
   Switch sw;
   SeekBar sb;
   SeekBar sb2;
    ImageView iv;
    ImageView iv2;
    int START_GAME,start_policy;
   ConstraintLayout constraintLayout;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        context=this;

        // יצירת SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        // שמירת נתונים
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", "user123");
        editor.putInt("age", 25);
        editor.putBoolean("isLoggedIn", true);
        editor.apply();  // שמירה אסינכרונית

        // קריאת נתונים
        String username = sharedPreferences.getString("username", "defaultUsername");
        int age = sharedPreferences.getInt("age", 0);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        // הדפסת הנתונים
        Log.d("SharedPreferences", "Username: " + username);
        Log.d("SharedPreferences", "Age: " + age);
        Log.d("SharedPreferences", "Is Logged In: " + isLoggedIn);
}


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == START_GAME) {
            if (resultCode == RESULT_OK) {
                int i = data.getIntExtra("num_guesses", -1);
                  String str=data.getStringExtra("user_name");
                  Toast.makeText(this,"game finished counter="+i+"user name"+str,Toast.LENGTH_LONG).show();
            }
        }
        if (requestCode == start_policy) {

        }
    }



    private void initViews() {
        constraintLayout = findViewById(R.id.constraintLayout);
        b1 = findViewById(R.id.btn1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "btn1", Toast.LENGTH_SHORT).show();

            }
        });


        b2=findViewById(R.id.btn2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "btn2", Toast.LENGTH_SHORT).show();
            }
        });
   sw=findViewById(R.id.switch1);
   sw.setOnCheckedChangeListener(this);
        iv=(ImageView)findViewById(R.id.iv);
        iv2=(ImageView)findViewById(R.id.iv2);
   sb=findViewById(R.id.sb);
        sb.setProgress(100);

   sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
       @Override
       public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
           float alpha = (float)i/100;
           iv.setAlpha(alpha);
           float alpha1=(float) 1-alpha;
           iv2.setAlpha(alpha1);

       }

       @Override
       public void onStartTrackingTouch(SeekBar seekBar) {

       }

       @Override
       public void onStopTrackingTouch(SeekBar seekBar) {

       }
   });
        sb2=findViewById(R.id.sb);
        sb2.setProgress(0);
;
LinerBtn2=findViewById(R.id.linearBtn2);
LinerBtn2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent= new Intent(context , gameActivity.class);
        // startActivity(intent);

        startActivityForResult(intent,START_GAME);
    }
});
settings=findViewById(R.id.settings);
settings.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent= new Intent(context, SettingsActivity.class);
        startActivity(intent);
    }
});

        policy=findViewById(R.id.policy);
        policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, policyActivity.class);
                startActivityForResult(intent,start_policy);
            }
        });

LinearBtn=findViewById(R.id.linearBtn);
LinearBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent= new Intent(context , LinearActivity.class);
        startActivity(intent);
    }
});


    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            if(b)
                constraintLayout.setBackgroundColor(Color.WHITE);
            else
                constraintLayout.setBackgroundColor(Color.BLACK);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();
        if(id == R.id.action_login){
            Toast.makeText(this, "you selected login", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.action_register){
            Toast.makeText(this, "you selected register", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.action_start){
            Toast.makeText(this, "you selected start", Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId() == R.id.new_item) {
            Intent intent = new Intent(this, NewActivity.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}





    //@Override
    //public void onClick(View view) {
        //if(view==b1) {
            //Toast.makeText(context, "btn1", Toast.LENGTH_SHORT).show();
        //}
        //if(view==b2){
            //Toast.makeText(context, "btn2", Toast.LENGTH_SHORT).show();
    //}
    //public void func(View view) {
         //if (view == b1) {
             //Log.d("ori", "murderr");
             //et1.setText("murderr");
         //}
       //else{
             //Log.d("ori", "hider");
             //et1.setText("hider");

     //}
    //public void aaaaa(View view) {
        //if(view.getId()==R.id.btn1){
            //Toast.makeText(this, "btn1", Toast.LENGTH_SHORT).show();
       // }
       // if(view.getId()==R.id.btn2)
            //Toast.makeText(this, "btn2", Toast.LENGTH_SHORT).show();




