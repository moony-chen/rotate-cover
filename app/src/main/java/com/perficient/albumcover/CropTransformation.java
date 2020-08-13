package com.perficient.albumcover;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;


public class CropTransformation extends BitmapTransformation {

    private static final int VERSION = 1;
    private static final String ID = "com.perficient.albumcover.CropTransformation." + VERSION;

    private Croppable photo;


    public CropTransformation(Croppable photo) {
        this.photo = photo;
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        if (photo.thumbnailWidth == 0 || photo.thumbnailHeight == 0 || photo.originalHeight == 0 || photo.originalWidth == 0) {
            return toTransform;
        }

        // Get all the thumbnail and original values as floats to avoid integer division.
        float thumbX = photo.thumbnailX;
        float thumbY = photo.thumbnailY;
        float thumbWidth = photo.thumbnailWidth;
        float thumbHeight = photo.thumbnailHeight;
        float originalWidth = photo.originalWidth;
        float originalHeight = photo.originalHeight;

        // Get the width and height of the bitmap we'll be cropping from.
        float bitmapWidth = toTransform .getWidth();
        float bitmapHeight = toTransform.getHeight();

        // Get the percent distance of the starting points from the edges for the original thumbnail
        float startingLeftPercentage = thumbX / originalWidth;
        float startingTopPercentage = thumbY / originalHeight;

        // Use the starting percentages to get the starting coordinates with respect to the
        // bitmap we're cropping from
        float startingLeft = startingLeftPercentage * bitmapWidth;
        float startingTop = startingTopPercentage * bitmapHeight;

        // Figure out the percentage width/height of the thumbnail with respect to the original image
        float thumbWidthPercentage = thumbWidth / originalWidth;
        float thumbHeightPercentage = thumbHeight / originalHeight;

        // Use that percentage to calculate the width/height to crop from the given bitmap
        float newWidth = thumbWidthPercentage * bitmapWidth;
        float newHeight = thumbHeightPercentage * bitmapHeight;

        // Create a bitmap by cropping out our new width/height begin at our starting coordinates.
        return Bitmap.createBitmap(
                toTransform,
                (int)startingLeft,
                (int)startingTop,
                (int)newWidth,
                (int)newHeight);

    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update((ID + photo.originalHeight+photo.originalWidth+
                photo.thumbnailHeight+photo.thumbnailWidth+
                photo.thumbnailX+photo.thumbnailY).getBytes(CHARSET));
    }
}
