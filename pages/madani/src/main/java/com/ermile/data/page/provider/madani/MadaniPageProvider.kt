package com.ermile.data.page.provider.madani

import com.ermile.data.pageinfo.common.MadaniDataSource
import com.ermile.data.pageinfo.common.size.DefaultPageSizeCalculator
import com.ermile.data.source.DisplaySize
import com.ermile.data.source.PageProvider
import com.ermile.data.source.PageSizeCalculator
import com.ermile.salamquran.android.pages.madani.R

class MadaniPageProvider : PageProvider {
  private val baseUrl = "https://dl.salamquran.com/app"
  private val dataSource = MadaniDataSource()

  override fun getDataSource() = dataSource

  override fun getPageSizeCalculator(displaySize: DisplaySize): PageSizeCalculator =
      DefaultPageSizeCalculator(displaySize)

  override fun getImageVersion() = 6

  override fun getImagesBaseUrl() = "$baseUrl/"

  override fun getImagesZipBaseUrl() = "$baseUrl/zips/"

  override fun getPatchBaseUrl() = "$baseUrl/patches/v"

  override fun getAyahInfoBaseUrl() = "$baseUrl/databases/"

  override fun getAudioDirectoryName() = "audio"

  override fun getDatabaseDirectoryName() = "databases"

  override fun getAyahInfoDirectoryName() = getDatabaseDirectoryName()

  override fun getDatabasesBaseUrl() = "$baseUrl/databases/"

  override fun getAudioDatabasesBaseUrl() = getDatabasesBaseUrl() + "audio/"

  override fun getImagesDirectoryName() = ""

  override fun getPreviewTitle() = R.string.madani_title

  override fun getPreviewDescription() = R.string.madani_description
}
