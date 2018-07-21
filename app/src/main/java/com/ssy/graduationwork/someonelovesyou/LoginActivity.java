package com.ssy.graduationwork.someonelovesyou;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button button_signup, button_login;
    EditText editText_phone, getEditText_phone_pw;
    CheckBox chk_auto;
    SharedPreferences setting;
    SharedPreferences.Editor editor;
    String loginId, loginPwd;



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
        chk_auto=findViewById(R.id.chk_auto);

        setting=getSharedPreferences("setting",0);
        editor=setting.edit();

        if(setting.getBoolean("chk_auto",false)){
            editText_phone.setText(setting.getString("ID",""));
            getEditText_phone_pw.setText(setting.getString("PW", ""));
            chk_auto.setChecked(true);
            Intent MainIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(MainIntent);

        }


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
                if(chk_auto.isChecked()){
                    Toast.makeText(getApplicationContext(),"로그인",Toast.LENGTH_SHORT).show();
                    String ID=editText_phone.getText().toString();
                    String PW=getEditText_phone_pw.getText().toString();

                    editor.putString("ID",ID);
                    editor.putString("PW",PW);
                    editor.putBoolean("chk_auto",true);
                    editor.commit();
                    Intent MainIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(MainIntent);


                }
                else{
                    editor.clear();
                    editor.commit();
                    Intent MainIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(MainIntent);
                }

            }
        });





    }



}
