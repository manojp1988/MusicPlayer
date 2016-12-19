package com.mano.musicplayer.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mano.musicplayer.fragment.AlbumFragment;
import com.mano.musicplayer.fragment.ArtistFragment;

/**
 * Created by manojperiathambi on 12/17/16.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new AlbumFragment();
            case 1:
                return new ArtistFragment();

        }
        return null;
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Album";
            case 1:
                return "Artist";

        }
        return null;
    }
}
