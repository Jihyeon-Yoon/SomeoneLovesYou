package com.ssy.graduationwork.someonelovesyou;

import android.Manifest;
import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;

import com.kakao.usermgmt.response.model.User;
import com.ssy.graduationwork.someonelovesyou.Object.UserVO;
import com.ssy.graduationwork.someonelovesyou.Request.GetUserDTO;
import com.ssy.graduationwork.someonelovesyou.Retrofit.RetroCallback;
import com.ssy.graduationwork.someonelovesyou.Retrofit.RetroClient;

import org.json.JSONObject;

import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {
         private RetroClient retroClient;
     final static String LOG = "MainActivity";
     public UserVO user;

    @Override
    protected void onResume() {

        GetUserDTO dto = new GetUserDTO();
        dto.setUserid("01022345690");

        retroClient.getUSer(dto, new RetroCallback() {

            /*
            응답 오류
             */
            @Override
            public void onError(Throwable t) {
                Log.d(LOG, "에러 : " + t.toString());
            }

            @Override
            public void onSuccess(int code, Object receivedData) {
                try {
                    Log.d(LOG, "성공");

                    ResponseBody body = ((ResponseBody) receivedData);
                    String responseJSON = body.string();
                    //responseJSON JSON을 분석해서 처리하는 코드 작성.

                            /*
                            로그인인 경우에는 result로 토큰을 받음
                            이 토큰은 사용자를 인증하는데 사용되므로 반드시 저장해야함. Preference 사용하면 좋을듯?
                             */
                    Log.d(LOG,responseJSON);
                    JSONObject jObj1 = new JSONObject(responseJSON);
                    JSONObject jObj2 = jObj1.getJSONObject("results");
                    String userid = jObj2.getString("userid");
                    String userpwd = jObj2.getString("userpwd");
                    String state = jObj2.getString("state");
                    String emotion = jObj2.getString("emotion");
                    String username = jObj2.getString("username");
                    UserVO user = new UserVO(userid,userpwd,state,emotion,username);

                    Log.d("userTest",userid);

                    loadFragment(new Friend());



                }catch(Exception e){}
            }

            @Override
            public void onFailure(int code) {
                Log.d(LOG,"실패");
            }
        });

        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getHashKey();
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 100);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        }

        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            String name=extras.getString("id");
            Log.d("maintest",name);
        }



        // 타이틀바 가운데 정렬
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_layout);


        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);


        retroClient = RetroClient.getInstance(this).createBaseApi();

    }

    private boolean loadFragment(Fragment fragment) {
        if(fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();

            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;

        switch(item.getItemId()) {

            case R.id.navigation_friend:
                fragment = new Friend();
                //((Friend)(fragment)).setUser(user);
                break;

            case R.id.navigation_heart:
                fragment = new Heart();
                break;

            case R.id.navigation_plant:
                fragment = new Plant();
                break;

            case R.id.navigation_music:
                fragment = new Music();
                break;
        }

        return loadFragment(fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int curId = item.getItemId();
        Intent intent = null;

        switch(curId) {
            case R.id.modify_menu:
                intent = new Intent (getApplicationContext(),  Modify_Information.class);
                break;
            case R.id.menu_copyright:
                intent = new Intent (getApplicationContext(), CopyrightActivity.class);
                break;
            default:
                break;
        }

        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }


    private void getHashKey(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
    }

}
