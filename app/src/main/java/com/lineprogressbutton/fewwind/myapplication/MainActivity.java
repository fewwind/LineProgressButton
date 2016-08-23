package com.lineprogressbutton.fewwind.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    LineProgressButton mLineProgressBar;
    private float i = 0.0f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            }
        });
        mLineProgressBar = (LineProgressButton) findViewById(R.id.id_line_progerss_bar);

        mLineProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLineProgressBar.post(loadTask);
            }
        });

    }


    Runnable loadTask = new Runnable() {
        @Override
        public void run() {

            if (i <= 1.0) {
                mLineProgressBar.postDelayed(loadTask, 200);
                i += 0.05f;
                mLineProgressBar.setPrecent(i, "下载中 " + (int) (i * 100) + "%");
            } else {
                mLineProgressBar.setContext("下载完成");

            }
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


    public void learnGit34() {

    }
}
