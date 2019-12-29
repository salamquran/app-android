package com.ermile.salamquran.android.common

import com.ermile.salamquran.android.data.SuraAyah

data class TranslationMetadata(val sura: Int,
                               val ayah: Int,
                               val text: CharSequence,
                               val link: SuraAyah? = null)
