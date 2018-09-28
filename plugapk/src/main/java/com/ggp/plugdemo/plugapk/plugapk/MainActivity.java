package com.ggp.plugdemo.plugapk.plugapk;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Fragment mFragment;
    private TextView mText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_plug);
        mText = findViewById(R.id.hellotext);
        mText.setText(R.string.hello_blank_fragment);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        mFragment = new FirstFragment();
        fragmentTransaction.replace(R.id.myframe,mFragment);
        fragmentTransaction.commit();
    }
}
