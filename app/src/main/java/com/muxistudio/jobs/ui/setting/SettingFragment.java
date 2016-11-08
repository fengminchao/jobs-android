package com.muxistudio.jobs.ui.setting;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import com.muxistudio.jobs.App;
import com.muxistudio.jobs.R;
import com.muxistudio.jobs.ui.BaseActivity;
import com.muxistudio.jobs.util.PreferUtil;
import javax.inject.Inject;


/**
 * Created by ybao on 16/11/6.
 */

public class SettingFragment extends PreferenceFragment
    implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener {

  private SwitchPreference themePreference;
  private SwitchPreference notifyPreference;
  private Preference cachePreference;
  private Preference accountPreference;
  private Preference logoutPreference;

  private PreferUtil sp;

  @Inject Context mContext;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mContext = App.sContext;
    addPreferencesFromResource(R.xml.preference_setting);
    sp = new PreferUtil();
    themePreference = (SwitchPreference) findPreference(getString(R.string.setting_change_theme));
    themePreference.setOnPreferenceChangeListener(this);
    notifyPreference = (SwitchPreference) findPreference(getString(R.string.setting_notify));
    notifyPreference.setOnPreferenceChangeListener(this);
    cachePreference = findPreference(getString(R.string.setting_clear_cache));
    cachePreference.setOnPreferenceClickListener(this);
    accountPreference = findPreference(getString(R.string.setting_change_accout));
    accountPreference.setOnPreferenceClickListener(this);
    logoutPreference = findPreference(getString(R.string.setting_logout));
    logoutPreference.setOnPreferenceClickListener(this);
  }

  @Override public boolean onPreferenceChange(Preference preference, Object newValue) {
    if (preference.getKey().equals(getString(R.string.setting_change_theme))){
      ((BaseActivity) mContext).reload();
      sp.putBoolean(getString(R.string.key_theme),Boolean.parseBoolean(newValue.toString()));
    }else if (preference.getKey().equals(getString(R.string.setting_notify))){
      changeNotify(Boolean.valueOf(newValue.toString()));
      sp.putBoolean(getString(R.string.key_notify),Boolean.parseBoolean(newValue.toString()));
    }
    return true;
  }

  private void changeNotify(Boolean aBoolean) {

  }

  @Override public boolean onPreferenceClick(Preference preference) {
    if (preference.getKey().equals(getString(R.string.key_cache))){
      clearCache();
    }else if (preference.getKey().equals(getString(R.string.key_account))){
      //startActivity();
    }else if (preference.getKey().equals(getString(R.string.key_logout))){
      //startActivity();
    }
    return true;
  }

  public void clearCache (){

  }
}
