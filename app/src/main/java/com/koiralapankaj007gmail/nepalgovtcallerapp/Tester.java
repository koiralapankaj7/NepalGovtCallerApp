package com.koiralapankaj007gmail.nepalgovtcallerapp;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class Tester extends Activity implements View.OnClickListener {

    Button button;
    TextView textView;
    public static String result;
//    JSONObject jsonObject;
//    JSONArray jsonArray;
//    ContactsData contactsData;

    ArrayList<HashMap<String, String>> contactList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tester);

        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.text_view);

        button.setOnClickListener(this);

        contactList = new ArrayList<>();

        String method = "getData";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                textView.setText(result);
                break;

            default:
                break;
        }
    }

    class BackgroundJsonGetter extends AsyncTask<Void, Void, String> {

        private String json_url;
        String json_string;

        @Override
        protected void onPreExecute() {
            json_url = "http://192.168.43.142/ContactManager/retrive_data.php";
        }

        @Override
        protected String doInBackground(Void... params) {
            if (json_string != null) {
                try {
                    URL url = new URL(json_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder stringBuilder = new StringBuilder();


                    while ((json_string = bufferedReader.readLine()) != null) {
                        stringBuilder.append(json_string).append("\n");
                    }

                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return stringBuilder.toString().trim();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {

        }

    }

//    void contactDetails() {
//        try {
//            jsonObject = new JSONObject(result);
//            jsonArray = jsonObject.getJSONArray("server_response");
//
//            String id, fName, lName, company, image, instantMsg, notes, phoneNo, homeNo, officeNo, email, socialProfile, url;
//
//            int count = 0;
//
//            while (count < jsonArray.length()) {
//                JSONObject jo = jsonArray.getJSONObject(count);
//                id = jo.getString("id");
//                fName = jo.getString("fName");
//                lName = jo.getString("lName");
//                company = jo.getString("company");
//                image = jo.getString("image");
//                instantMsg = jo.getString("instantMsg");
//                notes = jo.getString("notes");
//                phoneNo = jo.getString("phoneNo");
//                homeNo = jo.getString("homeNo");
//                officeNo = jo.getString("officeNo");
//                email = jo.getString("email");
//                socialProfile = jo.getString("socialProfile");
//                url = jo.getString("twitter");
//
//                contactsData = new ContactsData(id, fName, lName, company, image, instantMsg, notes, phoneNo, homeNo, officeNo, email, socialProfile, url);
//
//                count++;
//
//                //                String strJson = "{\"Employee\" :[{\"id\":\"101\",\"name\":\"pankaj\",\"salary\":\"50000\"}, {\"id\":\"102\",\"name\":\"shova\",\"salary\":\"60000\"}]}";
////                String data = "";
//
////        try {
////            jsonObject = new JSONObject(strJson);
////            jsonArray = jsonObject.optJSONArray("Employee");
////
////            for (int i = 0; i < jsonArray.length(); i++) {
////                JSONObject jsonObject = jsonArray.getJSONObject(i);
////
////                int id = Integer.parseInt(jsonObject.optString("id"));
////                String name = jsonObject.optString("name");
////                String salary = jsonObject.optString("salary");
////
////                data += "Node" + i + " :\n id = " + id + " \n Name = " + name + "\n Salary = " + salary + "\n";
////            }
////            textView.setText(data);
////
////        } catch (JSONException e) {
////            e.printStackTrace();
////        }
//
////                contactDetails.add(id);
//////                contactDetails.add(name);
//////                contactDetails.add(salary);
////                contactDetails.add(fName);
////                contactDetails.add(lName);
////                contactDetails.add(company);
////                contactDetails.add(image);
////                contactDetails.add(instantMsg);
////                contactDetails.add(notes);
////                contactDetails.add(phoneNo);
////                contactDetails.add(homeNo);
////                contactDetails.add(officeNo);
////                contactDetails.add(email);
////                contactDetails.add(socialProfile);
////                contactDetails.add(url);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

}
