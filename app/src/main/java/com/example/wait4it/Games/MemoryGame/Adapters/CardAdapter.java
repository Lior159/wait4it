package com.example.wait4it.Games.MemoryGame.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wait4it.Games.MemoryGame.Interfaces.TimerControlListener;
import com.example.wait4it.Games.MemoryGame.Logic.MemoryGameLogic;
import com.example.wait4it.Games.MemoryGame.Model.Card;
import com.example.wait4it.R;
import com.example.wait4it.Utilities.ImageLoader;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.Collections;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Card> cards;
    private MemoryGameLogic memoryGameLogic;
    private boolean isTryFindMatch = false;

    public CardAdapter(Context context, int numCards, TimerControlListener timerControlListener) {
        this.context = context;
        this.cards = new ArrayList<>();
        this.memoryGameLogic = new MemoryGameLogic(this, context, timerControlListener, numCards/2);
        generateCards(numCards);

    }

    public Card getItem(int position){
        return cards.get(position);
    }

    private void generateCards(int numCards) {
        Resources res = context.getResources();
        String packageName = context.getPackageName();

        cards = new ArrayList<>();
        for (int i = 0; i < numCards/2; i++) {
            String resourceNamePrimary = String.format("memorygame_card%02d", i);
            String resourceNameSecondary = String.format("memorygame_card%02d_temp", i);
            int primaryId = res.getIdentifier(resourceNamePrimary, "drawable", packageName);
            int secondaryId = res.getIdentifier(resourceNameSecondary, "drawable", packageName);
            Log.d("CardAdapter", "resourceNamePrimary: " + resourceNamePrimary + ", Primary ID: " + primaryId);
            Log.d("CardAdapter", "resourceNameSecondary: " + resourceNameSecondary + ", Secondary ID: " + secondaryId);
            cards.add(new Card(primaryId, secondaryId));
            cards.add(new Card(primaryId, secondaryId));
        }
        Collections.shuffle(cards);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.memorygame_card_item, parent, false);

        int spanCount = ((GridLayoutManager) ((RecyclerView)parent).getLayoutManager()).getSpanCount();
        int numOfRows = cards.size() / spanCount;
        //int itemWidth = parent.getWidth() / spanCount;
        int itemHeight = parent.getHeight() / numOfRows;

        int width = parent.getWidth() - 100;
        int div = (int) Math.floor(Math.sqrt(cards.size()));
        width/=div;


        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();

        //layoutParams.width = (int)(itemHeight*2.5/3.5);
        layoutParams.height = (int)((width / 79) * 127.5);
        view.setLayoutParams(layoutParams);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("CA", "onBindViewHolder: setting "+position);
        Card card = cards.get(position);
        ImageLoader imageLoader = new ImageLoader(context);

//        holder.memoryGame_IMG_cardImage.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//            @Override
//            public boolean onPreDraw() {
//                holder.memoryGame_IMG_cardImage.getViewTreeObserver().removeOnPreDrawListener(this);
//                int temp = (int) holder.memoryGame_IMG_cardImage.getWidth();
//                holder.memoryGame_IMG_cardImage.setMaxHeight(50);
//                return false;
//            }
//        });

        if(card.getVisible()){
            if (card.isFlipped())
                imageLoader.load(card.getPrimaryImageId(), card.getSecondaryImageId(), holder.memoryGame_IMG_cardImage);
            else
                imageLoader.load(R.drawable.card_back, R.drawable.memorygame_card_back_temp, holder.memoryGame_IMG_cardImage);

            holder.memoryGame_IMG_cardImage.setOnClickListener(v->{
                if(!isTryFindMatch)
                {
                    isTryFindMatch = true;
                    memoryGameLogic.flipCard(position);
                    notifyDataSetChanged();
                }
                else
                {
                    disableAllCards(holder);
                    memoryGameLogic.flipCard(position);
                    notifyDataSetChanged();

                    new Handler().postDelayed(()->{
                        enableAllCards();
                    },400);



                    isTryFindMatch = false;
                }
            });
        }
        else{
            holder.memoryGame_IMG_cardImage.setVisibility(View.INVISIBLE);
            holder.memoryGame_IMG_cardImage.setOnClickListener(null);
        }

    }

    private void enableAllCards() {
        for (int i = 0; i < cards.size(); i++) {
            Card cardTemp = cards.get(i);
            if(!cardTemp.getVisible())
                notifyItemChanged(i);
        }

    }


    private void disableAllCards(ViewHolder holder){
        for (int i = 0; i < cards.size(); i++) {
            holder.itemView.setClickable(false);
        }
    }
    
    

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ShapeableImageView memoryGame_IMG_cardImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            memoryGame_IMG_cardImage = itemView.findViewById(R.id.memoryGame_IMG_cardImage);
        }
    }

}

