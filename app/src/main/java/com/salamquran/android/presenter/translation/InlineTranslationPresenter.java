package com.salamquran.android.presenter.translation;

import com.salamquran.android.common.LocalTranslation;
import com.salamquran.android.common.QuranAyahInfo;
import com.salamquran.android.data.QuranInfo;
import com.salamquran.android.data.VerseRange;
import com.salamquran.android.database.TranslationsDBAdapter;
import com.salamquran.android.model.translation.TranslationModel;
import com.salamquran.android.util.QuranSettings;
import com.salamquran.android.util.TranslationUtil;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;

public class InlineTranslationPresenter extends
    BaseTranslationPresenter<InlineTranslationPresenter.TranslationScreen> {
  private final QuranSettings quranSettings;

  @Inject
  InlineTranslationPresenter(TranslationModel translationModel,
                             TranslationsDBAdapter dbAdapter,
                             TranslationUtil translationUtil,
                             QuranSettings quranSettings,
                             QuranInfo quranInfo) {
    super(translationModel, dbAdapter, translationUtil, quranInfo);
    this.quranSettings = quranSettings;
  }

  public void refresh(VerseRange verseRange) {
    if (getDisposable() != null) {
      getDisposable().dispose();
    }

    setDisposable(getVerses(false, getTranslations(quranSettings), verseRange)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new DisposableSingleObserver<ResultHolder>() {
          @Override
          public void onSuccess(ResultHolder result) {
            if (getTranslationScreen() != null) {
              getTranslationScreen()
                  .setVerses(result.getTranslations(), result.getAyahInformation());
            }
          }

          @Override
          public void onError(Throwable e) {
          }
        }));
  }

  public interface TranslationScreen {
    void setVerses(@NonNull LocalTranslation[] translations, @NonNull List<QuranAyahInfo> verses);
  }
}
