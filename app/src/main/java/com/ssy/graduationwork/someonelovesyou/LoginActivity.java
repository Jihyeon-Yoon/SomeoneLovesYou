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

    Button button_signup, button_login, button_findPassword;
    EditText editText_phone, getEditText_phone_pw;
    CheckBox chk_auto;
    SharedPreferences setting;
    SharedPreferences.Editor editor;

    String loginId, loginPwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




        button_signup = findViewById(R.id.button_signup);
        button_login = findViewById(R.id.button_login);
        button_findPassword = findViewById(R.id.button_findPassword);
        editText_phone = findViewById(R.id.editText_phone);
        getEditText_phone_pw = findViewById(R.id.editText_pw);
        chk_auto=findViewById(R.id.chk_auto);

        setting=getSharedPreferences("setting",0);
        editor=setting.edit();

        if(setting.getBoolean("chk_auto",false)){
            editText_phone.setText(setting.getString("ID",""));
            getEditText_phone_pw.setText(setting.getString("PW", ""));
            chk_auto.setChecked(false);
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

        button_findPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent findPasswordIntent = new Intent(getApplicationContext(), FindPassword.class);
                startActivity(findPasswordIntent);
            }
        });


        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chk_auto.isChecked()){
                   // Toast.makeText(getApplicationContext(),"로그인",Toast.LENGTH_SHORT).show();
                    String ID=editText_phone.getText().toString();
                    String PW=getEditText_phone_pw.getText().toString();
                    if((!ID.equals("01022345690")&&!ID.equals("01050345566"))||(!PW.equals("ah4023")&&!PW.equals("bk1243"))){
                        Toast toast= Toast.makeText(getApplicationContext(),"해당 ID가 존재하지 않습니다",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else if(ID.equals("01022345690")&&PW.equals("ah4023")){
                        editor.putString("ID",ID);
                        editor.putString("PW",PW);
                        editor.putBoolean("chk_auto",false);
                        editor.commit();
                        Intent MainIntent = new Intent(getApplicationContext(), MainActivity.class);
                        MainIntent.putExtra("id","01022345690");
                        startActivity(MainIntent);
                    }
                    else if(ID.equals("01050345566")&&PW.equals("bk1243")){
                        editor.putString("ID",ID);
                        editor.putString("PW",PW);
                        editor.putBoolean("chk_auto",false);
                        editor.commit();
                        Intent MainIntent = new Intent(getApplicationContext(), MainActivity.class);
                        MainIntent.putExtra("id","01050345566");
                        startActivity(MainIntent);

                    }
                    else{
                        Toast toast= Toast.makeText(getApplicationContext(),"비밀번호가 틀립니다",Toast.LENGTH_SHORT);
                        toast.show();
                    }

                }
                else{
                   // Toast.makeText(getApplicationContext(),"로그인",Toast.LENGTH_SHORT).show();
                    String ID=editText_phone.getText().toString();
                    String PW=getEditText_phone_pw.getText().toString();
                    if((!ID.equals("01022345690")&&!ID.equals("01050345566"))||(!PW.equals("ah4023")&&!PW.equals("bk1243"))){
                        Toast toast= Toast.makeText(getApplicationContext(),"해당 ID가 존재하지 않습니다",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else if(ID.equals("01022345690")&&PW.equals("ah4023")){
                        editor.putString("ID",ID);
                        editor.putString("PW",PW);
                        editor.putBoolean("chk_auto",false);
                        editor.commit();
                        Intent MainIntent = new Intent(getApplicationContext(), MainActivity.class);
                        MainIntent.putExtra("id","01022345690");
                        startActivity(MainIntent);
                    }
                    else if(ID.equals("01050345566")&&PW.equals("bk1243")){
                        editor.putString("ID",ID);
                        editor.putString("PW",PW);
                        editor.putBoolean("chk_auto",false);
                        editor.commit();
                        Intent MainIntent = new Intent(getApplicationContext(), MainActivity.class);
                        MainIntent.putExtra("id","01050345566");
                        startActivity(MainIntent);

                    }
                    else{
                        Toast toast= Toast.makeText(getApplicationContext(),"비밀번호가 틀립니다",Toast.LENGTH_SHORT);
                        toast.show();
                    }

                }

            }
        });


    }


}