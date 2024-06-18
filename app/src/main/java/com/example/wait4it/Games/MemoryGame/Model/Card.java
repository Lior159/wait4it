package com.example.wait4it.Games.MemoryGame.Model;

public class Card {
    private final int imageId; // Identifier for the image/resource
    private boolean isFlipped;
    private int count;
    private boolean isVisible;

    public Card(int imageId) {
        this.imageId = imageId;
        this.isFlipped = false;
        this.count = 0;
        this.isVisible = true;
    }

    public void setVisible(boolean visible)
    {
        this.isVisible = visible;
    }

    public boolean getVisible(){
        return this.isVisible;
    }

    public int getImageId() {
        return imageId;
    }

    public boolean isFlipped() {
        return isFlipped;
    }

    public void setFlipped(boolean flipped) {
        isFlipped = flipped;
    }
    public void incrementCount(){
        count++;
    }
    public int getCount(){
        return this.count;
    }
}

