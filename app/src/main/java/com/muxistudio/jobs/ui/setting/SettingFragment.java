package com.muxistudio.jobs.ui.setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;

import com.muxistudio.jobs.App;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.ui.BaseActivity;
import com.muxistudio.jobs.ui.find.FindFragment;
import com.muxistudio.jobs.ui.login.LoginActivity;
import com.muxistudio.jobs.ui.main.MainActivity;
import com.muxistudio.jobs.util.PreferenceUtil;

/**
 * Created by ybao on 16/11/6.
 */

public class SettingFragment extends PreferenceFragment
        implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {

    // TODO: 17/3/14 logout the userstorge

    private SwitchPreference themePreference;
    private SwitchPreference notifyPreference;
    private Preference cachePreference;
    private Preference accountPreference;
    private Preference logoutPreference;

    private PreferenceUtil sp;

    private Context mContext;

    public static void start(Context context) {
        Intent starter = new Intent(context, SettingActiviy.class);
        context.startActivity(starter);
    }

    public static SettingFragment newInstance() {
        Bundle args = new Bundle();
        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }
    //
    //@Override protected void onPostCreate(Bundle savedInstanceState) {
    //  super.onPostCreate(savedInstanceState);
    //  LinearLayout root = (LinearLayout) findViewById(android.R.id.list).getParent().getParent
    // ().getParent();
    //  Toolbar
    //      toolbar = (Toolbar) LayoutInflater.from(this).inflate(R.layout.view_setting_bar,root,
    // false);
    //  root.addView(toolbar,0);
    //  toolbar.setNavigationOnClickListener(v -> this.finish());
    //  ((ListView) findViewById(android.R.id.list)).setDivider(App.sContext.getResources()
    // .getDrawable(R.drawable.divider_list));
    //}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = App.sContext;
        addPreferencesFromResource(R.xml.preference_setting);
        sp = new PreferenceUtil();
        themePreference = (SwitchPreference) findPreference(getString(R.string.key_theme));
        themePreference.setOnPreferenceChangeListener(this);
        notifyPreference = (SwitchPreference) findPreference(getString(R.string.key_notify));
        notifyPreference.setOnPreferenceChangeListener(this);
        cachePreference = findPreference(getString(R.string.key_cache));
        cachePreference.setOnPreferenceClickListener(this);
        accountPreference = findPreference(getString(R.string.key_account));
        accountPreference.setOnPreferenceClickListener(this);
        logoutPreference = findPreference(getString(R.string.key_logout));
        logoutPreference.setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (preference.getKey().equals(getString(R.string.key_theme))) {
            ((BaseActivity) getActivity()).reload();
            sp.putBoolean(PreferenceUtil.IS_SETTING_SHOW, true);
            sp.putBoolean(getString(R.string.key_theme), Boolean.parseBoolean(newValue.toString()));
            sp.putBoolean(PreferenceUtil.IS_NIGHT_THEME, (boolean) newValue);
        } else if (preference.getKey().equals(getString(R.string.key_notify))) {
            changeNotify(Boolean.valueOf(newValue.toString()));
            sp.putBoolean(getString(R.string.key_notify),
                    Boolean.parseBoolean(newValue.toString()));
        }
        return true;
    }

    private void changeNotify(Boolean aBoolean) {

    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        if (preference.getKey().equals(getString(R.string.key_cache))) {
            clearCache();
        } else if (preference.getKey().equals(getString(R.string.key_account))) {
            //startActivity();
        } else if (preference.getKey().equals(getString(R.string.key_logout))) {
            LoginActivity.startActivity(getActivity());
            //((MainActivity) getActivity()).getFragmentManager().beginTransaction().remove(this).commit();
            ((MainActivity) getActivity()).showFragment(FindFragment.newInstance());
            //this.finish();
        }
        return true;
    }

    public void clearCache() {

    }
}
