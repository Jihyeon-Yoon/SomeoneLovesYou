package com.ssy.graduationwork.someonelovesyou;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // 타이틀바 가운데 정렬
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_layout);

        final EditText edit_name=findViewById(R.id.editText_name);
        final EditText edit_phone=findViewById(R.id.editText_newPhone);
        final EditText edit_email=findViewById(R.id.editText_email);
        final EditText edit_pw=findViewById(R.id.editText_newPW);
        final EditText edit_pwCheck=findViewById(R.id.editText_checkPW);

        Button registerButton=findViewById(R.id.button_createAccount);



    }
}
