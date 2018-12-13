package com.example.qr_readerexample;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

public class NotificationFragment extends AppCompatActivity {
    private CardView water;
    private CardView light;
    private CardView diagnosis;
    private String respmsg = new String();
    private TextView foo, solution;
    private int responseCode2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_notification);
        foo = (TextView) findViewById(R.id.textView2);
        solution = (TextView) findViewById(R.id.textView3);
        water = (CardView) findViewById(R.id.cardView7);
        light = (CardView) findViewById(R.id.cardView6);
        diagnosis = (CardView) findViewById(R.id.cardView5);

        water.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(NotificationFragment.this, "You choosed watering the plant", Toast.LENGTH_SHORT).show();
                new SendPostRequest1().execute();
            }
        });
        light.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(NotificationFragment.this, "You choosed Adjust Lighting", Toast.LENGTH_SHORT).show();
                new SendPostRequest2().execute();
            }
        });
        diagnosis.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(NotificationFragment.this, "You choosed Diagnose the Plant", Toast.LENGTH_SHORT).show();
                new SendPostRequest3().execute();
            }
        });
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.getMenu().findItem(R.id.navigation_notifications).setChecked(true);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        Intent myIntent2 = new Intent(NotificationFragment.this,
                                HomeFragment.class);
                        startActivity(myIntent2);
                        break;
                    case R.id.navigation_dashboard:
                        Intent myIntent1 = new Intent(NotificationFragment.this,
                                ViewFragment.class);
                        startActivity(myIntent1);
                        break;
                    case R.id.navigation_notifications:
                        break;
                }
                return true;
            }
        });
    }
    public class SendPostRequest1 extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try{
                //String flower = flowername + " " + "20" + " " + "20";
                String flower = "water";
                URL url = new URL("http://129.236.222.158:1024");
                JSONObject postDataParams = new JSONObject();
                postDataParams.put(flower, "email");


                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(3000 /* milliseconds */);
                conn.setConnectTimeout(3000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));
                writer.flush();
                writer.close();
                os.close();

                respmsg = conn.getResponseMessage();
                int responseCode=conn.getResponseCode();
                responseCode2=responseCode;

            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {

        }


        public String getPostDataString(JSONObject params) throws Exception {

            StringBuilder result = new StringBuilder();
            boolean first = true;

            Iterator<String> itr = params.keys();

            while(itr.hasNext()){

                String key= itr.next();
                Object value = params.get(key);

                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(key, "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(value.toString(), "UTF-8"));

            }
            return result.toString();
        }

    }
    public class SendPostRequest2 extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try{
                //String flower = flowername + " " + "20" + " " + "20";
                String flower = "light";
                URL url = new URL("http://129.236.222.158:1024");
                JSONObject postDataParams = new JSONObject();
                postDataParams.put(flower, "email");


                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(3000 /* milliseconds */);
                conn.setConnectTimeout(3000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));
                writer.flush();
                writer.close();
                os.close();

                respmsg = conn.getResponseMessage();
                int responseCode=conn.getResponseCode();
                responseCode2=responseCode;

            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {

        }


        public String getPostDataString(JSONObject params) throws Exception {

            StringBuilder result = new StringBuilder();
            boolean first = true;

            Iterator<String> itr = params.keys();

            while(itr.hasNext()){

                String key= itr.next();
                Object value = params.get(key);

                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(key, "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(value.toString(), "UTF-8"));

            }
            return result.toString();
        }

    }
    public class SendPostRequest3 extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try{
                //String flower = flowername + " " + "20" + " " + "20";
                String flower = "diagnose";
                URL url = new URL("http://129.236.222.158:1024");
                JSONObject postDataParams = new JSONObject();
                postDataParams.put(flower, "email");


                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(3000 /* milliseconds */);
                conn.setConnectTimeout(3000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));
                writer.flush();
                writer.close();
                os.close();

                respmsg = conn.getResponseMessage();
                int responseCode=conn.getResponseCode();
                responseCode2=responseCode;

            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            foo.setText(respmsg);
            maketreatment(respmsg);

        }

        public void maketreatment(String disease){
            switch(disease){
                case "LATE BLIGHT":
                    solution.setText("Monitor the field, remove and destroy the infected leaves\n  Treat organically with copper spray. \n Use chemical fungicides.");
                    break;
                case "Yellow leaf curl virus":
                    solution.setText("Monitor the field, handpick diseased plants and bury them\n  Use sticky yellow plastic traps. \n Spray insecticides such as organophosphates, carbametes during the sedling stage.");
                    break;
                case "Bacterial Spot":
                    solution.setText("Discard or destroy any affected plants. \n Do not compost them. Rotate your plants yearly to prevent re-infection next year.");
                    break;
            }
        }


        public String getPostDataString(JSONObject params) throws Exception {

            StringBuilder result = new StringBuilder();
            boolean first = true;

            Iterator<String> itr = params.keys();

            while(itr.hasNext()){

                String key= itr.next();
                Object value = params.get(key);

                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(key, "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(value.toString(), "UTF-8"));

            }
            return result.toString();
        }

    }
}
