package com.koiralapankaj007gmail.nepalgovtcallerapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


class BackgroundTask extends AsyncTask<String, Void, String> {

    private Context context;
    private String json_url;
    private String method;

    BackgroundTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        json_url = "http://192.168.0.103/ContactManager/retrive_data.php";
    }

    @Override
    protected final String doInBackground(String... params) {

        String addContact_url = "http://192.168.0.103/ContactManager/add_contact.php";
        method = params[0];

        if (method.equals("addContact") && method != null) {
            try {
                URL url = new URL(addContact_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));

                String data = URLEncoder.encode("first_name", "UTF-8") +"="+ URLEncoder.encode(params[1], "UTF-8")+"&"+
                            URLEncoder.encode("last_name", "UTF-8") +"="+ URLEncoder.encode(params[2], "UTF-8")+"&"+
                            URLEncoder.encode("company_name", "UTF-8") +"="+ URLEncoder.encode(params[3], "UTF-8")+"&"+
                            URLEncoder.encode("phone_no", "UTF-8") +"="+ URLEncoder.encode(params[4], "UTF-8")+"&"+
                            URLEncoder.encode("home_no", "UTF-8") +"="+ URLEncoder.encode(params[5], "UTF-8")+"&"+
                            URLEncoder.encode("office_no", "UTF-8") +"="+ URLEncoder.encode(params[6], "UTF-8")+"&"+
                            URLEncoder.encode("email", "UTF-8") +"="+ URLEncoder.encode(params[7], "UTF-8")+"&"+
                            URLEncoder.encode("url", "UTF-8") +"="+ URLEncoder.encode(params[8], "UTF-8")+"&"+
                            URLEncoder.encode("social_profile", "UTF-8") +"="+ URLEncoder.encode(params[9], "UTF-8")+"&"+
                            URLEncoder.encode("instant_msg", "UTF-8") +"="+ URLEncoder.encode(params[10], "UTF-8")+"&"+
                            URLEncoder.encode("notes", "UTF-8") +"="+ URLEncoder.encode(params[11], "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();

                InputStream IS = httpURLConnection.getInputStream();
                IS.close();

            return "Add contact information successfully.....";

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (method.equals("getData")){

            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();

                String json_string;
                while ((json_string = bufferedReader.readLine()) != null) {
                    stringBuilder.append(json_string).append('\n');
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
        if (method.equals("addContact")) {
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        } else if (method.equals("getData")) {
            Toast.makeText(context, result, Toast.LENGTH_LONG).show();
            Tester.result = result;
//            ContactsFragment.result = result;
        }
    }

}
