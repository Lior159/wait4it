package com.example.wait4it.Games.MemoryGame.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wait4it.Games.MemoryGame.Interfaces.TimerControlListener;
import com.example.wait4it.Games.MemoryGame.Logic.MemoryGameLogic;
import com.example.wait4it.Games.MemoryGame.Model.Card;
import com.example.wait4it.R;
import com.example.wait4it.Utilities.ImageLoader;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Card> cards;
    private MemoryGameLogic memoryGameLogic;

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

        ArrayList<Card> generatedCards = new ArrayList<>();
        for (int i = 0; i < numCards/2; i++) {
            String resourceNamePrimary = String.format("memorygame_card%02d", i);
            String resourceNameSecondary = String.format("memorygame_card%02d_temp", i);
            int primaryId = res.getIdentifier(resourceNamePrimary, "drawable", packageName);
            int secondaryId = res.getIdentifier(resourceNameSecondary, "drawable", packageName);
            Log.d("CardAdapter", "resourceNamePrimary: " + resourceNamePrimary + ", Primary ID: " + primaryId);
            Log.d("CardAdapter", "resourceNameSecondary: " + resourceNameSecondary + ", Secondary ID: " + secondaryId);
            generatedCards.add(new Card(primaryId, secondaryId));
            generatedCards.add(new Card(primaryId, secondaryId));
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.memorygame_card_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Card card = cards.get(position);
        ImageLoader imageLoader = new ImageLoader(context);
        if(card.getVisible()){
            if (card.isFlipped())
                imageLoader.load(card.getPrimaryImageId(), card.getSecondaryImageId(), holder.memoryGame_IMG_cardImage);
            else
                imageLoader.load(R.drawable.memorygame_card_back, R.drawable.memorygame_card_back_temp, holder.memoryGame_IMG_cardImage);

            holder.memoryGame_IMG_cardImage.setOnClickListener(v->{
                memoryGameLogic.flipCard(position);
                notifyDataSetChanged();
            });
        }
        else{
            holder.memoryGame_IMG_cardImage.setVisibility(View.INVISIBLE);
            holder.memoryGame_IMG_cardImage.setOnClickListener(null);
        }

    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    /*
        @Override
        public int getCount() {
            return cards.size();
        }

        @Override
        public Object getItem(int position) {
            return cards.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

       @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView == null){
                convertView = inflater.inflate(R.layout.memorygame_card_item, parent,false);
                holder = new ViewHolder();

                convertView.setTag(holder);
            }
            else
                holder = (ViewHolder) convertView.getTag();


            Card card = cards.get(position);
            ImageLoader imageLoader = new ImageLoader(context);
            if(card.getVisible()){
                if(card.isFlipped())
                    imageLoader.load(card.getPrimaryImageId(), card.getSecondaryImageId(), holder.cardImage);
                else
                    imageLoader.load(R.drawable.memorygame_card_back,R.drawable.memorygame_card_back_temp, holder.cardImage);

                convertView.setOnClickListener(v->{
                    memoryGameLogic.flipCard(position);
                    notifyDataSetChanged();
                });
            }
            else
            {
                holder.cardImage.setVisibility(View.INVISIBLE);
                convertView.setOnClickListener(null);
            }
            return convertView;
        }
        */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ShapeableImageView memoryGame_IMG_cardImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            memoryGame_IMG_cardImage = itemView.findViewById(R.id.memoryGame_IMG_cardImage);
        }
    }

}

