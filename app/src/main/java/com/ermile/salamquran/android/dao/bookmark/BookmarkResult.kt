package com.ermile.salamquran.android.dao.bookmark

import com.ermile.salamquran.android.dao.Tag
import com.ermile.salamquran.android.ui.helpers.QuranRow

data class BookmarkResult(val rows: List<QuranRow>, val tagMap: Map<Long, Tag>)
