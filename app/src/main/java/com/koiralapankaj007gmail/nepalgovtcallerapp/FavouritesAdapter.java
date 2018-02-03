package com.koiralapankaj007gmail.nepalgovtcallerapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;


public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.MyViewHolder> {
    //private Context context;
    private LayoutInflater inflater;
    //Array list to hold name and image type of FavouritesList class
    private List<FavouritesList> data = Collections.emptyList();

    //Constructor
    public FavouritesAdapter(Context context, List<FavouritesList> data) {
        //this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Getting contact_list_row layout id and store into view. MyViewHolder above is a return type which is Class type
        View view = inflater.inflate(R.layout.favourites_list_row, parent, false);

        //Log.d("Contact", "onCreateViewHolder called");

        //Creating object of class MyViewHolder and passing view as an argument.
        MyViewHolder holder = new MyViewHolder(view);
        //return holder
        return holder;
    }


    //This method will bind all the available contacts and make a listview
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        FavouritesList current = data.get(position);
        // Log.d("Contact", "onBindViewHolder called" + position);
        holder.favouritesName.setText(current.favouritesName);
        holder.favouritesImage.setImageResource(current.favouritesImageId);
        holder.favouritesNumberType.setText(current.favouritesNumberType);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView favouritesName;
        ImageView favouritesImage;
        TextView favouritesNumberType;

        //Constructor of the class MyViewHolder where view is passed as an argument.this received view from onCreateViewHolder method above.
        public MyViewHolder(View itemView) {
            super(itemView);
            //With the help of view contact name and contact image is extract using id and stored same into variable contactName and contactImage.
            favouritesName = (TextView) itemView.findViewById(R.id.favourites_name);
            favouritesImage = (ImageView) itemView.findViewById(R.id.favourites_image);
            favouritesNumberType = (TextView) itemView.findViewById(R.id.favourites_number_type);
            favouritesImage.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // Toast.makeText(context, "Iteam Clicked at" + getAdapterPosition(), Toast.LENGTH_SHORT).show();
            delete(getAdapterPosition());
        }
    }

}
