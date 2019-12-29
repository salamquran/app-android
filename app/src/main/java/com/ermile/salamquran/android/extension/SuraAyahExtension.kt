package com.ermile.salamquran.android.extension

import com.ermile.salamquran.android.data.SuraAyah

fun SuraAyah.requiresBasmallah(): Boolean {
  return ayah == 1 && sura != 1 && sura != 9
}
