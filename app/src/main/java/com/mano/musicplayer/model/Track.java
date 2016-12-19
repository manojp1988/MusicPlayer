package com.mano.musicplayer.model;

import java.io.Serializable;

/**
 * Created by manojperiathambi on 12/15/16.
 */
public class Track implements Serializable {

    private long id;
    private String trackName;
    private String duration;
    private String artist;
    private int trackNum;
    private String composer;

    public Track(long id, String trackName, int duration, String artist, int trackNum, String composer) {
        this.id = id;
        this.trackName = trackName;

        int mns = (duration / 60000) % 60000;
        int scs = duration % 60000 / 1000;

        this.duration = String.format("%02d:%02d", mns, scs);
        this.artist = artist;
        this.trackNum = trackNum % 1000;
        this.composer = composer;
    }

    public int getTrackNum() {
        return trackNum;
    }

    public String getArtist() {
        return artist;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getDuration() {
        return duration;
    }

    public long getId() {
        return id;
    }

    public String getComposer() {
        return composer;
    }

}
