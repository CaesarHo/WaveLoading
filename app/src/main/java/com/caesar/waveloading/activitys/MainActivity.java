package com.caesar.waveloading.activitys;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.caesar.waveloading.R;
import com.caesar.waveloading.fragments.Blank1Fragment;
import com.caesar.waveloading.fragments.Blank2Fragment;
import com.caesar.waveloading.fragments.Blank3Fragment;
import com.caesar.waveloading.utils.FragmentUtils;

public class MainActivity extends AppCompatActivity {
    private FragmentManager manager;
    private Blank1Fragment blank1Fragment;
    private Blank2Fragment blank2Fragment;
    private Blank3Fragment blank3Fragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setCurrentTab(0);
                    return true;
                case R.id.navigation_dashboard:
                    setCurrentTab(1);
                    return true;
                case R.id.navigation_notifications:
                    setCurrentTab(2);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        setCurrentTab(0);
    }

    public void setCurrentTab(int currentItem) {
        hideFragment();
        switch (currentItem) {
            case 0://收藏夹
                if (blank1Fragment == null) {
                    blank1Fragment = Blank1Fragment.newInstance("", "");
                    FragmentUtils.addFragment(manager, blank1Fragment, R.id.content);
                }
                FragmentUtils.showFragment(manager, blank1Fragment, 0);
                break;
            case 1://联系人
                if (blank2Fragment == null) {
                    blank2Fragment = Blank2Fragment.newInstance("", "");
                    FragmentUtils.addFragment(manager, blank2Fragment, R.id.content);
                }
                FragmentUtils.showFragment(manager, blank2Fragment, 0);
                break;
            case 2://通话记录
                if (blank3Fragment == null) {
                    blank3Fragment = Blank3Fragment.newInstance("", "");
                    FragmentUtils.addFragment(manager, blank3Fragment, R.id.content);
                }
                FragmentUtils.showFragment(manager, blank3Fragment, 0);
                break;
        }
    }

    public void hideFragment() {
        if (blank1Fragment != null) {//收藏夹
            FragmentUtils.hideFragment(manager, blank1Fragment, 0);
        }
        if (blank2Fragment != null) {//联系人
            FragmentUtils.hideFragment(manager, blank2Fragment, 0);
        }
        if (blank3Fragment != null) {//通话记录
            FragmentUtils.hideFragment(manager, blank3Fragment, 0);
        }
    }

}
