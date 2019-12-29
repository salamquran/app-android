package com.ermile.salamquran.android.presenter.translation

import com.ermile.salamquran.android.R
import com.ermile.salamquran.android.common.LocalTranslation
import com.ermile.salamquran.android.common.QuranAyahInfo
import com.ermile.salamquran.android.data.QuranInfo
import com.ermile.salamquran.android.data.SuraAyah
import com.ermile.salamquran.android.database.TranslationsDBAdapter
import com.ermile.salamquran.android.di.QuranPageScope
import com.ermile.salamquran.android.model.translation.TranslationModel
import com.ermile.salamquran.android.ui.PagerActivity
import com.ermile.salamquran.android.util.QuranSettings
import com.ermile.salamquran.android.util.ShareUtil
import com.ermile.salamquran.android.util.TranslationUtil
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

@QuranPageScope
internal class TranslationPresenter @Inject internal constructor(translationModel: TranslationModel,
                     private val quranSettings: QuranSettings,
                     translationsAdapter: TranslationsDBAdapter,
                     translationUtil: TranslationUtil,
                     private val shareUtil: ShareUtil,
                     private val quranInfo: QuranInfo,
                     private val pages: Array<Int?>) :
    BaseTranslationPresenter<TranslationPresenter.TranslationScreen>(
        translationModel, translationsAdapter, translationUtil, quranInfo) {

  fun refresh() {
    disposable?.dispose()

    disposable = Observable.fromArray(*pages)
        .flatMap { page ->
          getVerses(quranSettings.wantArabicInTranslationView(),
              getTranslations(quranSettings), quranInfo.getVerseRangeForPage(page))
              .toObservable()
        }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(object : DisposableObserver<BaseTranslationPresenter.ResultHolder>() {
          override fun onNext(result: BaseTranslationPresenter.ResultHolder) {
            if (translationScreen != null && result.ayahInformation.isNotEmpty()) {
              translationScreen!!.setVerses(
                  getPage(result.ayahInformation), result.translations,
                  result.ayahInformation)
              translationScreen!!.updateScrollPosition()
            }
          }

          override fun onError(e: Throwable) {}

          override fun onComplete() {}
        })
  }

  fun onTranslationAction(activity: PagerActivity,
                          ayah: QuranAyahInfo,
                          translationNames: Array<LocalTranslation>,
                          actionId: Int) {
    when (actionId) {
      R.id.cab_share_ayah_link -> {
        val bounds = SuraAyah(ayah.sura, ayah.ayah)
        activity.shareAyahLink(bounds, bounds)
      }
      R.id.cab_share_ayah_text, R.id.cab_copy_ayah -> {
        val shareText = shareUtil.getShareText(activity, ayah, translationNames)
        if (actionId == R.id.cab_share_ayah_text) {
          shareUtil.shareViaIntent(activity, shareText, R.string.share_ayah_text)
        } else {
          shareUtil.copyToClipboard(activity, shareText)
        }
      }
    }
  }

  private fun getPage(result: List<QuranAyahInfo>): Int {
    val firstPage = pages.first()
    return if (pages.size == 1 && firstPage != null) {
      firstPage
    } else {
      quranInfo.getPageFromSuraAyah(result[0].sura, result[0].ayah)
    }
  }

  interface TranslationScreen {
    fun setVerses(page: Int,
                  translations: Array<LocalTranslation>,
                  verses: List<@JvmSuppressWildcards QuranAyahInfo>)
    fun updateScrollPosition()
  }
}
