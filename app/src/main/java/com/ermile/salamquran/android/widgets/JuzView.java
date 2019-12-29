package com.ermile.salamquran.android.widgets;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import android.text.TextPaint;
import android.text.TextUtils;

import com.ermile.salamquran.android.R;

import org.jetbrains.annotations.NotNull;

public class JuzView extends Drawable {
  public static final int TYPE_JUZ = 1;
  public static final int TYPE_QUARTER = 2;
  public static final int TYPE_HALF = 3;
  public static final int TYPE_THREE_QUARTERS = 4;

  private int radius;
  private int circleY;
  private int percentage;
  private float textOffset;
  private String overlayText;

  private RectF circleRect;
  private Paint circlePaint;
  private TextPaint overlayTextPaint;
  private Paint circleBackgroundPaint;

  public JuzView(Context context, int type, String overlayText) {
    final Resources resources = context.getResources();
    final int circleColor = ContextCompat.getColor(context, R.color.circlHizb);
    final int circleBackground = ContextCompat.getColor(context, R.color.circlHizb_background);
    Typeface font_dana = Typeface.createFromAsset(context.getAssets(),"dana_regular.ttf");

    circlePaint = new Paint();
    circlePaint.setStyle(Paint.Style.FILL);
    circlePaint.setColor(circleColor);
    circlePaint.setAntiAlias(true);

    circleBackgroundPaint = new Paint();
    circleBackgroundPaint.setStyle(Paint.Style.FILL);
    circleBackgroundPaint.setColor(circleBackground);
    circleBackgroundPaint.setAntiAlias(true);

    this.overlayText = overlayText;
    if (!TextUtils.isEmpty(this.overlayText)) {
      final int textColor = ContextCompat.getColor(context, R.color.circlHizb_Number);
      final int textSize =
          resources.getDimensionPixelSize(R.dimen.juz_overlay_text_size);
      overlayTextPaint = new TextPaint();
      overlayTextPaint.setAntiAlias(true);
      overlayTextPaint.setColor(textColor);
      overlayTextPaint.setTypeface(font_dana);
      overlayTextPaint.setTextSize(textSize);
      overlayTextPaint.setTextAlign(Paint.Align.CENTER);

      final float textHeight =
          overlayTextPaint.descent() - overlayTextPaint.ascent();
      textOffset = (textHeight / 2) - overlayTextPaint.descent();
    }

    final int percentage;
    switch (type) {
      case TYPE_JUZ:
        percentage = 100;
        break;
      case TYPE_THREE_QUARTERS:
        percentage = 75;
        break;
      case TYPE_HALF:
        percentage = 50;
        break;
      case TYPE_QUARTER:
        percentage = 25;
        break;
      default:
        percentage = 0;
    }
    this.percentage = percentage;
  }

  @Override
  public void setBounds(int left, int top, int right, int bottom) {
    super.setBounds(left, top, right, bottom);
    radius = (right - left) / 2;
    final int yOffset = ((bottom - top) - (2 * radius)) / 2;
    circleY = radius + yOffset;
    circleRect = new RectF(left, top + yOffset,
        right, top + yOffset + 2 * radius);
  }

  @Override
  public void draw(@NotNull Canvas canvas) {
    canvas.drawCircle(radius, circleY, radius, circleBackgroundPaint);
    canvas.drawArc(circleRect, -90,
        (int) (3.6 * percentage), true, circlePaint);
    if (overlayTextPaint != null) {
      canvas.drawText(overlayText, circleRect.centerX(),
          circleRect.centerY() + textOffset, overlayTextPaint);
    }
  }

  @Override
  public void setAlpha(int alpha) {
  }

  @Override
  public void setColorFilter(ColorFilter cf) {
  }

  @Override
  public int getOpacity() {
    return PixelFormat.TRANSLUCENT;
  }
}
