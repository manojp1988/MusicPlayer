package com.mano.musicplayer.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mano.musicplayer.R;
import com.mano.musicplayer.adapter.MusicAdapter;
import com.mano.musicplayer.loader.MusicLoader;
import com.mano.musicplayer.model.Album;

import java.util.ArrayList;
import java.util.List;


public class AlbumFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Album>> {

    private static final String TAG = "AlbumFragment";

    private List<Album> albumList;
    private MusicAdapter musicAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_album, container, false);
        this.getLoaderManager().initLoader(1, null, this).forceLoad();

        RecyclerView albumRecyclerView = (RecyclerView) rootView.findViewById(R.id.album_list_view);

        int orientation=this.getResources().getConfiguration().orientation;
        if(orientation== Configuration.ORIENTATION_PORTRAIT){
            //code for portrait mode
            albumRecyclerView.setLayoutManager(new GridLayoutManager(rootView.getContext(), 2));
        }
        else{
            //code for landscape mode
            albumRecyclerView.setLayoutManager(new GridLayoutManager(rootView.getContext(), 3));
        }

        musicAdapter = new MusicAdapter(rootView.getContext(), new ArrayList<Album>());
        albumRecyclerView.setAdapter(musicAdapter);


        return rootView;
    }

    @Override
    public Loader<List<Album>> onCreateLoader(int id, Bundle args) {
        return new MusicLoader(this.getContext());
    }

    @Override
    public void onLoadFinished(Loader<List<Album>> loader, List<Album> data) {
        if (data != null) {
            albumList = data;
            musicAdapter.setAlbumList(albumList);
            musicAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Album>> loader) {
        if (albumList != null)
            albumList.clear();
    }
}
