package com.mano.musicplayer.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by manojperiathambi on 12/14/16.
 */
public class Album implements Serializable {


    private String albumName;
    private String composer;
    private String artist;
    private long albumId;
    private long id;
    private String albumArt;
    private String tracks;
    private List<Track> trackList = new ArrayList<>();

    public Album(long id, String albumArt, long albumId, String albumName, String artist, String tracks, String composer) {
        this.id = id;
        this.albumArt = albumArt;
        this.albumId = albumId;
        this.albumName = albumName;
        this.artist = artist;
        this.tracks = tracks;
        this.composer = composer;
    }

    public String getAlbumArt() {
        return albumArt;
    }

    public String getTracks() {
        return tracks;
    }

    public long getAlbumId() {
        return albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getArtist() {
        return artist;
    }

    public String getComposer() {
        return composer;
    }

    public List<Track> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
    }
}
