package com.ermile.salamquran.android.dao.audio

import com.ermile.salamquran.android.data.SuraAyah

data class AudioPlaybackInfo(val currentAyah: SuraAyah,
                             val timesPlayed: Int = 1,
                             val rangePlayedTimes: Int = 1,
                             val shouldPlayBasmallah: Boolean = false)
