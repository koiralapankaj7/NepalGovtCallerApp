package com.koiralapankaj007gmail.nepalgovtcallerapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private Activity activity;

    //Array list to hold name and image type of ContactsList class. this is original one
    private List<ContactsList> data = Collections.emptyList();
    //this array will be made at the the time of searching name. this is a duplicate array
    private List<ContactsList> dataCopy = new ArrayList<>();

    //Constructor
    ContactsAdapter(Context context, List<ContactsList> data, Activity activity) {
        this.activity = activity;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
        dataCopy.addAll(data);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Getting contact_list_row layout id and store into view. MyViewHolder above is a return type which is Class type
        View view = inflater.inflate(R.layout.contact_list_row, parent, false);
        //Creating object of class MyViewHolder and passing view as an argument.
        //return holder
        return new MyViewHolder(view);
    }


    //This method will bind all the available contacts and make a list view
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ContactsList current = data.get(position);
        holder.contactName.setText(current.fName + " " + current.lName);
        holder.contactImage.setImageResource(current.image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //Declaration of attributes related to Contact recycler list view
        TextView contactName;
        ImageView contactImage;
        LinearLayout contactLayout;

        //Attributes related to the contact dropdown layout
        LinearLayout contactDropdownList;
        TextView numberDropdownList;
        LinearLayout callLayout;
        LinearLayout msgLayout;
        LinearLayout videoLayout;
        LinearLayout detailsLayout;

        int clickCount;     //to count number of clicks so that odd click will make dropdown list visible and even click will hide

        CheckPermissionAndActionPerform checkPermissionAndActionPerform;

        //Constructor of the class MyViewHolder where view is passed as an argument.this received view from onCreateViewHolder method above.
        MyViewHolder(View itemView) {
            super(itemView);
            //With the help of view contact name and contact image is extract using id and stored same into variable contactName and contactImage.

            contactName = (TextView) itemView.findViewById(R.id.contact_name);
            contactImage = (ImageView) itemView.findViewById(R.id.contact_image);
            contactLayout = (LinearLayout) itemView.findViewById(R.id.contact_layout);
            contactImage.setOnClickListener(this);
            contactLayout.setOnClickListener(this);
            context = itemView.getContext();

            contactDropdownList = (LinearLayout) itemView.findViewById(R.id.contact_dropdown_list);
            numberDropdownList = (TextView) itemView.findViewById(R.id.number_list_dropdown);
            callLayout = (LinearLayout) itemView.findViewById(R.id.call_btn_layout);
            msgLayout = (LinearLayout) itemView.findViewById(R.id.msg_btn_layout);
            videoLayout = (LinearLayout) itemView.findViewById(R.id.video_btn_layout);
            detailsLayout = (LinearLayout) itemView.findViewById(R.id.details_btn_layout);

            contactDropdownList.setOnClickListener(this);
            numberDropdownList.setOnClickListener(this);
            callLayout.setOnClickListener(this);
            msgLayout.setOnClickListener(this);
            videoLayout.setOnClickListener(this);
            detailsLayout.setOnClickListener(this);

            contactDropdownList.setVisibility(View.GONE);
            checkPermissionAndActionPerform = new CheckPermissionAndActionPerform(activity, context, "9861050106");


        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.contact_image :
                    delete(getAdapterPosition());
                    break;

                case R.id.contact_layout :
                    clickCount++;
                    if ((clickCount % 2) != 0) {
                        contactDropdownList.setVisibility(View.VISIBLE);
                    } else {
                        contactDropdownList.setVisibility(View.GONE);
                    }
                    break;

                case R.id.number_list_dropdown :
//                    Toast.makeText(context, "Item Clicked at " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    showAlertDialog(context);
                    break;

                case R.id.call_btn_layout :
                    if (checkPermissionAndActionPerform.isCallPermissionGranted()) {
                        checkPermissionAndActionPerform.makeCall();
                    }
                    break;

                case R.id.msg_btn_layout :
                    if (checkPermissionAndActionPerform.isSmsPermissionGranted()) {
                        checkPermissionAndActionPerform.sendSMS();
                    }
                    break;

                case R.id.video_btn_layout :
//                    Toast.makeText(context, "Item Clicked at " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    customToast();
                    break;

                case R.id.details_btn_layout :
                    forwardContactData(getAdapterPosition());
                    break;

                default:
                    break;
            }

        }

        private void showAlertDialog(Context context) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setTitle("Select Number");

            dialog.show();

        }

        @SuppressLint("InflateParams")
        void customToast() {
            View toastRoot = inflater.inflate(R.layout.custom_tost, null);
            Toast toast = new Toast(context);
            toast.setView(toastRoot);
            toast.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0, 0);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.show();
        }

        void delete(int position) {
            data.remove(position);
            notifyItemRemoved(position);
        }

        void forwardContactData(int position) {
            //this will store details of clicked view which will be forwarded along with intent to new activity
            HashMap<String, String> currentContactDetail = new HashMap<>();
            ContactsList current = data.get(position);
            currentContactDetail.put("id", String.valueOf(current.id));
            currentContactDetail.put("fName", current.fName);
            currentContactDetail.put("lName", current.lName);
            currentContactDetail.put("company", current.company);
            currentContactDetail.put("image", String.valueOf(current.image));
            currentContactDetail.put("instantMsg", current.instantMsg);
            currentContactDetail.put("notes", current.notes);
            currentContactDetail.put("phoneNo", current.phoneNo);
            currentContactDetail.put("homeNo", current.homeNo);
            currentContactDetail.put("officeNo", current.officeNo);
            currentContactDetail.put("email", current.email);
            currentContactDetail.put("socialProfile", current.socialProfile);
            currentContactDetail.put("url", current.url);
            currentContactDetail.put("back", "All contact");
            currentContactDetail.put("heading", null);
            currentContactDetail.put("edit", "Edit");
            Intent intent = new Intent(context, ContactDetails.class);
            intent.putExtra("contactDetails", currentContactDetail);
            context.startActivity(intent);
        }
    }

//    This will perform search
    void filter(String text) {
        data.clear();
        if (text.isEmpty()) {
            data.addAll(dataCopy);
        } else {
            text = text.toLowerCase();
            for (ContactsList data1 : dataCopy) {
                String name = data1.fName + " " + data1.lName;
                if (name.toLowerCase().contains(text)) {
                    data.add(data1);
                }
            }
        }
        notifyDataSetChanged();
    }

}
