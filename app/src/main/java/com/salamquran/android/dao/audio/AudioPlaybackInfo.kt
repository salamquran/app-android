package com.salamquran.android.dao.audio

import com.salamquran.android.data.SuraAyah

data class AudioPlaybackInfo(val currentAyah: SuraAyah,
                             val timesPlayed: Int = 1,
                             val rangePlayedTimes: Int = 1,
                             val shouldPlayBasmallah: Boolean = false)
