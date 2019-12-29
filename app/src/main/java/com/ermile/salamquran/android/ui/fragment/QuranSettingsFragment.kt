package com.ermile.salamquran.android.ui.fragment

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceGroup
import androidx.preference.PreferenceScreen
import com.ermile.data.source.PageProvider
import com.ermile.salamquran.android.QuranAdvancedPreferenceActivity
import com.ermile.salamquran.android.QuranApplication
import com.ermile.salamquran.android.R
import com.ermile.salamquran.android.data.Constants
import com.ermile.salamquran.android.pageselect.PageSelectActivity
import com.ermile.salamquran.android.salamquran.Language.LanguageActivity
import com.ermile.salamquran.android.ui.AudioManagerActivity
import com.ermile.salamquran.android.ui.QuranActivity
import com.ermile.salamquran.android.ui.TranslationManagerActivity
import java.util.*
import javax.inject.Inject

class QuranSettingsFragment : PreferenceFragmentCompat(),
  SharedPreferences.OnSharedPreferenceChangeListener {

  @Inject
  lateinit var pageTypes:
      Map<@JvmSuppressWildcards String, @JvmSuppressWildcards PageProvider>

  override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {

    addPreferencesFromResource(R.xml.quran_preferences)

    val appContext = requireContext().applicationContext

    // field injection
    (appContext as QuranApplication).applicationComponent.inject(this)

    // salamquran
    val t2Prefs: PreferenceScreen? = findPreference(Constants.prefs_change_language)
    t2Prefs?.setOnPreferenceClickListener {
      startActivity(Intent(activity, LanguageActivity::class.java))
      true
    }
//-----------------------------------


    // handle translation manager click
    val translationPref: Preference? = findPreference(Constants.PREF_TRANSLATION_MANAGER)
    translationPref?.setOnPreferenceClickListener {
      startActivity(Intent(activity, TranslationManagerActivity::class.java))
      true
    }

    // handle audio manager click
    val audioManagerPref: Preference? = findPreference(Constants.PREF_AUDIO_MANAGER)
    audioManagerPref?.setOnPreferenceClickListener {
      startActivity(Intent(activity, AudioManagerActivity::class.java))
      true
    }

    val pageChangePref: Preference? = findPreference(Constants.PREF_PAGE_TYPE)
    if (pageTypes.size < 2 && pageChangePref != null) {
      val readingPrefs: Preference? = findPreference(Constants.PREF_READING_CATEGORY)
      (readingPrefs as PreferenceGroup).removePreference(pageChangePref)
    }
  }

  override fun onResume() {
    super.onResume()
    context?.let {
      (Objects.requireNonNull(activity)!!.application as QuranApplication)
          .refreshLocale(it, true)
    }
    preferenceScreen.sharedPreferences
      .registerOnSharedPreferenceChangeListener(this)
  }

  override fun onPause() {
    preferenceScreen.sharedPreferences
      .unregisterOnSharedPreferenceChangeListener(this)
    super.onPause()
  }

  override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
    if (key == Constants.PREF_USE_ARABIC_NAMES) {
      val context = activity
      if (context is QuranActivity) {
        context.restartActivity()
      }
    }
  }

  override fun onPreferenceTreeClick(preference: Preference): Boolean {
    val key = preference.key
    if ("key_prefs_advanced" == key) {
      val intent = Intent(activity, QuranAdvancedPreferenceActivity::class.java)
      startActivity(intent)
      return true
    } else if (Constants.PREF_PAGE_TYPE == key) {
      val intent = Intent(activity, PageSelectActivity::class.java)
      startActivity(intent)
      return true
    }

    return super.onPreferenceTreeClick(preference)
  }
}
