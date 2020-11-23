package com.mex.mazeescape.model;

public class ImageIndex {

    int imageStartX;
    int imageEndX;
    int imageStartY;
    int imageEndY;

    public ImageIndex(int imageStartX, int imageEndX, int imageStartY, int imageEndY) {
        this.imageStartX = imageStartX;
        this.imageEndX = imageEndX;
        this.imageStartY = imageStartY;
        this.imageEndY = imageEndY;
    }

    public int getImageStartX() {
        return imageStartX;
    }

    public int getImageEndX() {
        return imageEndX;
    }

    public int getImageStartY() {
        return imageStartY;
    }

    public int getImageEndY() {
        return imageEndY;
    }
}
