package com.example.wait4it.Games.MemoryGame.Model;

public class Card {
    private final int primaryImageId;
    private final int secondaryImageId;
    private boolean isFlipped;
    private int count;
    private boolean isVisible;

    public Card(int primaryImageId, int secondaryImageId) {
        this.primaryImageId = primaryImageId;
        this.secondaryImageId = secondaryImageId;
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

    public int getPrimaryImageId() {
        return primaryImageId;
    }
    public int getSecondaryImageId() {
        return secondaryImageId;
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

