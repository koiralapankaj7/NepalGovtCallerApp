package com.koiralapankaj007gmail.nepalgovtcallerapp;




import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Vibrator;
import android.support.annotation.RequiresApi;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;


public class KeypadFragment extends Fragment implements View.OnClickListener, View.OnLongClickListener {

    View rootView;
    private EditText phoneNumber;
    ImageView backspace;
    ImageView addNumber;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_keypad, container, false);

        phoneNumber = (EditText) rootView.findViewById(R.id.number_edit_text);
        phoneNumber.setOnClickListener(this);
        phoneNumber.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        ImageView one = (ImageView) rootView.findViewById(R.id.number_1_btn);
        one.setOnClickListener(this);
        ImageView two = (ImageView) rootView.findViewById(R.id.number_2_btn);
        two.setOnClickListener(this);
        ImageView three = (ImageView) rootView.findViewById(R.id.number_3_btn);
        three.setOnClickListener(this);
        ImageView four = (ImageView) rootView.findViewById(R.id.number_4_btn);
        four.setOnClickListener(this);
        ImageView five = (ImageView) rootView.findViewById(R.id.number_5_btn);
        five.setOnClickListener(this);
        ImageView six = (ImageView) rootView.findViewById(R.id.number_6_btn);
        six.setOnClickListener(this);
        ImageView seven = (ImageView) rootView.findViewById(R.id.number_7_btn);
        seven.setOnClickListener(this);
        ImageView eight = (ImageView) rootView.findViewById(R.id.number_8_btn);
        eight.setOnClickListener(this);
        ImageView nine = (ImageView) rootView.findViewById(R.id.number_9_btn);
        nine.setOnClickListener(this);
        ImageView zero = (ImageView) rootView.findViewById(R.id.number_0_btn);
        zero.setOnClickListener(this);
        ImageView astric = (ImageView) rootView.findViewById(R.id.number_astric_btn);
        astric.setOnClickListener(this);
        ImageView hash = (ImageView) rootView.findViewById(R.id.number_hash_btn);
        hash.setOnClickListener(this);
        addNumber = (ImageView) rootView.findViewById(R.id.add_number);
        addNumber.setOnClickListener(this);
        addNumber.setVisibility(View.INVISIBLE);
        backspace = (ImageView) rootView.findViewById(R.id.number_backspace_btn);
        backspace.setOnClickListener(this);
        backspace.setOnLongClickListener(this);
        backspace.setVisibility(View.INVISIBLE);
        ImageView makeCall = (ImageView) rootView.findViewById(R.id.call_btn);
        makeCall.setOnClickListener(this);

        return rootView;

    }

    @Override
    public boolean onLongClick(View v) {
        phoneNumber.setText("");
        backspace.setVisibility(View.INVISIBLE);
        addNumber.setVisibility(View.INVISIBLE);
        return true;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        vibrateButton();
        switch(v.getId()) {
            case R.id.number_edit_text:
                numberEditText();
                break;

            case R.id.number_backspace_btn:
                numberBackspace();
                break;

            case R.id.number_1_btn:
                numberOne();
                break;

            case R.id.number_2_btn:
                numberTwo();
                break;

            case R.id.number_3_btn:
                numberThree();
                break;

            case R.id.number_4_btn:
                numberFour();
                break;

            case R.id.number_5_btn:
                numberFive();
                break;

            case R.id.number_6_btn:
                numberSix();
                break;

            case R.id.number_7_btn:
                numberSeven();
                break;

            case R.id.number_8_btn:
                numberEight();
                break;

            case R.id.number_9_btn:
                numberNine();
                break;

            case R.id.number_0_btn:
                numberZero();
                break;

            case R.id.number_astric_btn:
                numberAstric();
                break;

            case R.id.number_hash_btn:
                numberHash();
                break;

            case R.id.call_btn:
                CheckPermissionAndActionPerform checkPermisionAndActionPerform = new CheckPermissionAndActionPerform(getActivity(), getContext(), phoneNumber.getText().toString().trim());
                if (checkPermisionAndActionPerform.isCallPermissionGranted()) {
                    checkPermisionAndActionPerform.makeCall();
                }
                break;

            case R.id.add_number:
                addContact();
                break;

            default:
                break;
        }

        if (phoneNumber.getText().toString().equals("")) {
            backspace.setVisibility(View.INVISIBLE);
            addNumber.setVisibility(View.INVISIBLE);
        } else {
            backspace.setVisibility(View.VISIBLE);
            addNumber.setVisibility(View.VISIBLE);
        }

        if(phoneNumber.getText().length() >= 13 && phoneNumber.getText().length() <=16) {
            phoneNumber.setTextSize(30);
        } else if(phoneNumber.getText().length() > 16) {
            phoneNumber.setTextSize(23);
        } else {
            phoneNumber.setTextSize(35);
        }
    }




    private void numberOne() {
        phoneNumber.setText(phoneNumber.getText().toString() + "1");
    }

    private void numberTwo() {
        phoneNumber.setText(phoneNumber.getText().toString() + "2");
    }

    private void numberThree() {
        phoneNumber.setText(phoneNumber.getText().toString() + "3");
    }

    private void numberFour() {
        phoneNumber.setText(phoneNumber.getText().toString() + "4");
    }

    private void numberFive() {
        phoneNumber.setText(phoneNumber.getText().toString() + "5");
    }

    private void numberSix() {
        phoneNumber.setText(phoneNumber.getText().toString() + "6");
    }

    private void numberSeven() {
        phoneNumber.setText(phoneNumber.getText().toString() + "7");
    }

    private void numberEight() {
        phoneNumber.setText(phoneNumber.getText().toString() + "8");
    }

    private void numberNine() {
        phoneNumber.setText(phoneNumber.getText().toString() + "9");
    }

    private void numberZero() {
        phoneNumber.setText(phoneNumber.getText().toString() + "0");
    }

    private void numberAstric() {
        phoneNumber.setText(phoneNumber.getText().toString() + "*");
    }

    private void numberHash() {
        phoneNumber.setText(phoneNumber.getText().toString() + "#");
    }

    private void numberEditText() {   }

    private void numberBackspace() {
        int length = phoneNumber.getText().length();
        if (length > 0) {
            phoneNumber.getText().delete(length - 1, length);
        }
    }

    private void addContact() {
        Intent intent = new Intent(getActivity(), AddContact.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void vibrateButton() {
        Vibrator vibrator = (Vibrator) this.getContext().getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(10);
    }

}
