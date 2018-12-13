package com.example.qr_readerexample;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Fragment;




public class Main2Activity extends AppCompatActivity {

    private TextView mTextMessage;
    Button button;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                android.support.v4.app.Fragment selectedFragment = null;
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        Intent myIntent0 = new Intent(Main2Activity.this,
                        HomeFragment.class);
                        startActivity(myIntent0);
                        break;
                    case R.id.navigation_dashboard:
                        Intent myIntent1 = new Intent(Main2Activity.this,
                                ViewFragment.class);
                        startActivity(myIntent1);
                        break;
                    case R.id.navigation_notifications:
                        Intent myIntent2 = new Intent(Main2Activity.this,
                                NotificationFragment.class);
                        startActivity(myIntent2);
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                return true;
            }
        });
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
//        button = (Button) findViewById(R.id.MyButton);
//        button.setOnClickListener(new OnClickListener() {
//            public void onClick(View arg0) {
//                Intent myIntent = new Intent(Main2Activity.this,
//                        DecoderActivity.class);
//
//                //startActivity(myIntent);
//                startActivityForResult(myIntent,100);
//            }
//        });
    }

//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        TextView text = findViewById(R.id.textView);
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 100) {
//            if(resultCode == RESULT_OK) {
//                String result = "Your plant is " + data.getStringExtra("FlowerName");
//                text.setText(result);
//            }
//        }
//    }

}
