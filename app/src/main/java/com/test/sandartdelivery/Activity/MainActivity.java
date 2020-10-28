package com.test.sandartdelivery.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.test.sandartdelivery.Fragment.HomeFragment;
import com.test.sandartdelivery.R;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getCanonicalName();

    private RecyclerView rvMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFragment();
    }

    private void addFragment(){
        Fragment fragment = new HomeFragment();
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.main_frame_layout, fragment).commit();
    }

}
