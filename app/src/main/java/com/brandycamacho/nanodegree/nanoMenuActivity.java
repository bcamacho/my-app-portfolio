package com.brandycamacho.nanodegree;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.brandycamacho.nanodegree.kbv.KenBurnsView;

import java.util.ArrayList;

public class nanoMenuActivity extends ActionBarActivity {
    // we need to access our layout resource
    private LinearLayout ll;
    // ArrayList to hold our menu data
    private ArrayList<String> menuArray;
    // Ken burns background effect
    private KenBurnsView mKenBurns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set XML View
        setContentView(R.layout.activity_nano_menu);
        // Configure Ken Burns Effect
        mKenBurns = (KenBurnsView) findViewById(R.id.ken_burns_images);
        mKenBurns.setImageResource(R.drawable.splash_screen_background);
        // Create ScrollView to hold our LinyearLayout
        ScrollView sv = new ScrollView(this);
        // Create Linear Layout container to hold our Nano Degree menu
        ll = new LinearLayout(this);
        // Attach LinearLayout to ScrollView
        sv.addView(ll);
        // Set background color
//        ll.setBackgroundColor(Color.LTGRAY);
        // Configure orientation of layout to vertical
        ll.setOrientation(LinearLayout.VERTICAL);
        // Configure layout parameters
        RelativeLayout.LayoutParams rlParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        rlParams.setMargins(0, dp2Px(65),0,0);
        // Attache parameters to our ScrollView
        sv.setLayoutParams(rlParams);

        // Implement our menu by starting nanoMenu private class
        nanoMenu();

        // Attached to Relative Layout View container
        RelativeLayout rl=((RelativeLayout) findViewById(R.id.rl_nano_menu));
        // Create new TextView to hold our header title
        TextView tv_menu_header = new TextView(this);
        // Attach text to our header title through string resource file
        tv_menu_header.setText(R.string.title);
        RelativeLayout.LayoutParams tvTitleParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
//        tvTitleParams.setMargins();
        tv_menu_header.setLayoutParams(tvTitleParams);
        tv_menu_header.setPadding(0,0,0,0);
        tvTitleParams.setMargins(0,50,0,50);
        tv_menu_header.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.h1));
        tv_menu_header.setGravity(Gravity.CENTER_HORIZONTAL);
        // Assign header text to Relative Layout View container
        rl.addView(tv_menu_header);
        // Assign ScrollView Layout to Relative Layout View container
        rl.addView(sv);
    }

    private void nanoMenu(){
        menuArray = new ArrayList<String>();
        menuArray.add("Spotify Streamer");
        menuArray.add("Scores App");
        menuArray.add("Library App");
        menuArray.add("Build it Bigger");
        menuArray.add("XYZ Reader");
        menuArray.add("Capstone");

        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        for (int i = 0 ; i < menuArray.size(); i++) {
            final int count = i;
            final Button btn = new Button(this);
            llParams.setMargins(0, dp2Px(5), 0, dp2Px(5));
            btn.setLayoutParams(llParams);
            btn.setId(i);
//            btn.setBackgroundColor(Color.rgb(240, 140, 53));
            btn.setHeight(dp2Px(70));
            btn.setBottom(dp2Px(10));
            btn.setText(menuArray.get(i));
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    print("This button will launch "+menuArray.get(count));
//                    btn.setBackgroundColor(Color.rgb(000, 140, 53));
                }
            });

            ll.addView(btn);

            // Add a line separator between buttons, also known as horizontal rule </hr>
            if(i != (menuArray.size() - 1)) {
                 /* Needed a way to control horizontal line between buttons.
                    This required the use of multiple views */
                RelativeLayout rl = new RelativeLayout(this);
                RelativeLayout.LayoutParams rlParams = new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                );
                rl.setPadding(10, 0, 10, 0);
                rl.setLayoutParams(rlParams);
                // Create a horizontal line to separate buttons
                View line = new View(this);
                ViewGroup.LayoutParams rlLineParams = new ViewGroup.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 1);
                line.setLayoutParams(rlLineParams);
                line.setBackgroundColor(Color.rgb(51, 51, 51));
                rl.addView(line);
                ll.addView(rl);
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nano_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void print(String txt){
        Toast.makeText(this, txt, Toast.LENGTH_SHORT).show();
    }

    private int dp2Px(int size){
        DisplayMetrics dm = getResources().getDisplayMetrics();
        float dpIntoPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, size, dm);
        int val = Math.round(dpIntoPx);
        return val;
    }
    private int px2int(String px){
        px = px.replace("px", "");
        return Integer.valueOf(px);
    }
}
