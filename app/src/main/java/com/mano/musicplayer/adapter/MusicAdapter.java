package com.mano.musicplayer.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mano.musicplayer.AlbumActivity;
import com.mano.musicplayer.R;
import com.mano.musicplayer.model.Album;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

/**
 * Created by manojperiathambi on 12/14/16.
 */

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.AlbumViewHolder> {

    private static final String TAG = "MusicAdapter";

    private List<Album> albumList;
    private Context context;

    public MusicAdapter(Context context, List<Album> albumList) {
        this.context = context;
        this.albumList = albumList;
    }

    public void setAlbumList(List<Album> albumList) {
        this.albumList.clear();
        this.albumList.addAll(albumList);
    }

    @Override
    public MusicAdapter.AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_item, parent, false);
        AlbumViewHolder albumViewHolder = new AlbumViewHolder(view);
        return albumViewHolder;
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {
        Album album = albumList.get(position);
        holder.albumNameView.setText(album.getAlbumName());

        holder.composerView.setText(album.getComposer());

//        Bitmap bm= BitmapFactory.decodeFile(album.getAlbumArt());
//        holder.albumArtView.setImageBitmap(bm);

        Picasso.with(context).load(Uri.fromFile(new File(album.getAlbumArt())))
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)

                .into(holder.albumArtView);

    }

    @Override
    public int getItemCount() {

        return albumList != null ? albumList.size() : 0;
    }


    public class AlbumViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView albumArtView;
        public TextView albumNameView;
        public TextView composerView;

        public AlbumViewHolder(View view) {
            super(view);
            this.albumArtView = (ImageView) view.findViewById(R.id.albumView);
            this.albumNameView = (TextView) view.findViewById(R.id.albumTitleView);
            this.composerView = (TextView) view.findViewById(R.id.composerView);
            view.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            Intent albumIntent = new Intent(context, AlbumActivity.class);
            albumIntent.putExtra("album", albumList.get(getAdapterPosition()));
            context.startActivity(albumIntent);

            //Toast.makeText(context, "Item clicked", Toast.LENGTH_SHORT).show();
        }
    }
}
