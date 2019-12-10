package com.salamquran.android.dao.bookmark

import com.salamquran.android.dao.Tag
import com.salamquran.android.ui.helpers.QuranRow

data class BookmarkResult(val rows: List<QuranRow>, val tagMap: Map<Long, Tag>)
