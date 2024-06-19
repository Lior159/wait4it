package com.example.wait4it.Games.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wait4it.Games.Model.GameItem;
import com.example.wait4it.R;
import com.example.wait4it.Utilities.ImageLoader;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.MyHolder> {

    private final Context context;
    private final ArrayList<GameItem> gamesList;
    private final LayoutInflater inflater;

    public GamesAdapter(Context context, ArrayList<GameItem> games) {
        this.context = context;
        this.gamesList = games;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public GamesAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.main_games_view_item, parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GamesAdapter.MyHolder holder, int position) {
        ImageLoader imageLoader = new ImageLoader(context);
        GameItem gameItem = gamesList.get(position);
        imageLoader.load(gameItem.getIcon(),R.drawable.ic_launcher_background,holder.gameView_IMG_icon);
        holder.gameView_LBL_name.setText(gameItem.getName());
        holder.itemView.setOnClickListener(v->{
            if(gameItem.getActivityClass() != null){
                Intent intent = new Intent(context, gameItem.getActivityClass());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return gamesList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private final ShapeableImageView gameView_IMG_icon;
        private final MaterialTextView gameView_LBL_name;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            gameView_IMG_icon = itemView.findViewById(R.id.gameView_IMG_icon);
            gameView_LBL_name = itemView.findViewById(R.id.gameView_LBL_name);
        }
    }
}
