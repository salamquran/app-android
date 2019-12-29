package com.ermile.salamquran.android.util;

import com.ermile.data.source.PageProvider;
import com.ermile.data.pageinfo.common.MadaniDataSource;

import com.ermile.salamquran.android.data.QuranInfo;
import com.ermile.salamquran.android.data.SuraAyah;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AudioUtilsTest {

  @Test
  public void testGetLastAyahWithNewSurahOnNextPageForMadani() {
    PageProvider pageProviderMock = mock(PageProvider.class);
    when(pageProviderMock.getDataSource())
        .thenReturn(new MadaniDataSource());

    QuranInfo quranInfoMock = new QuranInfo(pageProviderMock);

    AudioUtils audioUtils = new AudioUtils(quranInfoMock, mock(QuranFileUtils.class));
    SuraAyah lastAyah = audioUtils.getLastAyahToPlay(new SuraAyah(109, 1), 603, 1, false);

    assertNotNull(lastAyah);
    assertEquals(5, lastAyah.ayah);
    assertEquals(111, lastAyah.sura);
  }

  @Test
  public void testSuraTawbaDoesNotNeedBasmallah() {
    AudioUtils audioUtils = new AudioUtils(mock(QuranInfo.class), mock(QuranFileUtils.class));

    // start after ayah 1 of sura anfal
    SuraAyah start = new SuraAyah(8, 2);
    // finish in sura tawbah, so no basmallah needed here
    SuraAyah ending = new SuraAyah(9, 100);

    // overall don't need a basmallah
    assertFalse(audioUtils.doesRequireBasmallah(start, ending));
  }

  @Test
  public void testNeedBasmallahAcrossRange() {
    AudioUtils audioUtils = new AudioUtils(mock(QuranInfo.class), mock(QuranFileUtils.class));

    SuraAyah start = new SuraAyah(8, 1);
    SuraAyah ending = new SuraAyah(10, 2);
    // should need a basmallah due to 10:1
    assertTrue(audioUtils.doesRequireBasmallah(start, ending));
  }
}
