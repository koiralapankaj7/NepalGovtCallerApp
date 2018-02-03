package com.koiralapankaj007gmail.nepalgovtcallerapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class ContactsFragment extends Fragment implements View.OnClickListener, TextWatcher {

    private EditText searchName;
    ContactsAdapter adapter;
    RecyclerView recyclerView;

    private String TAG = ContactsFragment.class.getSimpleName();
    private ProgressDialog pDialog;
    ArrayList<HashMap<String, String>> contactList;

    public ContactsFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_contacts, container, false);

        recyclerView = (RecyclerView) layout.findViewById(R.id.contactstList);
//        adapter = new ContactsAdapter(getContext(), getContactNameAndImage(), getActivity());
//        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Button addContact = (Button) layout.findViewById(R.id.add_contact);
        addContact.setOnClickListener(this);

        searchName = (EditText) layout.findViewById(R.id.search_contact);
        searchName.addTextChangedListener(this);


        contactList = new ArrayList<>();
        new GetContacts().execute();

        return layout;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_contact:
                Intent intent = new Intent(getActivity(), AddContact.class);
                startActivity(intent);
                break;

            case R.id.search_contact:
//                searchName.setFocusableInTouchMode(true);
//                searchName.setFocusable(true);
                break;

            default:
                break;
        }

    }

//  START -----> SEARCHING MECHANISM
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String inputText = searchName.getText().toString().toLowerCase(Locale.getDefault());
        adapter.filter(inputText);
    }
//  END -------> SEARCHING MECHANISM

    //this method will return array of contact details
    public List<ContactsList> getContactNameAndImage() {
        List<ContactsList> data = new ArrayList<>();

        for (int i = 0; i < contactList.size(); i++) {
            ContactsList current = new ContactsList();
            for (HashMap.Entry<String, String> entry : contactList.get(i).entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                switch (key) {
                    case "id":
                        current.id = Integer.parseInt(value);
                        break;
                    case "fName":
                        current.fName = value;
                        break;
                    case "lName":
                        current.lName = value;
                        break;
                    case "company":
                        current.company = value;
                        break;
                    case "image":
                        current.image = R.drawable.ic_account_circle_black_24dp; //Integer.parseInt(value);
                        break;
                    case "instantMsg":
                        current.instantMsg = value;
                        break;
                    case "notes":
                        current.notes = value;
                        break;
                    case "phoneNo":
                        current.phoneNo = value;
                        break;
                    case "homeNo":
                        current.homeNo = value;
                        break;
                    case "officeNo":
                        current.officeNo = value;
                        break;
                    case "email":
                        current.email = value;
                        break;
                    case "socialProfile":
                        current.socialProfile = value;
                        break;
                    case "url":
                        current.url = value;
                        break;
                    default:
                        break;
                }
            }
            data.add(current);
        }
        return data;
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {

        String id, fName, lName, company, image, instantMsg, notes, phoneNo, homeNo, officeNo, email, socialProfile, url;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //SHOWING PROGRESS DIALOG
            pDialog = new ProgressDialog(getContext());
            pDialog.setMessage("Please Wait....");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            //MAKING A REQUEST TO URL AND GETTING RESPONSE
            String jsonStr = sh.makeServiceCall("http://192.168.0.103/ContactManager/retrive_data.php");
//            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    //GETTING JSONArray NODE
                    JSONArray contacts = jsonObj.getJSONArray("server_response");

                    //LOOPING THROUGH ALL CONTACTS
                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        id = c.getString("id");
                        fName = c.getString("fName");
                        lName = c.getString("lName");
                        company = c.getString("company");
                        image = c.getString("image");
                        instantMsg = c.getString("instantMsg");
                        notes = c.getString("notes");
                        phoneNo = c.getString("phoneNo");
                        homeNo = c.getString("homeNo");
                        officeNo = c.getString("officeNo");
                        email = c.getString("email");
                        socialProfile = c.getString("socialProfile");
                        url = c.getString("url");

                        //TMP HASH MAP
                        HashMap<String, String> contact = new HashMap<>();

                        //adding each child to hashmap key => value
                        contact.put("id", id);
                        contact.put("fName", fName);
                        contact.put("lName", lName);
                        contact.put("company", company);
                        contact.put("image", image);
                        contact.put("instantMsg", instantMsg);
                        contact.put("notes", notes);
                        contact.put("phoneNo", phoneNo);
                        contact.put("homeNo", homeNo);
                        contact.put("officeNo", officeNo);
                        contact.put("email", email);
                        contact.put("socialProfile", socialProfile);
                        contact.put("url", url);

                        //adding contact to contacts list
                        contactList.add(contact);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json Parsing error: " + e.getMessage());
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
            }
//            Log.e(TAG, "HASH MAp: " + contactList);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            //Dismiss the progress dialog
            if (pDialog.isShowing()) {
                pDialog.dismiss();
            }

            adapter = new ContactsAdapter(getContext(), getContactNameAndImage(), getActivity());
            recyclerView.setAdapter(adapter);
        }
    }

}
