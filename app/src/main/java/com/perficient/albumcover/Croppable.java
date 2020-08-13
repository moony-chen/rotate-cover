package com.perficient.albumcover;

public class Croppable {
    int thumbnailX;
    int thumbnailY;
    int thumbnailWidth;
    int thumbnailHeight;
    int originalWidth;
    int originalHeight;

    public Croppable(int thumbnailX, int thumbnailY, int thumbnailWidth, int thumbnailHeight, int originalWidth, int originalHeight) {
        this.thumbnailX = thumbnailX;
        this.thumbnailY = thumbnailY;
        this.thumbnailWidth = thumbnailWidth;
        this.thumbnailHeight = thumbnailHeight;
        this.originalWidth = originalWidth;
        this.originalHeight = originalHeight;
    }

    public int getThumbnailX() {
        return thumbnailX;
    }

    public void setThumbnailX(int thumbnailX) {
        this.thumbnailX = thumbnailX;
    }

    public int getThumbnailY() {
        return thumbnailY;
    }

    public void setThumbnailY(int thumbnailY) {
        this.thumbnailY = thumbnailY;
    }

    public int getThumbnailWidth() {
        return thumbnailWidth;
    }

    public void setThumbnailWidth(int thumbnailWidth) {
        this.thumbnailWidth = thumbnailWidth;
    }

    public int getThumbnailHeight() {
        return thumbnailHeight;
    }

    public void setThumbnailHeight(int thumbnailHeight) {
        this.thumbnailHeight = thumbnailHeight;
    }

    public int getOriginalWidth() {
        return originalWidth;
    }

    public void setOriginalWidth(int originalWidth) {
        this.originalWidth = originalWidth;
    }

    public int getOriginalHeight() {
        return originalHeight;
    }

    public void setOriginalHeight(int originalHeight) {
        this.originalHeight = originalHeight;
    }
}
