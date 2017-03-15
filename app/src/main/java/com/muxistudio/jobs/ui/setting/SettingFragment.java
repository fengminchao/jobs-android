package com.muxistudio.jobs.ui.setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;

import com.muxistudio.jobs.App;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.api.UserStorge;
import com.muxistudio.jobs.injector.PerActivity;
import com.muxistudio.jobs.ui.BaseActivity;
import com.muxistudio.jobs.ui.find.FindFragment;
import com.muxistudio.jobs.ui.login.LoginActivity;
import com.muxistudio.jobs.ui.main.MainActivity;
import com.muxistudio.jobs.util.PreferenceUtil;

import javax.inject.Inject;

/**
 * Created by ybao on 16/11/6.
 */

@PerActivity
public class SettingFragment extends PreferenceFragment
        implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {

    @Inject
    UserStorge mUserStorge;
    private SwitchPreference themePreference;
    private SwitchPreference notifyPreference;
    private Preference cachePreference;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = App.sContext;
        initInjector();
        addPreferencesFromResource(R.xml.preference_setting);
        sp = new PreferenceUtil();
        themePreference = (SwitchPreference) findPreference(getString(R.string.key_theme));
        themePreference.setOnPreferenceChangeListener(this);
        notifyPreference = (SwitchPreference) findPreference(getString(R.string.key_notify));
        notifyPreference.setOnPreferenceChangeListener(this);
        cachePreference = findPreference(getString(R.string.key_cache));
        cachePreference.setOnPreferenceClickListener(this);
        logoutPreference = findPreference(getString(R.string.key_logout));
        logoutPreference.setOnPreferenceClickListener(this);
    }

    private void initInjector() {
        ((MainActivity)getActivity()).getComponent().inject(this);
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
        } else if (preference.getKey().equals(getString(R.string.key_logout))) {
            mUserStorge.logout();
            ((MainActivity)getActivity()).reload();
            LoginActivity.startActivity(getActivity());
        }
        return true;
    }

    public void clearCache() {

    }
}
