package com.rsc.rschackathon.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.rsc.rschackathon.R;
import com.rsc.rschackathon.adapters.ViewPagerAdapter;
import com.rsc.rschackathon.fragments.FragmentListOfQuizzes;
import com.rsc.rschackathon.fragments.FragmentMapWithQuizzes;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewPagerActivity extends AppCompatActivity {

    ViewPagerAdapter viewPagerAdapter;

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.home_activity_tabs)
    protected TabLayout tabs;

    @BindView(R.id.home_activity_viewpager)
    protected ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        ButterKnife.bind(this);

        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(false);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        setupViewPager(viewPager);
        tabs.setupWithViewPager(viewPager);

    }


    private void setupViewPager(ViewPager viewPager) {
        viewPagerAdapter.addFragment(FragmentListOfQuizzes.newInstance("LIST"), "LIST");
        viewPagerAdapter.addFragment(FragmentMapWithQuizzes.newInstance("MAP"), "MAP");
        viewPager.setAdapter(viewPagerAdapter);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.view_pager_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.search:
//                Toast.makeText(this, "SEARCH", Toast.LENGTH_SHORT).show();
//            default:
//                break;
//        }
//        return true;
//    }
}
