package com.mano.musicplayer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mano.musicplayer.R;
import com.mano.musicplayer.model.Track;

import java.util.List;

/**
 * Created by manojperiathambi on 12/15/16.
 */

public class SongAdapter extends BaseAdapter {

    private List<Track> trackList;
    private Context context;

    public SongAdapter(Context context, List<Track> trackList) {
        this.context = context;
        this.trackList = trackList;
    }

    @Override
    public int getCount() {
        return trackList.size();
    }

    @Override
    public Object getItem(int i) {
        return trackList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if (view == null) {
            view = (View) LayoutInflater.from(this.context)
                    .inflate(R.layout.album_track_list, viewGroup, false);

        }

        TextView songNameView = (TextView) view.findViewById(R.id.song);
        TextView durationView = (TextView) view.findViewById(R.id.duration);

        Track track = trackList.get(i);
        songNameView.setText(track.getTrackName());
        durationView.setText(track.getDuration());

        return view;
    }


}
