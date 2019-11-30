package com.ermile.salamquran.android.ui.helpers;

import android.content.Context;
import androidx.core.content.ContextCompat;

import com.ermile.salamquran.android.R;
import com.ermile.salamquran.android.dao.bookmark.Bookmark;
import com.ermile.salamquran.android.dao.Tag;
import com.ermile.salamquran.android.data.QuranInfo;

import javax.inject.Inject;

import dagger.Reusable;

@Reusable
public class QuranRowFactory {

  private final QuranInfo quranInfo;
  
  @Inject
  public QuranRowFactory(QuranInfo quranInfo) {
    this.quranInfo = quranInfo;
  }

  public QuranRow fromRecentPageHeader(Context context, int count) {
    return new QuranRow.Builder()
        .withText(context.getResources().getQuantityString(R.plurals.plural_recent_pages, count))
        .withType(QuranRow.HEADER)
        .build();
  }

  public QuranRow fromPageBookmarksHeader(Context context) {
    return new QuranRow.Builder()
        .withText(context.getString(R.string.menu_bookmarks_page))
        .withType(QuranRow.HEADER).build();
  }

  public QuranRow fromAyahBookmarksHeader(Context context) {
    return new QuranRow.Builder()
        .withText(context.getString(R.string.menu_bookmarks_ayah))
        .withType(QuranRow.HEADER).build();
  }

  public QuranRow fromCurrentPage(Context context, int page, long timeStamp) {
    return new QuranRow.Builder()
        .withText(quranInfo.getSuraNameString(context, page))
        .withMetadata(quranInfo.getPageSubtitle(context, page))
        .withSura(quranInfo.safelyGetSuraOnPage(page))
        .withPage(page)
        .withDate(timeStamp)
        .withImageResource(R.drawable.bookmark_currentpage)
        .withImageOverlayColor(ContextCompat.getColor(context, R.color.ic_lastPage_bookmark))
        .build();
  }

  public QuranRow fromBookmark(Context context, Bookmark bookmark) {
    return fromBookmark(context, bookmark, null);
  }

  public QuranRow fromBookmark(Context context, Bookmark bookmark, Long tagId) {
    final QuranRow.Builder builder = new QuranRow.Builder();

    if (bookmark.isPageBookmark()) {
      final int sura = quranInfo.getSuraNumberFromPage(bookmark.getPage());
      builder.withText(quranInfo.getSuraNameString(context, bookmark.getPage()))
          .withMetadata(quranInfo.getPageSubtitle(context, bookmark.getPage()))
          .withType(QuranRow.PAGE_BOOKMARK)
          .withBookmark(bookmark)
          .withDate(bookmark.getTimestamp())
          .withSura(sura)
          .withImageResource(R.drawable.ic_favorite)
          .withImageOverlayColor(ContextCompat.getColor(context, R.color.ic_page_bookmark));
    } else {
      String ayahText = bookmark.getAyahText();

      final String title;
      final String metadata;
      if (ayahText == null) {
        title = quranInfo.getAyahString(bookmark.getSura(), bookmark.getAyah(), context);
        metadata = quranInfo.getPageSubtitle(context, bookmark.getPage());
      } else {
        title = ayahText;
        metadata = quranInfo.getAyahMetadata(bookmark.getSura(), bookmark.getAyah(),
            bookmark.getPage(), context);
      }

      builder.withText(title)
          .withMetadata(metadata)
          .withType(QuranRow.AYAH_BOOKMARK)
          .withBookmark(bookmark)
          .withDate(bookmark.getTimestamp())
          .withImageResource(R.drawable.ic_favorite)
          .withImageOverlayColor(ContextCompat.getColor(context, R.color.ic_aya_bookmark));
    }

    if (tagId != null) {
      builder.withTagId(tagId);
    }
    return builder.build();
  }

  public QuranRow fromTag(Tag tag) {
    return new QuranRow.Builder()
        .withType(QuranRow.BOOKMARK_HEADER)
        .withText(tag.getName())
        .withTagId(tag.getId())
        .build();
  }

  public static QuranRow fromNotTaggedHeader(Context context) {
    return new QuranRow.Builder()
        .withType(QuranRow.BOOKMARK_HEADER)
        .withText(context.getString(R.string.not_tagged))
        .build();
  }
}
