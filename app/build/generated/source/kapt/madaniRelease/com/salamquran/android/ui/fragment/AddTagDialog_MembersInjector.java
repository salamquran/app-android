// Generated by Dagger (https://google.github.io/dagger).
package com.salamquran.android.ui.fragment;

import com.salamquran.android.presenter.bookmark.AddTagDialogPresenter;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class AddTagDialog_MembersInjector implements MembersInjector<AddTagDialog> {
  private final Provider<AddTagDialogPresenter> addTagDialogPresenterProvider;

  public AddTagDialog_MembersInjector(
      Provider<AddTagDialogPresenter> addTagDialogPresenterProvider) {
    this.addTagDialogPresenterProvider = addTagDialogPresenterProvider;
  }

  public static MembersInjector<AddTagDialog> create(
      Provider<AddTagDialogPresenter> addTagDialogPresenterProvider) {
    return new AddTagDialog_MembersInjector(addTagDialogPresenterProvider);}

  @Override
  public void injectMembers(AddTagDialog instance) {
    injectAddTagDialogPresenter(instance, addTagDialogPresenterProvider.get());
  }

  public static void injectAddTagDialogPresenter(AddTagDialog instance,
      AddTagDialogPresenter addTagDialogPresenter) {
    instance.addTagDialogPresenter = addTagDialogPresenter;
  }
}
