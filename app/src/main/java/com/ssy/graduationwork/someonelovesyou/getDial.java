package com.ssy.graduationwork.someonelovesyou;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class getDial extends AppCompatActivity {
    private ArrayList<Map<String, String>> dataList;

    public String id, name;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 100);
        }







        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 100);
        }

        String [] arrProjection={
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME
        };

        String [] arrPhoneProjection={
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };

        ContentResolver cor=this.getContentResolver();
        Cursor clsCursor=null;
        clsCursor=cor.query(ContactsContract.Contacts.CONTENT_URI,
                arrProjection,
                ContactsContract.Contacts.HAS_PHONE_NUMBER+"=1" ,null,null);





        while(clsCursor.moveToNext()){
            String phone=clsCursor.getString(0);
            // Log.d("Unity","ID: "+clsCursor.getString(0));
            Log.d("Unity","이름: "+clsCursor.getString(1));


            Cursor clsPhoneCursor=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    arrPhoneProjection,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID+" = " +phone,null,null);

            while(clsPhoneCursor.moveToNext()){
                Log.d("Unity","번호: "+clsPhoneCursor.getString(0));

            }
            clsPhoneCursor.close();



        }




    }
}