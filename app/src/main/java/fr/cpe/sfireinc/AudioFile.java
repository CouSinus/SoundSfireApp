package fr.cpe.sfireinc;

// Usefull link -> https://howtodoinjava.com/design-patterns/creational/builder-pattern-in-java/

import java.util.Locale;

public class AudioFile {

    // Final attributes
    private final String title; // Required
    private final int duration; // Required
    private final String filePath; // Required
    private final String artist; // Optional
    private final String album; // Optional
    private final String genre; // Optional
    private final int year; // Optional

    private AudioFile(AudioBuilder builder){
        this.title = builder.title;
        this.duration = builder.duration;
        this.filePath = builder.filePath;
        this.artist = builder.artist;
        this.album = builder.album;
        this.genre = builder.genre;
        this.year = builder.year;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public String getDurationText() {
        int second = (duration / 1000) % 60;
        int durationMinute = ((duration / 1000) - second) / 60;
        int minute = durationMinute % 60;
        int hour = (durationMinute - minute) / 60;
        if(hour > 0)
            return String.format(Locale.getDefault(),
                    "%02d:%02d:%02d",hour,minute,second);
        return String.format(Locale.getDefault(),
                "%02d:%02d",minute,second);
    }

    public static class AudioBuilder {
        private final String title;
        private final int duration;
        private final String filePath;
        private String artist;
        private String album;
        private String genre;
        private int year;

        public AudioBuilder(String title, int duration, String filePath){
            this.title = title;
            this.duration = duration;
            this.filePath = filePath;
        }

        public AudioBuilder artist(String artist){
            this.artist = artist;
            return this;
        }

        public AudioBuilder album(String album){
            this.album = album;
            return this;
        }

        public AudioBuilder genre(String genre){
            this.genre = genre;
            return this;
        }

        public AudioBuilder year(int year){
            this.year = year;
            return this;
        }

        public AudioFile build() {
            AudioFile audio = new AudioFile(this);
            return audio;
        }
    }
}