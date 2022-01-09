package com.uietkuk.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapter fragmentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout=findViewById(R.id.tabLayout);
        pager2=findViewById(R.id.viewPager2);

        FragmentManager fm=getSupportFragmentManager();
        fragmentAdapter=new FragmentAdapter(fm, getLifecycle());
        pager2.setAdapter(fragmentAdapter);

        tabLayout.addTab(tabLayout.newTab().setText("Home"));
        tabLayout.addTab(tabLayout.newTab().setText("Department"));


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Toast.makeText(MainActivity.this, "on tab select", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Toast.makeText(MainActivity.this, "on unselect called", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Toast.makeText(MainActivity.this, "on tab reselect", Toast.LENGTH_SHORT).show();


            }
        });
        

        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                Fragment fragment=null;
                if(position==0){
                    Toast.makeText(MainActivity.this, "home", Toast.LENGTH_SHORT).show();
                    fragment=new Home();

                }
                else{
                    Toast.makeText(MainActivity.this, "depart", Toast.LENGTH_SHORT).show();
                }

                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });


    }
}