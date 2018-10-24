package com.lamnn.demoservice;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;

public class SongManager {

    final String MEDIA_PATH = Environment.getExternalStorageDirectory().toString();
    private ArrayList<HashMap<String, String>> songList = new ArrayList<>();

    public SongManager() {

    }

    public ArrayList<HashMap<String, String>> getPlayList() {

        File home = new File(MEDIA_PATH);
        Log.v("framgia_lam",home.listFiles().length+ "");
        if (home.listFiles(new FileExtensionFilter()).length > 0) {
            for (File file : home.listFiles(new FileExtensionFilter())) {

                HashMap<String, String> song = new HashMap<>();
                song.put("songTitle", file.getName().substring(0, file.getName().length() - 4));
                song.put("songPath", file.getPath());

                songList.add(song);
            }

        }
        return songList;
    }

    class FileExtensionFilter implements FilenameFilter {
        public boolean accept(File dir, String name) {
            return (name.endsWith(".mp3") || name.endsWith(".MP3"));
        }
    }
}