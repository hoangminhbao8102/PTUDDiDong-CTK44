package com.example.musicapplab12;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicHolder> {
    private final ArrayList<SongEntity> listSong;
    private final Context mContext;
    public MusicAdapter(ArrayList<SongEntity> listSong, Context mContext) {
        this.listSong = listSong;
        this.mContext = mContext;
    }
    @NonNull
    @Override
    public MusicHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_song, parent, false);
        return new MusicHolder(view);
    }
    @Override
    public void onBindViewHolder(MusicAdapter.MusicHolder holder, int position) {
        SongEntity item = listSong.get(position);
        holder.tvName.setText(item.getName());
        holder.tvName.setTag(item);
    }
    @Override
    public int getItemCount() {
        return listSong.size();
    }
    public class MusicHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        @SuppressLint("PrivateResource")
        public MusicHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_song);
            itemView.setOnClickListener(v -> {
                v.startAnimation(AnimationUtils.loadAnimation(mContext,
                        R.anim.abc_fade_in));
                ((MainActivity) mContext).playSong((SongEntity) tvName.getTag());
            });
        }
    }
}
