package com.example.finalproject.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalproject.R;


public class ContactFragment extends Fragment {

    private EditText subjectEditText;
    private EditText messageEditText;
    private Button sendEmailButton;
    private Button callButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_contact, container, false);

        subjectEditText = view.findViewById(R.id.subjectEditText);
        messageEditText = view.findViewById(R.id.messageMultiLine);
        sendEmailButton = view.findViewById(R.id.sendEmailButton);
        callButton = view.findViewById(R.id.callButton);

        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( !subjectEditText.getText().toString().isEmpty()
                && !messageEditText.getText().toString().isEmpty())
                {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:"));
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"tournal@yahoo.com"});
                    intent.putExtra(Intent.EXTRA_SUBJECT, subjectEditText.getText().toString());
                    intent.putExtra(Intent.EXTRA_TEXT, messageEditText.getText().toString());
                    if(intent.resolveActivity(view.getContext().getPackageManager())!=null){
                        startActivity(intent);
                    } else{
                        Toast.makeText(view.getContext(), "There is no app that support this action!", Toast.LENGTH_SHORT).show();
                    }
                } else{
                    Toast.makeText(view.getContext(), "Please fill all fields!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "0700000000"));
                startActivity(intent);
            }
        });
        return view;
    }
}