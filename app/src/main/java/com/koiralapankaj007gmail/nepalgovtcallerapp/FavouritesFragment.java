package com.koiralapankaj007gmail.nepalgovtcallerapp;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavouritesFragment extends Fragment {

    private RecyclerView recyclerView;
    private FavouritesAdapter adapter;

    public FavouritesFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment and store into view named rootView
        View layout = inflater.inflate(R.layout.fragment_favourites, container, false);

        recyclerView = (RecyclerView) layout.findViewById(R.id.favouritesList);

        adapter = new FavouritesAdapter(getActivity(), getFavouritesNameAndImage());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return layout;
    }

    //this method will return array of name and image
    public static List<FavouritesList> getFavouritesNameAndImage() {
        List<FavouritesList> data = new ArrayList<>();
        int[] favouritesImage = {R.drawable.ic_account_circle_black_24dp, R.drawable.ic_account_circle_black_24dp, R.drawable.ic_account_circle_black_24dp};
        String[] favouritesName = {"Ram Koirala", "Shyam Koirala", "Sundar Koirala"};
        String[] favouritesNumberType = {"Phone", "Home", "Office"};
//            for (int i = 0; i<contactName.length && i<contactImage.length; i++) {
//                ContactsList current = new ContactsList();
//                current.contactImageId = contactImage[i];
//                current.contactName = contactName[i];
//                data.add(current);
//            }
        for (int i = 0; i< 20; i++) {
            FavouritesList current = new FavouritesList();
            current.favouritesImageId = favouritesImage[i%favouritesImage.length];
            current.favouritesName = favouritesName[i%favouritesName.length];
            current.favouritesNumberType = favouritesNumberType[i%favouritesNumberType.length];
            data.add(current);
        }
        return data;
    }

}
