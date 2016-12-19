package com.mano.musicplayer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mano.musicplayer.R;

/**
 * Created by manojperiathambi on 12/16/16.
 */

public class ArtistFragment extends Fragment {

    private static final String TAG = "ArtistFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_artist, container, false);

        return rootView;
    }

}
