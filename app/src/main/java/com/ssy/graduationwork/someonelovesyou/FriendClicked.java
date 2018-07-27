package com.ssy.graduationwork.someonelovesyou;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FriendClicked extends AppCompatActivity {
    private  int img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_clicked);

        Intent intent =getIntent();

        ImageView profile=(ImageView)findViewById(R.id.img_prof);
        TextView name=findViewById(R.id.friend_clicked_name);
        TextView phone=findViewById(R.id.friend_clicked_phone);

        img=Integer.parseInt(intent.getStringExtra("profile"));
        profile.setImageResource(img);
        name.setText(intent.getStringExtra("name"));
       phone.setText(intent.getIntExtra("phone",1));

    }
}
