package com.ermile.salamquran.android.dao.bookmark

import com.ermile.salamquran.android.dao.RecentPage
import com.ermile.salamquran.android.dao.Tag

data class BookmarkData(val tags: List<Tag> = emptyList(),
                        val bookmarks: List<Bookmark> = emptyList(),
                        val recentPages: List<RecentPage> = emptyList())
