// Generated by Dagger (https://google.github.io/dagger).
package com.salamquran.android.presenter.audio;

import com.salamquran.android.data.QuranInfo;
import com.salamquran.android.util.AudioUtils;
import com.salamquran.android.util.QuranFileUtils;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AudioPresenter_Factory implements Factory<AudioPresenter> {
  private final Provider<QuranInfo> quranInfoProvider;

  private final Provider<AudioUtils> audioUtilProvider;

  private final Provider<QuranFileUtils> quranFileUtilsProvider;

  public AudioPresenter_Factory(Provider<QuranInfo> quranInfoProvider,
      Provider<AudioUtils> audioUtilProvider, Provider<QuranFileUtils> quranFileUtilsProvider) {
    this.quranInfoProvider = quranInfoProvider;
    this.audioUtilProvider = audioUtilProvider;
    this.quranFileUtilsProvider = quranFileUtilsProvider;
  }

  @Override
  public AudioPresenter get() {
    return new AudioPresenter(quranInfoProvider.get(), audioUtilProvider.get(), quranFileUtilsProvider.get());
  }

  public static AudioPresenter_Factory create(Provider<QuranInfo> quranInfoProvider,
      Provider<AudioUtils> audioUtilProvider, Provider<QuranFileUtils> quranFileUtilsProvider) {
    return new AudioPresenter_Factory(quranInfoProvider, audioUtilProvider, quranFileUtilsProvider);
  }

  public static AudioPresenter newAudioPresenter(QuranInfo quranInfo, AudioUtils audioUtil,
      QuranFileUtils quranFileUtils) {
    return new AudioPresenter(quranInfo, audioUtil, quranFileUtils);
  }
}
