package com.lamnn.demoservice;

import android.os.Environment;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class SongManager {

    private static final String MEDIA_PATH = Environment.getExternalStorageDirectory().toString();
    private static final String MEDIA_EXTENSION = ".MP3";


    public SongManager() {
    }

    public ArrayList<Song> getPlayList() {

        Song song;
        File home = new File(MEDIA_PATH);
        ArrayList<Song> songs = new ArrayList<>();
        if (home.listFiles(new FileExtensionFilter()).length > 0) {
            for (File file : home.listFiles(new FileExtensionFilter())) {
                song = new Song();
                song.setPath(file.getPath());
                song.setTitle(file.getName()
                        .substring(0,
                                file.getName().length() - MEDIA_EXTENSION.length()));
                songs.add(song);
            }
        }
        return songs;
    }

    class FileExtensionFilter implements FilenameFilter {
        public boolean accept(File dir, String name) {
            return (name.endsWith(MEDIA_EXTENSION.toLowerCase()) || name.endsWith(MEDIA_EXTENSION));
        }
    }
}
