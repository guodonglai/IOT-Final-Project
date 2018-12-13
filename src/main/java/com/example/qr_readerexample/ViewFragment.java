package com.example.qr_readerexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.Intent;
import java.util.Calendar;
import java.util.Date;

import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View.OnClickListener;

import com.macroyau.thingspeakandroid.ThingSpeakChannel;
import com.macroyau.thingspeakandroid.ThingSpeakLineChart;
import com.macroyau.thingspeakandroid.model.ChannelFeed;
import android.widget.Toast;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class ViewFragment extends AppCompatActivity {
    private ThingSpeakChannel tsChannel;
    private ThingSpeakLineChart tsChart, tsChart1, tsChart2;
    private LineChartView chartView;
    private LineChartView chartView1;
    private LineChartView chartView2;
    private Button button;
    private ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_views);

        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = getPackageManager().getLaunchIntentForPackage("com.cinetica_tech.thingview");
                startActivity(i);
            }
        });

        imageButton = (ImageButton) findViewById(R.id.imageButton2);
        imageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent j = getPackageManager().getLaunchIntentForPackage("org.videolan.vlc");
                startActivity(j);
            }
        });

        tsChannel   = new ThingSpeakChannel(640271);
        tsChannel.setChannelFeedUpdateListener(new ThingSpeakChannel.ChannelFeedUpdateListener() {
            @Override
            public void onChannelFeedUpdated(long channelId, String channelName, ChannelFeed channelFeed) {
                // Notify last update time of the Channel feed through a Toast message
                Date lastUpdate = channelFeed.getChannel().getUpdatedAt();
                Toast.makeText(ViewFragment.this, lastUpdate.toString(), Toast.LENGTH_LONG).show();

            }
        });
        tsChannel.loadChannelFeed();

        // Create a Calendar object dated 5 minutes ago
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -5);

        // Configure LineChartView
        chartView = (LineChartView) findViewById(R.id.chart);
        chartView1 = (LineChartView) findViewById(R.id.chart3);
        chartView2 = (LineChartView) findViewById(R.id.chart4);

        chartView.setZoomEnabled(true);
        chartView.setValueSelectionEnabled(true);

        // Create a line chart from Field1 of ThinkSpeak Channel 9
        tsChart = new ThingSpeakLineChart(640271, 1);
        // Get 200 entries at maximum
        tsChart.setNumberOfEntries(200);
        // Set value axis labels on 10-unit interval
        tsChart.setValueAxisLabelInterval(1);
        // Set date axis labels on 5-minute interval
        tsChart.setDateAxisLabelInterval(10);
        // Show the line as a cubic spline
        tsChart.useSpline(true);
        // Set the line color
        tsChart.setLineColor(Color.parseColor("#D32F2F"));
        // Set the axis color
        tsChart.setAxisColor(Color.parseColor("#455a64"));


        tsChart.setChartStartDate(calendar.getTime());
        tsChart.setListener(new ThingSpeakLineChart.ChartDataUpdateListener() {
            @Override
            public void onChartDataUpdated(long channelId, int fieldId, String title, LineChartData lineChartData, Viewport maxViewport, Viewport initialViewport) {
                // Set chart data to the LineChartView
                chartView.setLineChartData(lineChartData);
                // Set scrolling bounds of the chart
                chartView.setMaximumViewport(maxViewport);
                // Set the initial chart bounds
                chartView.setCurrentViewport(initialViewport);
//                LineChartData data = new LineChartData();
//                float data1=data.getBaseValue();
//                TextView tvName = (TextView)findViewById(R.id.textView);
//                tvName.setText((int) data1);
            }
        });
        // Load chart data asynchronously
        tsChart.loadChartData();

        chartView1.setZoomEnabled(true);
        chartView1.setValueSelectionEnabled(true);

        // Create a line chart from Field1 of ThinkSpeak Channel 9
        tsChart1 = new ThingSpeakLineChart(640331, 1);
        // Get 200 entries at maximum
        tsChart1.setNumberOfEntries(200);
        // Set value axis labels on 10-unit interval
        tsChart1.setValueAxisLabelInterval(1);
        // Set date axis labels on 5-minute interval
        tsChart1.setDateAxisLabelInterval(10);
        // Show the line as a cubic spline
        tsChart1.useSpline(true);
        // Set the line color
        tsChart1.setLineColor(Color.parseColor("#D32F2F"));
        // Set the axis color
        tsChart1.setAxisColor(Color.parseColor("#455a64"));


        tsChart1.setChartStartDate(calendar.getTime());
        tsChart1.setListener(new ThingSpeakLineChart.ChartDataUpdateListener() {
            @Override
            public void onChartDataUpdated(long channelId, int fieldId, String title, LineChartData lineChartData, Viewport maxViewport, Viewport initialViewport) {
                // Set chart data to the LineChartView
                chartView1.setLineChartData(lineChartData);
                // Set scrolling bounds of the chart
                chartView1.setMaximumViewport(maxViewport);
                // Set the initial chart bounds
                chartView1.setCurrentViewport(initialViewport);
//                LineChartData data = new LineChartData();
//                float data1=data.getBaseValue();
//                TextView tvName = (TextView)findViewById(R.id.textView);
//                tvName.setText((int) data1);
            }
        });
        // Load chart data asynchronously
        tsChart1.loadChartData();

        chartView2.setZoomEnabled(true);
        chartView2.setValueSelectionEnabled(true);

        // Create a line chart from Field1 of ThinkSpeak Channel 9
        tsChart2 = new ThingSpeakLineChart(640332, 1);
        // Get 200 entries at maximum
        tsChart2.setNumberOfEntries(200);
        // Set value axis labels on 10-unit interval
        tsChart2.setValueAxisLabelInterval(1);
        // Set date axis labels on 5-minute interval
        tsChart2.setDateAxisLabelInterval(10);
        // Show the line as a cubic spline
        tsChart2.useSpline(true);
        // Set the line color
        tsChart2.setLineColor(Color.parseColor("#D32F2F"));
        // Set the axis color
        tsChart2.setAxisColor(Color.parseColor("#455a64"));


        tsChart2.setChartStartDate(calendar.getTime());
        tsChart2.setListener(new ThingSpeakLineChart.ChartDataUpdateListener() {
            @Override
            public void onChartDataUpdated(long channelId, int fieldId, String title, LineChartData lineChartData, Viewport maxViewport, Viewport initialViewport) {
                // Set chart data to the LineChartView
                chartView2.setLineChartData(lineChartData);
                // Set scrolling bounds of the chart
                chartView2.setMaximumViewport(maxViewport);
                // Set the initial chart bounds
                chartView2.setCurrentViewport(initialViewport);
//                LineChartData data = new LineChartData();
//                float data1=data.getBaseValue();
//                TextView tvName = (TextView)findViewById(R.id.textView);
//                tvName.setText((int) data1);
            }
        });
        // Load chart data asynchronously
        tsChart2.loadChartData();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.getMenu().findItem(R.id.navigation_dashboard).setChecked(true);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        Intent myIntent0 = new Intent(ViewFragment.this,
                                HomeFragment.class);
                        startActivity(myIntent0);
                        break;
                    case R.id.navigation_dashboard:
                        break;
                    case R.id.navigation_notifications:
                        Intent myIntent2 = new Intent(ViewFragment.this,
                                NotificationFragment.class);
                        startActivity(myIntent2);
                        break;
                }
                return true;
            }
        });
    }
}
