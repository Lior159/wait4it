package com.example.wait4it.Games.MemoryGame.Logic;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.wait4it.Games.MemoryGame.Adapters.CardAdapter;
import com.example.wait4it.Games.MemoryGame.Interfaces.TimerControlListener;
import com.example.wait4it.Games.MemoryGame.Model.Card;

import java.util.Arrays;

public class MemoryGameLogic {
    private final int BASE_POINTS = 300;
    private final int EASY_MAX_MATCHES = 6;
    private final int NORMAL_MAX_MATCHES = 8;
    private final int HARD_MAX_MATCHES = 15;
    private int score;
    int pointsDeduction;
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
        setStartingScore();
        this.countMatches = 0;
    }

    private void setStartingScore() {
        switch(maxMatches){
            case EASY_MAX_MATCHES:
                score = BASE_POINTS;
                pointsDeduction = 5;
                break;
            case NORMAL_MAX_MATCHES:
                score = BASE_POINTS*3;
                pointsDeduction = 10;
                break;
            case HARD_MAX_MATCHES:
                score=BASE_POINTS*6;
                pointsDeduction = 15;
                break;
        }

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
            updateScore();

            //adapter.notifyDataSetChanged();
            if(flippedIndices[0] == -1){
                flippedIndices[0] = position;
                adapter.notifyDataSetChanged();
                Log.d("MemoryGameLog","FlippedIndices[0] = " + flippedIndices[0]);
            }
            else if(flippedIndices[1] == -1)
            {

                flippedIndices[1] = position;
                Log.d("MemoryGameLog","FlippedIndices[1] = " + flippedIndices[1]);
                adapter.notifyDataSetChanged();
                checkForMatch();
            }
            else
                return;
        }
    }

    private void updateScore() {
        score-= pointsDeduction;
        if(score<0)
            score=0;
    }

    private void checkForMatch() {
        Card firstCard = (Card) adapter.getItem(flippedIndices[0]);
        Card secondCard = (Card) adapter.getItem(flippedIndices[1]);

        Log.d("MemoryGameLog", "firstCard: " + firstCard.toString());
        Log.d("MemoryGameLog", "secondCard: " + secondCard.toString());

        if (firstCard.getPrimaryImageId() == secondCard.getPrimaryImageId())
        {
            handleMatch(firstCard,secondCard);
            Arrays.fill(flippedIndices, -1);
        }
        else {
            updateScore();
            Handler handler = new Handler();

            handler.postDelayed(()->{
                flipBack();
            }, 400);
        }
        adapter.notifyDataSetChanged();

    }

    private void handleMatch(Card firstCard, Card secondCard) {
        Log.d("MemoryGameLog", "invisibling " +flippedIndices[0]);
        firstCard.setVisible(false);
        Log.d("MemoryGameLog", "invisibling " +flippedIndices[1]);
        secondCard.setVisible(false);
        adapter.notifyItemChanged(flippedIndices[0]);
        adapter.notifyItemChanged(flippedIndices[1]);
        countMatches++;
        if(countMatches == maxMatches)
            timerControlListener.onGameCompleted(getScore());

    }
    private void flipBack() {
        Log.d("MemoryGameLog", "unflipping " +flippedIndices[0]);
        ((Card) adapter.getItem(flippedIndices[0])).setFlipped(false);
        Log.d("MemoryGameLog", "After flip flippedIndices[0]: " + ((Card)(adapter.getItem(flippedIndices[0]))).toString());
        Log.d("MemoryGameLog", "unflipping " +flippedIndices[1]);
        ((Card) adapter.getItem(flippedIndices[1])).setFlipped(false);
        Log.d("MemoryGameLog", "After flip flippedIndices[1]: " + ((Card)(adapter.getItem(flippedIndices[1]))).toString());
        adapter.notifyDataSetChanged();
        Arrays.fill(flippedIndices, -1);
    }

    public int getScore(){
        return this.score;
    }
}