package com.ermile.page.common.draw;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&\u00a8\u0006\n"}, d2 = {"Lcom/ermile/page/common/draw/ImageDrawHelper;", "", "draw", "", "pageCoordinates", "Lcom/ermile/page/common/data/PageCoordinates;", "canvas", "Landroid/graphics/Canvas;", "image", "Landroid/widget/ImageView;", "pages_debug"})
public abstract interface ImageDrawHelper {
    
    public abstract void draw(@org.jetbrains.annotations.NotNull()
    com.ermile.page.common.data.PageCoordinates pageCoordinates, @org.jetbrains.annotations.NotNull()
    android.graphics.Canvas canvas, @org.jetbrains.annotations.NotNull()
    android.widget.ImageView image);
}