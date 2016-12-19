package com.mano.musicplayer;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mano.musicplayer.model.Album;
import com.mano.musicplayer.model.Track;
import com.squareup.picasso.Picasso;

import java.io.File;

public class AlbumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);


        final Album album = (Album) getIntent().getSerializableExtra("album");
        TextView albumNameBigView = (TextView) findViewById(R.id.AlbumNameBig);
        albumNameBigView.setText(album.getAlbumName());

        final ImageView temp = (ImageView) findViewById(R.id.albumArtBigView);
        Picasso.with(this).load(Uri.fromFile(new File(album.getAlbumArt())))
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(temp);


        final Toolbar playingToolBar = (Toolbar) findViewById(R.id.playing_toolbar);
        playingToolBar.setVisibility(View.GONE );
        /*


        List<Track> trackList = album.getTrackList();

        /*ListView songListView = (ListView) findViewById(R.id.album_track_list);
        songListView.setAdapter(new SongAdapter(this, trackList));
        songListView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getAction() == MotionEvent.ACTION_MOVE);
            }
        });*/


        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.song_listLayout);
        for (final Track track : album.getTrackList()) {

            View view = (View) LayoutInflater.from(this)
                    .inflate(R.layout.album_track_list, null, false);

            TextView songNameView = (TextView) view.findViewById(R.id.song);
            TextView durationView = (TextView) view.findViewById(R.id.duration);
            TextView artistNameView = (TextView) view.findViewById(R.id.artist);
            TextView trackNumView = (TextView) view.findViewById(R.id.trackNumView);

            songNameView.setText(track.getTrackName());
            durationView.setText(track.getDuration());
            artistNameView.setText(track.getArtist());
            trackNumView.setText(String.valueOf(track.getTrackNum()));

            final ImageView playingImageView = (ImageView) findViewById(R.id.albumArt_playing);
            Picasso.with(view.getContext()).load(Uri.fromFile(new File(album.getAlbumArt())))
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(playingImageView);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    playingToolBar.setVisibility(View.VISIBLE );
                    TextView playingSongTitle = (TextView) findViewById(R.id.song_title_playing);
                    TextView playingArtistTitle = (TextView) findViewById(R.id.artist_playing_view);

                    Picasso.with(view.getContext()).load(Uri.fromFile(new File(album.getAlbumArt())))
                            .placeholder(R.mipmap.ic_launcher)
                            .error(R.mipmap.ic_launcher)
                            .into(playingImageView);

                    playingSongTitle.setText(track.getTrackName());
                    playingSongTitle.setSelected(true);


                    playingArtistTitle.setText(track.getArtist());

                }
            });

            linearLayout.addView(view);
        }


    }

}
