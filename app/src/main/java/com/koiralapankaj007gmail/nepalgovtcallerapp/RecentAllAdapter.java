package com.koiralapankaj007gmail.nepalgovtcallerapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class RecentAllAdapter extends RecyclerView.Adapter<RecentAllAdapter.MyViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<RecentAllList> data = Collections.emptyList();

    //Constructor
    public RecentAllAdapter(Context context, List<RecentAllList> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recent_all_contacts_row, parent, false);
        //Log.d("Contact", "onCreateViewHolder called");
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RecentAllList current = data.get(position);
        // Log.d("Contact", "onBindViewHolder called" + position);
        holder.contactName.setText(current.contactName);
        holder.contactNameType.setText(current.contactNameType);
        holder.time.setText(current.time);
        holder.callTypeImage.setImageResource(current.callTypeImage);
        holder.moreInfoImage.setImageResource(current.moreInfoImage);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView contactName;
        TextView time;
        TextView contactNameType;
        ImageView callTypeImage;
        ImageView moreInfoImage;

        //Constructor of the class MyViewHolder where view is passed as an argument.this received view from onCreateViewHolder method above.
        public MyViewHolder(View itemView) {
            super(itemView);
            contactName = (TextView) itemView.findViewById(R.id.recent_all_name);
            contactNameType = (TextView) itemView.findViewById(R.id.recent_all_name_type);
            time = (TextView) itemView.findViewById(R.id.recent_all_time);
            callTypeImage = (ImageView) itemView.findViewById(R.id.recent_all_image);
            moreInfoImage = (ImageView) itemView.findViewById(R.id.recent_all_information_btn);
            moreInfoImage.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // Toast.makeText(context, "Iteam Clicked at" + getAdapterPosition(), Toast.LENGTH_SHORT).show();
            delete();
        }

        public void delete() {
//            data.remove(position);
//            notifyItemRemoved(position);
            Intent intent = new Intent(context, ContactInfo.class);
            context.startActivity(intent);
        }
    }
}
