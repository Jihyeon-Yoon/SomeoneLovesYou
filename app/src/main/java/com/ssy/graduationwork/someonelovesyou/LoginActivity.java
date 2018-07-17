package com.ssy.graduationwork.someonelovesyou;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    Button button_signup, button_login;
    EditText editText_phone, getEditText_phone_pw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 타이틀바 가운데 정렬
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_layout);

        button_signup = findViewById(R.id.button_signup);
        button_login = findViewById(R.id.button_login);
        editText_phone = findViewById(R.id.editText_phone);
        getEditText_phone_pw = findViewById(R.id.editText_pw);
        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUpIntent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(signUpIntent);
            }
        });
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input_phone = editText_phone.getText().toString();
                String input_pw = getEditText_phone_pw.getText().toString();
                if (input_phone.equalsIgnoreCase("")) {
                    // Toast.makeText(this,"d",Toast.LENGTH_SHORT).show();
                    //  Toast.makeText(this, "폰번호 입력하세요", Toast.LENGTH_SHORT).show();
                } else if (input_pw.equalsIgnoreCase("")) {
                    //    Toast.makeText(this, "비밀번호 입력하세요", Toast.LENGTH_SHORT).show();
                }

                Intent MainIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(MainIntent);
            }
        });
        // button_signup.setOnClickListener(this);
        //  button_login.setOnClickListener(this);

    }



}
