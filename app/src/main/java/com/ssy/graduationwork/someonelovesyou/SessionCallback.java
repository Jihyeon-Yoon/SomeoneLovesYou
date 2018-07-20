package com.ssy.graduationwork.someonelovesyou;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import com.kakao.auth.ISessionCallback;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;


public class SessionCallback implements ISessionCallback{

    // 로그인에 성공한 상태

    @Override

    public void onSessionOpened() {

        requestMe();

    }
    SessionCallback(){

    }




    // 로그인에 실패한 상태

    @Override

    public void onSessionOpenFailed(KakaoException exception) {

        Log.e("SessionCallback :: ", "onSessionOpenFailed : " + exception.getMessage());


    }



    // 사용자 정보 요청

     public void requestMe() {

        // 사용자정보 요청 결과에 대한 Callback

         UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {

             @Override

             public void onCompleteLogout() {

                 //Intent MainIntent = new Intent(.this, LoginActivity.class);
                // startActivity(MainIntent);

             }

         });


        UserManagement.getInstance().requestMe(new MeResponseCallback() {

            // 세션 오픈 실패. 세션이 삭제된 경우,

            @Override

            public void onSessionClosed(ErrorResult errorResult) {

                Log.e("SessionCallback :: ", "onSessionClosed : " + errorResult.getErrorMessage());

            }



            // 회원이 아닌 경우,

            @Override

            public void onNotSignedUp() {

                Log.e("SessionCallback :: ", "onNotSignedUp");

            }



            // 사용자정보 요청에 성공한 경우,

            @Override

            public void onSuccess(UserProfile userProfile) {



                Log.e("SessionCallback :: ", "onSuccess");



                String nickname = userProfile.getNickname();

                String email = userProfile.getEmail();

                String profileImagePath = userProfile.getProfileImagePath();

                String thumnailPath = userProfile.getThumbnailImagePath();

                String UUID = userProfile.getUUID();

                long id = userProfile.getId();



                Log.e("Profile a: ", nickname + "");

                Log.e("Profile b: ", email + "");

                Log.e("Profile c: ", profileImagePath  + "");

                Log.e("Profile d: ", thumnailPath + "");

                Log.e("Profile e: ", UUID + "");

                Log.e("Profile f: ", id + "");

            }



            // 사용자 정보 요청 실패

            @Override

            public void onFailure(ErrorResult errorResult) {

                Log.e("SessionCallback :: ", "onFailure : " + errorResult.getErrorMessage());

            }



        });

    }

}