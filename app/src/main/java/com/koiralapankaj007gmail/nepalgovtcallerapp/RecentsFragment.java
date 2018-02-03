package com.koiralapankaj007gmail.nepalgovtcallerapp;


import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecentsFragment extends Fragment implements View.OnClickListener{
    private RecyclerView recyclerView;
    private RecentAllAdapter allAdapter;
    private RecentMissedAdapter missedAdapter;
    Button all;
    Button missed;

    public RecentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_recents, container, false);

        recyclerView = (RecyclerView) layout.findViewById(R.id.recent_all_contacts);

        all = (Button) layout.findViewById(R.id.all);
        missed = (Button) layout.findViewById(R.id.missed);
        all.setOnClickListener(this);
        missed.setOnClickListener(this);
        allList();

        return layout;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.all :
                allList();
                break;

            case R.id.missed :
                missedList();
                break;

            default:
                break;
        }
    }

    public void allList() {
        allAdapter = new RecentAllAdapter(getActivity(), getAllNameAndImage());
        recyclerView.setAdapter(allAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        all.setBackgroundColor(Color.parseColor("#651fff"));
        all.setTextColor(Color.parseColor("#FFFFFF"));
        missed.setBackgroundResource(R.drawable.button_border);
        missed.setTextColor(Color.parseColor("#651fff"));
    }

    public void missedList() {
        missedAdapter = new RecentMissedAdapter(getActivity(), getMissedNameAndImage());
        recyclerView.setAdapter(missedAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        missed.setBackgroundColor(Color.parseColor("#651fff"));
        missed.setTextColor(Color.parseColor("#FFFFFF"));
        all.setBackgroundResource(R.drawable.button_border);
        all.setTextColor(Color.parseColor("#651fff"));
    }


    //this method will return array of name and image
    public static List<RecentAllList> getAllNameAndImage() {
        List<RecentAllList> data = new ArrayList<>();

        String[] contactName = {"Ram Sharma", "Shyam Sundar", "Sundar Bahadur"};
        String[] contactNameType = {"unknown", "home", "mobile"};
        String[] time = {"3.20 PM", "4.30 PM", "2.30 AM"};
        int[] callTypeImage = {R.drawable.ic_phone_missed_black_24dp, R.drawable.ic_phone_missed_black_24dp, R.drawable.ic_phone_missed_black_24dp};
        int[] moreInfoImage = {R.drawable.ic_info_outline_black_24dp, R.drawable.ic_info_outline_black_24dp, R.drawable.ic_info_outline_black_24dp};

        for (int i = 0; i< 20; i++) {
            RecentAllList current = new RecentAllList();
            current.contactName = contactName[i%contactName.length];
            current.contactNameType = contactNameType[i%contactNameType.length];
            current.time = time[i%time.length];
            current.callTypeImage = callTypeImage[i%callTypeImage.length];
            current.moreInfoImage = moreInfoImage[i%moreInfoImage.length];
            data.add(current);
        }
        return data;
    }

    //this method will return array of name and image
    public static List<RecentMissedList> getMissedNameAndImage() {
        List<RecentMissedList> data = new ArrayList<>();

        String[] contactName = {"Rabin Regmi", "Abhinav Dahal", "Dhurba Sen"};
        String[] contactNameType = {"unknown", "home", "mobile"};
        String[] time = {"3.20 PM", "4.30 PM", "2.30 AM"};
        int[] callTypeImage = {R.drawable.ic_phone_missed_black_24dp, R.drawable.ic_phone_missed_black_24dp, R.drawable.ic_phone_missed_black_24dp};
        int[] moreInfoImage = {R.drawable.ic_info_outline_black_24dp, R.drawable.ic_info_outline_black_24dp, R.drawable.ic_info_outline_black_24dp};

        for (int i = 0; i< 20; i++) {
            RecentMissedList current = new RecentMissedList();
            current.contactName = contactName[i%contactName.length];
            current.contactNameType = contactNameType[i%contactNameType.length];
            current.time = time[i%time.length];
            current.callTypeImage = callTypeImage[i%callTypeImage.length];
            current.moreInfoImage = moreInfoImage[i%moreInfoImage.length];
            data.add(current);
        }
        return data;
    }


}
