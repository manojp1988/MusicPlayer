package com.mano.musicplayer.loader;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.mano.musicplayer.model.Album;
import com.mano.musicplayer.model.Track;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by manojperiathambi on 12/14/16.
 */

public class MusicLoader extends AsyncTaskLoader<List<Album>> {

    private static final String TAG = "MusicLoader";
    private boolean isLoading = false;
    private Context context;

    public MusicLoader(Context context) {
        super(context);
        this.context = context;
    }


    @Override
    public List<Album> loadInBackground() {

        Log.d(TAG, "loadInBackground: ");

        List<Album> albumList = new ArrayList<>();

        String where = null;

        final Uri uri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
        final String _id = MediaStore.Audio.Albums._ID;
        final String album_name = MediaStore.Audio.Albums.ALBUM;
        final String artist_name = MediaStore.Audio.Albums.ARTIST;
        final String albumart = MediaStore.Audio.Albums.ALBUM_ART;
        final String tracks_col = MediaStore.Audio.Albums.NUMBER_OF_SONGS;

        final String[] columns = {_id, album_name, artist_name, albumart, tracks_col};
        Cursor cursor = context.getContentResolver().query(uri, null, where,
                null, null);


        if (cursor.moveToFirst()) {
            do {

                long id = cursor.getLong(cursor.getColumnIndex(_id));

                String albumName = cursor.getString(cursor
                        .getColumnIndex(album_name));

                String artist = cursor.getString(cursor
                        .getColumnIndex(artist_name));

                String albumArtPath = cursor.getString(cursor
                        .getColumnIndex(albumart));

                String tracks = cursor.getString(cursor
                        .getColumnIndex(tracks_col));

                List<Track> trackList = getTrackList(context, id);

                Album album = new Album(id, albumArtPath, id, albumName, artist, tracks, trackList.get(0).getComposer());
                album.setTrackList(trackList);

                albumList.add(album);

            } while (cursor.moveToNext());

            cursor.close();
        }

        Collections.sort(albumList, new Comparator<Album>() {
            @Override
            public int compare(Album a1, Album a2) {
                return a1.getAlbumName().compareTo(a2.getAlbumName());
            }
        });

        return albumList;
    }


    @Override
    public void forceLoad() {
        if (!isLoading) {
            super.forceLoad();
            isLoading = !isLoading;
        }
    }


    public List<Track> getTrackList(Context context, long albumId) {

        List<Track> trackList = new ArrayList<>();

        String where = null;

        final Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        final String _id = MediaStore.Audio.Media._ID;
        final String album_id = MediaStore.Audio.Media.ALBUM_ID;
        final String trackNumCol = MediaStore.Audio.Media.TRACK;
        final String artistCol = MediaStore.Audio.Media.ARTIST;
        final String durationCol = MediaStore.Audio.Media.DURATION;
        final String songNameCol = MediaStore.Audio.Media.DISPLAY_NAME;
        final String composerCol = MediaStore.Audio.Media.COMPOSER;

        final String[] columns = {_id, durationCol, songNameCol, trackNumCol, artistCol, composerCol};

        Cursor cursor = context.getContentResolver().query(uri, null, "album_id = " + albumId,
                null, null);


        if (cursor.moveToFirst()) {
            do {

                long id = cursor.getLong(cursor.getColumnIndex(_id));
                int duration = cursor.getInt(cursor.getColumnIndex(durationCol));
                String songName = cursor.getString(cursor.getColumnIndex(songNameCol));
                String artistDetails = cursor.getString(cursor.getColumnIndex(artistCol));
                int trackNum = cursor.getInt(cursor.getColumnIndex(trackNumCol));
                String composer = cursor.getString(cursor.getColumnIndex(composerCol));

                trackList.add(new Track(id, songName, duration, artistDetails, trackNum, composer));

            } while (cursor.moveToNext());

            cursor.close();
        }

        Collections.sort(trackList, new Comparator<Track>() {
            @Override
            public int compare(Track track, Track t1) {
                return Integer.compare(track.getTrackNum(), t1.getTrackNum());
            }
        });

        return trackList;
    }

}
