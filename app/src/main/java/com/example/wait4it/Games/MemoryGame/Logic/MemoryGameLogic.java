package com.example.wait4it.Games.MemoryGame.Logic;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.example.wait4it.Games.MemoryGame.Adapters.CardAdapter;
import com.example.wait4it.Games.MemoryGame.Interfaces.TimerControlListener;
import com.example.wait4it.Games.MemoryGame.Model.Card;

import java.util.Arrays;

public class MemoryGameLogic {
    private int[] flippedIndices;
    private CardAdapter adapter;
    private Context context;
    private TimerControlListener timerControlListener;
    private boolean isTimeStarted;
    private int maxMatches;
    private int countMatches;

    public MemoryGameLogic(CardAdapter adapter, Context context, TimerControlListener timerControlListener, int maxMatches) {
        this.adapter = adapter;
        this.context = context;
        this.flippedIndices = new int[2];
        Arrays.fill(flippedIndices, -1);
        this.timerControlListener = timerControlListener;
        isTimeStarted = false;
        this.maxMatches = maxMatches;
        this.countMatches = 0;
    }
    public void flipCard(int position)
    {
        if(!isTimeStarted)
        {
            timerControlListener.startTimer();
            isTimeStarted = true;
        }

        Card card = (Card) adapter.getItem(position);
        if(card.isFlipped()){ // already revealed
            Toast.makeText(context, "Card already revealed",Toast.LENGTH_SHORT).show();
        }
        else // card is hidden
        {
            card.setFlipped(true);
            card.incrementCount();
            //adapter.notifyDataSetChanged();
            if(flippedIndices[0] == -1){
                flippedIndices[0] = position;
                adapter.notifyDataSetChanged();
            }
            else if(flippedIndices[1] == -1)
            {
                flippedIndices[1] = position;
                adapter.notifyDataSetChanged();
                checkForMatch();
            }
            else
                return;
        }
    }

    private void checkForMatch() {
        Card firstCard = (Card) adapter.getItem(flippedIndices[0]);
        Card secondCard = (Card) adapter.getItem(flippedIndices[1]);

        if (firstCard.getPrimaryImageId() == secondCard.getPrimaryImageId())
        {
            handleMatch();
            Arrays.fill(flippedIndices, -1);
        }
        else {
            Handler handler = new Handler();
            handler.postDelayed(()->{
                flipBack();
            }, 400);
        }

    }

    private void handleMatch() {
        Card firstCard = (Card) adapter.getItem(flippedIndices[0]);
        Card secondCard = (Card) adapter.getItem(flippedIndices[1]);

        firstCard.setVisible(false);
        secondCard.setVisible(false);
        countMatches++;
        if(countMatches == maxMatches)
            timerControlListener.onGameCompleted();
        adapter.notifyDataSetChanged();
    }
    private void flipBack() {
        ((Card) adapter.getItem(flippedIndices[0])).setFlipped(false);
        ((Card) adapter.getItem(flippedIndices[1])).setFlipped(false);
        adapter.notifyDataSetChanged();
        Arrays.fill(flippedIndices, -1);
    }
}