package com.koiralapankaj007gmail.nepalgovtcallerapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;


public class ContactDetails extends Activity implements View.OnClickListener {

    TextView back;
    TextView heading;
    TextView edit;

    TextView tvContactName;
    TextView tvNumberType;
    TextView tvNumber;
    ImageView ivMsgIcon;
    ImageView ivCallIcon;
    TextView tvNotes;
    TextView tvMail;
    TextView tvUrl;
    TextView tvSocial;
    TextView tvCompany;
    TextView tvInstantMsg;
    LinearLayout share;
    LinearLayout addToFav;
    LinearLayout block;
    LinearLayout delete;

    HashMap<String, String> contactDetails;

    String fName, lName, company, instantMsg, note, phoneNo, homeNo, officeNo, email, socialProfile, url;
    int id, image;
    String backStr, headingStr, editStr;

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_details);
        LinearLayout callDetails = (LinearLayout) this.findViewById(R.id.call_details);
        callDetails.setVisibility(View.GONE);

        back = (TextView) this.findViewById(R.id.back);
        heading = (TextView) this.findViewById(R.id.heading);
        edit = (TextView) this.findViewById(R.id.edit);

        tvContactName = (TextView) this.findViewById(R.id.contact_name);
        tvNumberType = (TextView) this.findViewById(R.id.number_type);
        tvNumber = (TextView) this.findViewById(R.id.number);
        ivMsgIcon = (ImageView) this.findViewById(R.id.msg_icon);
        ivCallIcon = (ImageView) this.findViewById(R.id.call_icon);
        tvNotes = (TextView) this.findViewById(R.id.notes);
        tvMail = (TextView) this.findViewById(R.id.email);
        tvUrl = (TextView) this.findViewById(R.id.url);
        tvSocial = (TextView) this.findViewById(R.id.socialProfile);
        tvCompany = (TextView) this.findViewById(R.id.company);
        tvInstantMsg = (TextView) this.findViewById(R.id.instantMsg);
        share = (LinearLayout) this.findViewById(R.id.shareContact);
        addToFav = (LinearLayout) this.findViewById(R.id.addToFav);
        block = (LinearLayout) this.findViewById(R.id.block);
        delete = (LinearLayout) this.findViewById(R.id.delete_contact);

        back.setOnClickListener(this);
        edit.setOnClickListener(this);
        tvNumberType.setOnClickListener(this);
        ivMsgIcon.setOnClickListener(this);
        ivCallIcon.setOnClickListener(this);
        share.setOnClickListener(this);
        addToFav.setOnClickListener(this);
        block.setOnClickListener(this);
        delete.setOnClickListener(this);

        Intent intent = getIntent();
        contactDetails = (HashMap<String, String>) intent.getSerializableExtra("contactDetails");
        getDataFromView(contactDetails);
        heading(backStr, headingStr, editStr);
        fillDetailsField();

    }


    HashMap getDataFromView(HashMap<String, String> contactDetails) {

        for (HashMap.Entry<String, String> entry : contactDetails.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            switch (key) {
                case "id":
                    id = Integer.parseInt(value);
                    break;
                case "fName":
                    fName = value;
                    break;
                case "lName":
                    lName = value;
                    break;
                case "company":
                    company = value;
                    break;
                case "image":
                    image = R.drawable.ic_account_circle_black_24dp; //Integer.parseInt(value);
                    break;
                case "instantMsg":
                    instantMsg = value;
                    break;
                case "notes":
                    note = value;
                    break;
                case "phoneNo":
                    phoneNo = value;
                    break;
                case "homeNo":
                    homeNo = value;
                    break;
                case "officeNo":
                    officeNo = value;
                    break;
                case "email":
                    email = value;
                    break;
                case "socialProfile":
                    socialProfile = value;
                    break;
                case "url":
                    url = value;
                    break;
                case "back":
                    backStr = value;
                    break;
                case "heading":
                    headingStr = value;
                    break;
                case "edit":
                    editStr = value;
                    break;
                default:
                    break;
            }
        }
        return contactDetails;
    }

    void heading(String backText, String headingText, String editText) {
        back.setText(backText);
        heading.setText(headingText);
        edit.setText(editText);
    }

    void fillDetailsField() {
        if (fName != null && lName != null) {
            tvContactName.setText(fName + " " + lName);
        }
        if (note != null) {
            tvNotes.setText(note);
        }
        if (email != null) {
            tvMail.setText(email);
        }
        if (url != null) {
            tvUrl.setText(url);
        }
        if (socialProfile != null) {
            tvSocial.setText(socialProfile);
        }
        if (company != null) {
            tvCompany.setText(company);
        }
        if (instantMsg != null) {
            tvInstantMsg.setText(instantMsg);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.back:
                finish();
                break;

            case R.id.edit:
                openEditActivity(v);
                break;

            case R.id.number_type :
                Toast.makeText(ContactDetails.this, "Number Type Clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.msg_icon :
                Toast.makeText(ContactDetails.this, "Msg Icon Clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.call_icon :
                Toast.makeText(ContactDetails.this, "Call Icon Clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.shareContact :
                Toast.makeText(ContactDetails.this, "Share Contact Layout Clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.addToFav :
                Toast.makeText(ContactDetails.this, "Add to Fav Layout Clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.block :
                Toast.makeText(ContactDetails.this, "Block Layout Clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.delete_contact :
                Toast.makeText(ContactDetails.this, "Delete Layout Clicked", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }

    private void openEditActivity(View view) {
        Intent intent = new Intent(view.getContext(), UpdateContact.class);
        intent.putExtra("contactDetails", getDataFromView(contactDetails));
        view.getContext().startActivity(intent);
    }
}
