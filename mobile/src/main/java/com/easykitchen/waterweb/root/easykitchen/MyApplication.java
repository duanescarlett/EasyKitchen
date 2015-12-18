package com.easykitchen.waterweb.root.easykitchen;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

    private static MyApplication sInstance;

    @Override
    public void onCreate(){
        super.onCreate();
        //printHashKey();
        this.sInstance = this;
    }

    public static MyApplication getsInstance(){
        return sInstance;
    }

    public static Context getAppContext(){
        return sInstance.getApplicationContext();
    }

//    public void printHashKey(){
//        // Add code to print out the key hash
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(
//                    "com.easykitchen.waterweb.root.easykitchen",
//                    PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//
//        } catch (NoSuchAlgorithmException e) {
//
//        }
//    }
}
