package com.ermile.salamquran.android.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import androidx.core.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ermile.salamquran.android.R;
import com.ermile.salamquran.android.dao.Tag;
import com.ermile.salamquran.android.util.QuranSettings;

import java.util.List;

public class TagsViewGroup extends LinearLayout {
  private static final int MAX_TAGS = 6;

  private int mTagWidth;
  private int mTagsToShow;
  private int mTagsMargin;
  private int mTagsTextSize;
  private int mDefaultTagBackgroundColor;
  private int mDefaultTagTextColor;
  private Typeface font_dana;
  private boolean mIsRtl;
  private Context mContext;

  private List<Tag> mTags;

  public TagsViewGroup(Context context) {
    this(context, null);
  }

  public TagsViewGroup(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public TagsViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public TagsViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    init(context);
  }

  private void init(Context context) {
    mContext = context;

    Resources resources = context.getResources();
    mTagWidth = resources.getDimensionPixelSize(R.dimen.tag_width);
    mTagsMargin = resources.getDimensionPixelSize(R.dimen.tag_margin);
    mTagsTextSize = resources.getDimensionPixelSize(R.dimen.tag_text_size);
    mDefaultTagBackgroundColor = ContextCompat.getColor(context, R.color.tagBackgroundColor_bookmark_inList);
    mDefaultTagTextColor = ContextCompat.getColor(context, R.color.tagTextColor_bookmark_inList);
    font_dana = Typeface.createFromAsset(context.getAssets(),"dana_regular.ttf");
    mTagsToShow = MAX_TAGS;
    mIsRtl = QuranSettings.getInstance(context).isArabicNames();
  }

  public void setTags(List<Tag> tags) {
    removeAllViews();
    mTags = tags;

    for (int i = 0, tagsSize = tags.size(); i < tagsSize; i++) {
      Tag tag = tags.get(i);
      LinearLayout.LayoutParams params =
          new LinearLayout.LayoutParams(mTagWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
      params.gravity = Gravity.CENTER;
      setLeftRightMargin(params, i, Math.min(tagsSize, MAX_TAGS) - 1);

      TextView tv = new TextView(mContext);
      tv.setText(tag.getName());
      tv.setTypeface(font_dana);
      tv.setTextColor(mDefaultTagTextColor);
      tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTagsTextSize);
      tv.setBackgroundColor(mDefaultTagBackgroundColor);
      tv.setGravity(Gravity.CENTER);
      tv.setLines(1);
      tv.setEllipsize(TextUtils.TruncateAt.END);
      addView(tv, params);
    }
    requestLayout();
  }

  private void setLeftRightMargin(LayoutParams params, int position, int maxPosition) {
    if (position == 0) {
      setStartMargin(params, 0);
      setEndMargin(params, maxPosition == 0 ? 0 : mTagsMargin);
    } else if (position == maxPosition) {
      setStartMargin(params, mTagsMargin);
      setEndMargin(params, 0);
    } else {
      setStartMargin(params, mTagsMargin);
      setEndMargin(params, mTagsMargin);
    }
  }

  private void setStartMargin(LayoutParams params, int value) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
      params.setMarginStart(value);
    } else if (mIsRtl) {
      params.rightMargin = value;
    } else {
      params.leftMargin = value;
    }
  }

  private void setEndMargin(LayoutParams params, int value) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
      params.setMarginEnd(value);
    } else if (mIsRtl) {
      params.leftMargin = value;
    } else {
      params.rightMargin = value;
    }
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY) {
      mTagsToShow = MeasureSpec.getSize(widthMeasureSpec) / (mTagWidth + 2 * mTagsMargin);
      mTagsToShow = Math.min(mTagsToShow, mTags.isEmpty() ? mTagsToShow : mTags.size());
      int width = ((mTagsToShow - 1) * 2 * mTagsMargin) + (mTagsToShow * mTagWidth);
      setMeasuredDimension(width, MeasureSpec.getSize(heightMeasureSpec));
    }
  }
}
