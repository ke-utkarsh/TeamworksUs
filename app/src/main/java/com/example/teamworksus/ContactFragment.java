package com.example.teamworksus;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ContactFragment extends Fragment {
    public ContactFragment() {
        // Required empty public constructor
    }

    TextView tv1;
    TextView tv2;
    @Override
    public  View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View InputFragmentView = inflater.inflate(R.layout.fragment_home, container, false);

        tv1=(TextView) InputFragmentView.findViewById(R.id.phonenumber);
        tv2=(TextView) InputFragmentView.findViewById(R.id.emailo);

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p="+919876543210";
                Uri u=Uri.parse("tel:"+p);
                Intent i = new Intent(Intent.ACTION_DIAL, u);
                try{
                    startActivity(i);
                }catch(Exception e){
                    Toast.makeText(getActivity(), "Sorry, Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m="abc@xyz.com";
                Intent ema = new Intent(Intent.ACTION_SEND);
                ema.putExtra(Intent.EXTRA_EMAIL,new String[]{ m});
                ema.putExtra(Intent.EXTRA_SUBJECT,"");
                ema.putExtra(Intent.EXTRA_TEXT,"");
                ema.setType("message/rfc822");
                try {
                    startActivity(Intent.createChooser(ema, "Choose an Email client :"));
                }catch(Exception e){
                    Toast.makeText(getActivity(), "Sorry, Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return InputFragmentView;
    }
}
