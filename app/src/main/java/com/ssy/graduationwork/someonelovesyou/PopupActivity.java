package com.ssy.graduationwork.someonelovesyou;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;

public class PopupActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // setTheme(android.R.style.Theme_NoTitleBar);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);

        //setTitle("회원 상태 변경");
        setContentView(R.layout.activity_popup);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }






}
