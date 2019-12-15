package com.ermile.salamquran.android.presenter.bookmark;

import com.ermile.salamquran.android.data.Constants;
import com.ermile.salamquran.android.di.ActivityScope;
import com.ermile.salamquran.android.model.bookmark.RecentPageModel;
import com.ermile.salamquran.android.presenter.Presenter;
import com.ermile.salamquran.android.ui.PagerActivity;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

@ActivityScope
public class RecentPagePresenter implements Presenter<PagerActivity> {
  private final RecentPageModel model;

  private int lastPage;
  private int minimumPage;
  private int maximumPage;
  private Disposable disposable;

  @Inject
  RecentPagePresenter(RecentPageModel model) {
    this.model = model;
  }

  private void onPageChanged(int page) {
    model.updateLatestPage(page);

    lastPage = page;
    if (minimumPage == Constants.NO_PAGE) {
      minimumPage = page;
      maximumPage = page;
    } else if (page < minimumPage) {
      minimumPage = page;
    } else if (page > maximumPage) {
      maximumPage = page;
    }
  }

  public void onJump() {
    saveAndReset();
  }

  @Override
  public void bind(PagerActivity what) {
    minimumPage = Constants.NO_PAGE;
    maximumPage = Constants.NO_PAGE;
    lastPage = Constants.NO_PAGE;

    disposable = what.getViewPagerObservable()
        .subscribeWith(new DisposableObserver<Integer>() {
          @Override
          public void onNext(Integer value) {
            onPageChanged(value);
          }

          @Override
          public void onError(Throwable e) {
          }

          @Override
          public void onComplete() {
          }
        });
  }

  @Override
  public void unbind(PagerActivity what) {
    disposable.dispose();
    saveAndReset();
  }

  private void saveAndReset() {
    if (minimumPage != Constants.NO_PAGE || maximumPage != Constants.NO_PAGE) {
      model.persistLatestPage(minimumPage, maximumPage, lastPage);

      minimumPage = Constants.NO_PAGE;
      maximumPage = Constants.NO_PAGE;
    }
    lastPage = Constants.NO_PAGE;
  }
}
