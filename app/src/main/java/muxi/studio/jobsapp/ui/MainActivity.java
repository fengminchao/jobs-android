package muxi.studio.jobsapp.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import muxi.studio.jobsapp.R;
import muxi.studio.jobsapp.adapter.FragmentAdapter;
import muxi.studio.jobsapp.base.BaseActivity;
import muxi.studio.jobsapp.mine.MineActivity;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {


    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.tab_layout)
    TabLayout mTabLayout;
    @Bind(R.id.appbar_layout)
    AppBarLayout mAppbarLayout;
    @Bind(R.id.view_pager)
    ViewPager mViewPager;
    @Bind(R.id.nav_view)
    NavigationView mNavView;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        username = getIntent().getStringExtra("username");

        initView();
    }

    private void initView() {
//        mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        mNavView = (NavigationView) findViewById(R.id.nav_view);
        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_menu_white_24dp));
        mToolbar.setTitle(getResources().getString(R.string.action_find));
        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mNavView.setNavigationItemSelectedListener(this);
        View view = mNavView.getHeaderView(0);
        TextView tvName = (TextView) view.findViewById(R.id.tv_name);
        tvName.setText(username);

        setupViewPager();
    }

    private void setupViewPager() {
        List<String> titles = new ArrayList<>();
        titles.add("宣讲会");
        titles.add("招聘会");
        titles.add("网投");
        for (int i = 0; i < 3; i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(i)));
        }
        mTabLayout.setBackgroundColor(Color.WHITE);
        mTabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.colorAccent));

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new FindFragment());
        fragments.add(new FindFragment());
        fragments.add(new FindFragment());

        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent intent;
        int itemId = item.getItemId();
        item.setChecked(true);
        mToolbar.setTitle(item.getTitle());
        mDrawerLayout.closeDrawers();
        setSupportActionBar(mToolbar);
        switch (itemId){
            case R.id.action_person:
                intent = new Intent(MainActivity.this, MineActivity.class);
                startActivity(intent);
                break;
        }

        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
