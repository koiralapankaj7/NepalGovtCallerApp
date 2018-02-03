package com.koiralapankaj007gmail.nepalgovtcallerapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.android.photoutil.CameraPhoto;
import com.kosalgeek.android.photoutil.GalleryPhoto;
import com.kosalgeek.android.photoutil.ImageLoader;

import java.io.FileNotFoundException;
import java.io.IOException;


public class AddContact extends Activity implements View.OnClickListener {

    private final String TAG = this.getClass().getName();

    TextView cancel, done;
    EditText firstName, lastName, companyName, phoneNo, email, url, socialProfile, instantMsg, notes;                                                                   // 9 items
    String _firstName, _lastName, _companyName, _phoneNo, _email, _url, _socialProfile, _instantMsg, _notes;
    ImageView contact_image_iv, camera_icon_iv, gallery_icon_iv, remove_mobile_iv, remove_email_iv, remove_url_iv, remove_social_profile_iv, remove_instant_msg_iv;     // 8 items
    LinearLayout camera_gallery_icon, dynamic_phone_1, append_phone, dynamic_email_1, append_email, dynamic_url_1, append_url, dynamic_social_1, append_social,
            dynamic_instant_1, append_instant, append_add_field;                                          // 7 items (1 nc + 6 cl)
    //    int _id, _image;
//    String _back, _heading, _edit;
    int clickCount; //To count number of clicks

    MyDBHandler myDBHandler;

    CheckPermissionAndActionPerform checkPermissionAndActionPerform;
    //From image picker library
    CameraPhoto cameraPhoto;
    GalleryPhoto galleryPhoto;
    final int CAMERA_REQUEST = 13323;
    final int GALLERY_REQUEST = 12224;


    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contacts);

        // CLICKABLE TEXT VIEW (APP BAR ITEMS) 2 items
        cancel = (TextView) findViewById(R.id.add_contact_cancle);
        done = (TextView) findViewById(R.id.add_contact_done);

        // EDIT TEXT FIELD (CONTACT DETAILS) 9 items
        firstName = (EditText) findViewById(R.id.add_contact_first_name);
        lastName = (EditText) findViewById(R.id.add_contact_last_name);
        companyName = (EditText) findViewById(R.id.add_contact_company_name);
        phoneNo = (EditText) findViewById(R.id.phone_number_1);
        email = (EditText) findViewById(R.id.email_id_1);
        url = (EditText) findViewById(R.id.url_1);
        socialProfile = (EditText) findViewById(R.id.social_profile_1);
        instantMsg = (EditText) findViewById(R.id.instant_message_1);
        notes = (EditText) findViewById(R.id.contact_notes);

        //CLICKABLE IMAGE VIEW (WORK AS A BUTTON) 8 items
        contact_image_iv = (ImageView) findViewById(R.id.contact_image_view);
        camera_icon_iv = (ImageView) findViewById(R.id.camera_icon);
        gallery_icon_iv = (ImageView) findViewById(R.id.gallery_icon);
        remove_mobile_iv = (ImageView) findViewById(R.id.remove_mobile_1);
        remove_email_iv = (ImageView) findViewById(R.id.remove_email_1);
        remove_url_iv = (ImageView) findViewById(R.id.remove_url_1);
        remove_social_profile_iv = (ImageView) findViewById(R.id.remove_social_profile_1);
        remove_instant_msg_iv = (ImageView) findViewById(R.id.remove_instant_msg_1);

        //CLICKABLE LAYOUTS 6 items
        append_phone = (LinearLayout) findViewById(R.id.append_phone_no_field);
        append_email = (LinearLayout) findViewById(R.id.append_email_id_field);
        append_url = (LinearLayout) findViewById(R.id.append_url_field);
        append_social = (LinearLayout) findViewById(R.id.append_social_profile_field);
        append_instant = (LinearLayout) findViewById(R.id.append_instant_message_field);
        append_add_field = (LinearLayout) findViewById(R.id.append_add_field);

        //HIDE AND DISPLAY LAYOUT ON CLICK 6 items
        camera_gallery_icon = (LinearLayout) findViewById(R.id.camera_gallery_layout);
        dynamic_phone_1 = (LinearLayout) findViewById(R.id.dynamic_phone_layout_1);
        dynamic_email_1 = (LinearLayout) findViewById(R.id.dynamic_email_layout_1);
        dynamic_url_1 = (LinearLayout) findViewById(R.id.dynamic_url_layout_1);
        dynamic_social_1 = (LinearLayout) findViewById(R.id.dynamic_social_layout_1);
        dynamic_instant_1 = (LinearLayout) findViewById(R.id.dynamic_instant_layout_1);
        camera_gallery_icon.setVisibility(View.GONE);
        dynamic_phone_1.setVisibility(View.GONE);
        dynamic_email_1.setVisibility(View.GONE);
        dynamic_url_1.setVisibility(View.GONE);
        dynamic_social_1.setVisibility(View.GONE);
        dynamic_instant_1.setVisibility(View.GONE);

        // SETTING ON CLICK LISTENER TO ALL CLICKABLE ITEMS 16 items
        cancel.setOnClickListener(this);
        done.setOnClickListener(this);
        contact_image_iv.setOnClickListener(this);
        camera_icon_iv.setOnClickListener(this);
        gallery_icon_iv.setOnClickListener(this);
        remove_mobile_iv.setOnClickListener(this);
        remove_email_iv.setOnClickListener(this);
        remove_url_iv.setOnClickListener(this);
        remove_social_profile_iv.setOnClickListener(this);
        remove_instant_msg_iv.setOnClickListener(this);
        append_phone.setOnClickListener(this);
        append_email.setOnClickListener(this);
        append_url.setOnClickListener(this);
        append_social.setOnClickListener(this);
        append_instant.setOnClickListener(this);
        append_add_field.setOnClickListener(this);

        checkPermissionAndActionPerform = new CheckPermissionAndActionPerform(this, getApplicationContext(), "");
        cameraPhoto = new CameraPhoto(getApplicationContext());
        galleryPhoto = new GalleryPhoto(getApplicationContext());

        myDBHandler = new MyDBHandler(this, null, null, 1);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            //Cancel text view of the app bar
            case R.id.add_contact_cancle:
                finish();
                break;

            //Done button of the app bar
            case R.id.add_contact_done:
                addContact();
                break;

            //Contact image
            case R.id.contact_image_view:
                clickCount++;
                hideAndSheik(camera_gallery_icon);
                break;

            //Camera icon (below contact image)
            case R.id.camera_icon:
                checkPermissionAndActionPerform.isCameraPermissionGranted();
                checkPermissionAndActionPerform.isExternalStoragePermissionGranted();
                try {
                    startActivityForResult(cameraPhoto.takePhotoIntent(), CAMERA_REQUEST);
                    cameraPhoto.addToGallery();
                } catch (IOException e) {
                    Toast.makeText(this, "Something wrong while taking photo", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Error : " + e);
                }
                break;

            //Gallery icon (below contact image)
            case R.id.gallery_icon:
                checkPermissionAndActionPerform.isExternalStoragePermissionGranted();
                startActivityForResult(galleryPhoto.openGalleryIntent(), GALLERY_REQUEST);
                break;

            //Minus button to remove mobile no
            case R.id.remove_mobile_1:
                Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
                break;

            //Minus button to remove email
            case R.id.remove_email_1:
                Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
                break;

            //Minus button to remove url
            case R.id.remove_url_1:
                Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
                break;

            //Minus button to remove social profile
            case R.id.remove_social_profile_1:
                Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
                break;

            //Minus button to remove instant message
            case R.id.remove_instant_msg_1:
                Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
                break;

            //Append field for mobile number
            case R.id.append_phone_no_field:
                clickCount++;
                hideAndSheik(dynamic_phone_1);
                break;

            //Append field for email
            case R.id.append_email_id_field:
                clickCount++;
                hideAndSheik(dynamic_email_1);
                break;

            //Append field for url
            case R.id.append_url_field:
                clickCount++;
                hideAndSheik(dynamic_url_1);
                break;

            case R.id.append_social_profile_field:
                clickCount++;
                hideAndSheik(dynamic_social_1);
                break;

            //Append field for instant message
            case R.id.append_instant_message_field:
                clickCount++;
                hideAndSheik(dynamic_instant_1);
                break;

            //Append field to create new extra fields
            case R.id.append_add_field:
                Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_REQUEST) {
                String photoPath = cameraPhoto.getPhotoPath();
                try {
                    Bitmap bitmap = ImageLoader.init().from(photoPath).requestSize(512, 512).getBitmap();
                    contact_image_iv.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    Toast.makeText(this, "Something wrong while loading photo", Toast.LENGTH_SHORT).show();
                }
//                Log.d(TAG, photoPath);
            } else if (requestCode == GALLERY_REQUEST) {
                Uri uri = data.getData();
                galleryPhoto.setPhotoUri(uri);
                String photoPath = galleryPhoto.getPath();
                try {
                    Bitmap bitmap = ImageLoader.init().from(photoPath).requestSize(512, 512).getBitmap();
                    contact_image_iv.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    Toast.makeText(this, "Something wrong while selecting photo", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

//    void addContact() {
//        String method = "addContact";
//        _firstName = firstName.getText().toString();
//        _lastName = lastName.getText().toString();
//        _companyName = companyName.getText().toString();
//        _phoneNo = phoneNo.getText().toString();
////        _homeNo = homeNo.getText().toString();
////        _officeNo = officeNo.getText().toString();
//        _email = email.getText().toString();
//        _url = url.getText().toString();
//        _socialProfile = socialProfile.getText().toString();
//        _instantMsg = instantMsg.getText().toString();
//        _notes = notes.getText().toString();
//
//        BackgroundTask backgroundTask = new BackgroundTask(this);
//        backgroundTask.execute(method, _firstName, _lastName, _companyName, _phoneNo, _email, _url, _socialProfile, _instantMsg, _notes);
//        finish();
//    }

    void addContact() {
        CompleteContactInformationHolder ccih = new CompleteContactInformationHolder();

        ccih.setFIRST_NAME(_firstName = firstName.getText().toString());
        ccih.setLAST_NAME(_lastName = lastName.getText().toString());
        ccih.setCOMPANY_NAME(_companyName = companyName.getText().toString());
        ccih.setPHONE_NUMBER_1(_phoneNo = phoneNo.getText().toString());
        ccih.setEMAIL_ID_1(_email = email.getText().toString());
        ccih.setURL_1(_url = url.getText().toString());
        ccih.setSOCIAL_PROFILE_1(_socialProfile = socialProfile.getText().toString());
        ccih.setINSTANT_MESSAGE_1(_instantMsg = instantMsg.getText().toString());
        ccih.setNOTES(_notes = notes.getText().toString());

        myDBHandler.addToSQLite(ccih);
    }

    private void hideAndSheik(LinearLayout ly) {
        if ((clickCount % 2) != 0) {
            ly.setVisibility(View.VISIBLE);
        } else {
            ly.setVisibility(View.GONE);
        }
    }
}
