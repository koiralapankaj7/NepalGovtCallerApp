package com.koiralapankaj007gmail.nepalgovtcallerapp;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends Activity implements View.OnClickListener {

    final String FRAGMENT_TAG = "MyFragment";

    private LinearLayout favourites;
    private LinearLayout recents;
    private LinearLayout contacts;
    private LinearLayout keypad;
    private LinearLayout voicemail;

    private ImageView favIcon;
    private ImageView recentIcon;
    private ImageView contactIcon;
    private ImageView keypadIcon;
    private ImageView voicemailIcon;

    private TextView favText;
    private TextView recentText;
    private TextView contactText;
    private TextView keypadText;
    private TextView voicemailText;

    ArrayList<LinearLayout> linLayout = new ArrayList<>();
    ArrayList<ImageView> imgView = new ArrayList<>();
    ArrayList<TextView> textView = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // SET OUR VIEW FROM THE MAIN LAYOUT RESOURCE
        setContentView(R.layout.activity_main);

//        DefaultFont defaultFont = new DefaultFont();
//        defaultFont.setDefaultFont();

        linLayout.add(favourites = (LinearLayout) findViewById(R.id.favourites));
        linLayout.add(recents = (LinearLayout) findViewById(R.id.recents));
        linLayout.add(contacts = (LinearLayout) findViewById(R.id.contacts));
        linLayout.add(keypad = (LinearLayout) findViewById(R.id.keypad));
        linLayout.add(voicemail = (LinearLayout) findViewById(R.id.voiceMail));
        favourites.setOnClickListener(this);
        recents.setOnClickListener(this);
        contacts.setOnClickListener(this);
        keypad.setOnClickListener(this);
        voicemail.setOnClickListener(this);

        imgView.add(favIcon = (ImageView) findViewById(R.id.favourites_icon));
        imgView.add(recentIcon = (ImageView) findViewById(R.id.recents_icon));
        imgView.add(contactIcon = (ImageView) findViewById(R.id.contacts_icon));
        imgView.add(keypadIcon = (ImageView) findViewById(R.id.keypad_icon));
        imgView.add(voicemailIcon = (ImageView) findViewById(R.id.voiceMail_icon));

        textView.add(favText = (TextView) findViewById(R.id.favourites_text));
        textView.add(recentText = (TextView) findViewById(R.id.recents_text));
        textView.add(contactText = (TextView) findViewById(R.id.contacts_text));
        textView.add(keypadText = (TextView) findViewById(R.id.keypad_text));
        textView.add(voicemailText = (TextView) findViewById(R.id.voiceMail_text));

        getFragmentManager().beginTransaction().add(R.id.fragment_container, new KeypadFragment(), FRAGMENT_TAG).commit();
        keypad.setBackgroundColor(Color.parseColor("#BBDEFB"));
        keypadIcon.setColorFilter(Color.parseColor("#304FFF"));
        keypadText.setTextColor(Color.parseColor("#304FFF"));
    }

    @TargetApi(Build.VERSION_CODES.M)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {

        switch(v.getId()) {

            case R.id.favourites :

                loadFragment(FavouritesFragment.class);
                lineLayout(favourites, "#BBDEFB", "#FFFFFF");
                imageView(favIcon, "#304FFF", "#000000");
                textView(favText, "#304FFF", "#000000");
                rippleEffect(v);
                break;

            case R.id.recents :

                loadFragment(RecentsFragment.class);
                lineLayout(recents, "#BBDEFB", "#FFFFFF");
                imageView(recentIcon, "#304FFF", "#000000");
                textView(recentText, "#304FFF", "#000000");
                rippleEffect(v);
                break;

            case R.id.contacts :

                loadFragment(ContactsFragment.class);
                lineLayout(contacts, "#BBDEFB", "#FFFFFF");
                imageView(contactIcon, "#304FFF", "#000000");
                textView(contactText, "#304FFF", "#000000");
                rippleEffect(v);
                break;

            case R.id.keypad :

                loadFragment(KeypadFragment.class);
                lineLayout(keypad, "#BBDEFB", "#FFFFFF");
                imageView(keypadIcon, "#304FFF", "#000000");
                textView(keypadText, "#304FFF", "#000000");
                rippleEffect(v);
                break;

            case R.id.voiceMail :

                loadFragment(VoicemailFragment.class);
                lineLayout(voicemail, "#BBDEFB", "#FFFFFF");
                imageView(voicemailIcon, "#304FFF", "#000000");
                textView(voicemailText, "#304FFF", "#000000");
                rippleEffect(v);
                break;

            default:
                break;

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void loadFragment(Class cl) {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment oldFragment = fragmentManager.findFragmentByTag(FRAGMENT_TAG);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Object obj = null;
            try {
                obj = cl.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }

        if (oldFragment != null) {
            fragmentTransaction.remove(oldFragment);
            fragmentTransaction.add(R.id.fragment_container, (Fragment) obj, FRAGMENT_TAG);
            fragmentTransaction.commit();
        } else {
            fragmentTransaction.add(R.id.fragment_container, (Fragment) obj, FRAGMENT_TAG);
            fragmentTransaction.commit();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void rippleEffect(View id) {
        id.setForeground(getDrawable(R.drawable.ripple_effect_square));
    }

    private void lineLayout(LinearLayout layout, String sel, String other) {
        for (int i = 0; i < linLayout.size(); i++) {
            if (layout == linLayout.get(i)) {
                layout.setBackgroundColor(Color.parseColor(sel));
            } else {
                linLayout.get(i).setBackgroundColor(Color.parseColor(other));
            }
        }
    }

    private void imageView(ImageView iView, String sel, String other) {
        for (int i = 0; i < imgView.size(); i++) {
            if (iView == imgView.get(i)) {
                iView.setColorFilter(Color.parseColor(sel));
            } else {
                imgView.get(i).setColorFilter(Color.parseColor(other));
            }
        }
    }

    public void textView(TextView tv, String sel, String other) {
        for (int i = 0; i < textView.size(); i++) {
            if (tv == textView.get(i)) {
                tv.setTextColor(Color.parseColor(sel));
            } else {
                textView.get(i).setTextColor(Color.parseColor(other));
            }
        }
    }

}






























//    public void recentsEventTrigger() {
//        Fragment oldFragment = fragmentManager.findFragmentByTag(FRAGMENT_TAG);
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        RecentsFragment recentFragment = new RecentsFragment();
//        if (oldFragment != null) {
//            fragmentTransaction.remove(oldFragment);
//            fragmentTransaction.add(R.id.fragment_container, recentFragment, FRAGMENT_TAG);
////            fragmentTransaction.addToBackStack("Recents Fragment");
//            fragmentTransaction.commit();
//        } else {
//            fragmentTransaction.add(R.id.fragment_container, recentFragment, FRAGMENT_TAG);
////            fragmentTransaction.addToBackStack("Recents Fragment");
//            fragmentTransaction.commit();
//        }
//    }

//    public void contactsEventTrigger() {
//        Fragment oldFragment = fragmentManager.findFragmentByTag(FRAGMENT_TAG);
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        ContactsFragment contactsFragment = new ContactsFragment();
//        if (oldFragment != null) {
//            fragmentTransaction.remove(oldFragment);
//            fragmentTransaction.add(R.id.fragment_container, contactsFragment, FRAGMENT_TAG);
////            fragmentTransaction.addToBackStack("Contacts Fragment");
//            fragmentTransaction.commit();
//        } else {
//
//            fragmentTransaction.add(R.id.fragment_container, contactsFragment, FRAGMENT_TAG);
////            fragmentTransaction.addToBackStack("Contacts Fragment");
//            fragmentTransaction.commit();
//        }
//    }
//
//
//    public void keypadEventTrigger() {
//        Fragment oldFragment = fragmentManager.findFragmentByTag(FRAGMENT_TAG);
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        KeypadFragment keypadFragment = new KeypadFragment();
//        if (oldFragment != null) {
//            fragmentTransaction.remove(oldFragment);
//            fragmentTransaction.add(R.id.fragment_container, keypadFragment, FRAGMENT_TAG);
////            fragmentTransaction.addToBackStack("Contacts Fragment");
//            fragmentTransaction.commit();
//        } else {
//            fragmentTransaction.add(R.id.fragment_container, keypadFragment, FRAGMENT_TAG);
////            fragmentTransaction.addToBackStack("Contacts Fragment");
//            fragmentTransaction.commit();
//        }
//
////        LinearLayout layout = (LinearLayout) view.findViewById(R.id.keypad);
////        ImageView icon = (ImageView) view.findViewById(R.id.keypad_icon);
////        TextView text = (TextView) view.findViewById(R.id.keypad_text);
////        icon.setBackgroundResource(R.drawable.ic_keyboard_after);
////        text.setTextColor(Color.parseColor("#651fff"));
//    }
//
//
//    public void voicemailEventTrigger() {
//        Fragment oldFragment = fragmentManager.findFragmentByTag(FRAGMENT_TAG);
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        VoicemailFragment voicemailFragment = new VoicemailFragment();
//        if (oldFragment != null) {
//            fragmentTransaction.remove(oldFragment);
//            fragmentTransaction.add(R.id.fragment_container, voicemailFragment, FRAGMENT_TAG);
////            fragmentTransaction.addToBackStack("Contacts Fragment");
//            fragmentTransaction.commit();
//        } else {
//            fragmentTransaction.add(R.id.fragment_container, voicemailFragment, FRAGMENT_TAG);
////            fragmentTransaction.addToBackStack("Contacts Fragment");
//            fragmentTransaction.commit();
//        }
//    }
