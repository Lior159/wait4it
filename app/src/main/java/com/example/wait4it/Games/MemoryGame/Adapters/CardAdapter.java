package com.example.wait4it.Games.MemoryGame.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.wait4it.Games.MemoryGame.Interfaces.TimerControlListener;
import com.example.wait4it.Games.MemoryGame.Logic.MemoryGameLogic;
import com.example.wait4it.Games.MemoryGame.Model.Card;
import com.example.wait4it.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.Collections;

public class CardAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Card> cards;
    private LayoutInflater inflater;
    private MemoryGameLogic memoryGameLogic;

    public CardAdapter(Context context, int numCards, TimerControlListener timerControlListener) {
        this.context = context;
        this.cards = new ArrayList<>();
        this.inflater = LayoutInflater.from(context);
        this.memoryGameLogic = new MemoryGameLogic(this, context, timerControlListener, numCards/2);
        generateCards(numCards);

    }

    private void generateCards(int numCards) {
        int[] images = new int[numCards / 2];
        Resources res = context.getResources();
        String packageName = context.getPackageName();
        for (int i = 0; i < images.length; i++) {
            String resourceName = String.format("memorygame_card%02d", i);
            images[i] = res.getIdentifier(resourceName, "drawable", packageName);
        }
        ArrayList generatedCards = new ArrayList<>();
        for (int i = 0; i < numCards / 2; i++) {
            int imageId = images[i];
            generatedCards.add(new Card(imageId));
            generatedCards.add(new Card(imageId));
        }
        Collections.shuffle(generatedCards);
        this.cards = generatedCards;
    }

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
            holder.cardImage = convertView.findViewById(R.id.memoryGame_IMG_cardImage);
            convertView.setTag(holder);
        }
        else
            holder = (ViewHolder) convertView.getTag();



        Card card = cards.get(position);
        if(card.getVisible()){
            if(card.isFlipped())
                holder.cardImage.setImageResource(card.getImageId());
            else
                holder.cardImage.setImageResource(R.drawable.memorygame_card_back);

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
    public class ViewHolder {
        ShapeableImageView cardImage;
    }

}

