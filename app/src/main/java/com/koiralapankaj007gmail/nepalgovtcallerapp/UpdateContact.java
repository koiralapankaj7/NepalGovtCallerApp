package com.koiralapankaj007gmail.nepalgovtcallerapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;


public class UpdateContact extends Activity implements View.OnClickListener{

    TextView cancel, done;
    EditText firstName, lastName, companyName, phoneNo, homeNo, officeNo, email, url, socialProfile, instantMsg, notes;
    String _firstName, _lastName, _companyName, _phoneNo, _homeNo, _officeNo, _email, _url, _socialProfile, _instantMsg, _notes;
    //    int _id, _image;
    //    String _back, _heading, _edit;
    ContactDetails contactDetails;

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_contact);

        firstName = (EditText) findViewById(R.id.add_contact_first_name);
        lastName = (EditText) findViewById(R.id.add_contact_last_name);
        companyName = (EditText) findViewById(R.id.add_contact_company_name);
//        phoneNo = (EditText) findViewById(R.id.add_contact_phone_no);
//        homeNo = (EditText) findViewById(R.id.add_contact_home_no);
//        officeNo = (EditText) findViewById(R.id.add_contact_office_no);
//        email = (EditText) findViewById(R.id.add_contact_email);
//        url = (EditText) findViewById(R.id.add_contact_add_url);
//        socialProfile = (EditText) findViewById(R.id.add_contact_social_profile);
//        instantMsg = (EditText) findViewById(R.id.add_contact_instance_msg);
//        notes = (EditText) findViewById(R.id.add_contact_notes);

        cancel = (TextView) findViewById(R.id.add_contact_cancle);
        done = (TextView) findViewById(R.id.add_contact_done);
        cancel.setOnClickListener(this);
        done.setOnClickListener(this);

        Intent intent = getIntent();
        HashMap<String, String> contactDetails = (HashMap<String, String>) intent.getSerializableExtra("contactDetails");
        this.contactDetails = new ContactDetails();
        this.contactDetails.getDataFromView(contactDetails);
        setField();

//        getDataFromView();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.add_contact_cancle :
                finish();
                break;

            case R.id.add_contact_done :
//                updateContact();
                break;
        }
    }

    void setField() {
        firstName.setText(contactDetails.fName);
        lastName.setText(contactDetails.lName);
        companyName.setText(contactDetails.company);
        phoneNo.setText(contactDetails.phoneNo);
        homeNo.setText(contactDetails.homeNo);
        officeNo.setText(contactDetails.officeNo);
        email.setText(contactDetails.email);
        url.setText(contactDetails.email);
        socialProfile.setText(contactDetails.socialProfile);
        instantMsg.setText(contactDetails.instantMsg);
        notes.setText(contactDetails.note);
    }

    void updateContact() {
        String method = "addContact";
        _firstName = firstName.getText().toString();
        _lastName = lastName.getText().toString();
        _companyName = companyName.getText().toString();
        _phoneNo = phoneNo.getText().toString();
        _homeNo = homeNo.getText().toString();
        _officeNo = officeNo.getText().toString();
        _email = email.getText().toString();
        _url = url.getText().toString();
        _socialProfile = socialProfile.getText().toString();
        _instantMsg = instantMsg.getText().toString();
        _notes = notes.getText().toString();

        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method, _firstName, _lastName, _companyName, _phoneNo, _homeNo, _officeNo, _email, _url, _socialProfile, _instantMsg, _notes);
        finish();
    }
}
