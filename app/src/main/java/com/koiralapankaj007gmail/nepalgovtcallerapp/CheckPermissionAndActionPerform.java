package com.koiralapankaj007gmail.nepalgovtcallerapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

class CheckPermissionAndActionPerform {

    private Activity activity;
    private String phoneNumber;
    private Context context;

    CheckPermissionAndActionPerform(Activity activity, Context context, String phoneNumber) {
        this.activity = activity;
        this.context = context;
        this.phoneNumber = phoneNumber;
    }


    boolean isCallPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG", "Permission is granted");
                return true;
            } else {
                Log.v("TAG", "Permission is revoked");
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG", "Permission is granted");
            return true;
        }
    }

    boolean isSmsPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG", "Permission is granted");
                return true;
            } else {
                Log.v("TAG", "Permission is revoked");
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.SEND_SMS}, 2);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG", "Permission is granted");
            return true;
        }
    }

    boolean isCameraPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG", "Permission is granted");
                return true;
            } else {
                Log.v("TAG", "Permission is revoked");
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, 3);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG", "Permission is granted");
            return true;
        }
    }

    boolean isExternalStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG", "Permission is granted");
                return true;
            } else {
                Log.v("TAG", "Permission is revoked");
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 4);
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 5);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG", "Permission is granted");
            return true;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    void makeCall() {
        try {
            if (!phoneNumber.equals(" ") && (phoneNumber.length() == 10 || phoneNumber.length() == 11)) {
                String no = "tel:" + phoneNumber;
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(no));

                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                activity.startActivity(intent);

            } else if (phoneNumber != null && phoneNumber.length() == 0) {
                Toast.makeText(context, "You missed to type the number!", Toast.LENGTH_SHORT).show();
            } else if (phoneNumber != null && phoneNumber.length() < 10) {
                Toast.makeText(context, "Check whether you entered correct number!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("DialerAppActivity", "error: " +
                    e.getMessage(), e);//Runtime error will be logged
        }

    }

    void sendSMS() {
        try {
            if (!phoneNumber.equals(" ") && (phoneNumber.length() == 10 || phoneNumber.length() == 11)) {
                String no = "sms:" + phoneNumber;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(no));
                activity.startActivity(intent);
            } else if (phoneNumber != null && phoneNumber.length() == 0) {
                Toast.makeText(context, "You missed to type the number!", Toast.LENGTH_SHORT).show();
            } else if (phoneNumber != null && phoneNumber.length() < 10) {
                Toast.makeText(context, "Check whether you entered correct number!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("DialerAppActivity", "error: " +
                    e.getMessage(), e);//Runtime error will be logged
        }
    }

}


//    @RequiresApi(api = Build.VERSION_CODES.M)
//
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        switch (requestCode) {
//
//            case 1: {
//
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(context, "Permission granted", Toast.LENGTH_SHORT).show();
//                    makeCall();
//                } else {
//                    Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            // other 'case' lines to check for other
//            // permissions this app might request
//        }
//    }