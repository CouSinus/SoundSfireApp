package fr.cpe.sfireinc;

import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import fr.cpe.sfireinc.databinding.AudioFileListFragmentBinding;

public class AudioFileListFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        List<AudioFile> audioList = new ArrayList<>();

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI; // La carte SD
        String[] projection = {MediaStore.Audio.Media.DATA, MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.ALBUM, MediaStore.Audio.Media.DURATION
        , MediaStore.Audio.Media.RELATIVE_PATH, MediaStore.Audio.Media.YEAR};
        //chemin du fichier, ID, titre, artist
        Cursor cursor = getContext().getContentResolver().query(uri,projection,null,null,null);

        if(cursor != null){
            AudioFile audio = new AudioFile();
            while(cursor.moveToNext()) {
                cursor.getString(0);
                audio.setAlbum(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)));
                audio.setArtist(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)));
                audio.setDuration(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)));
                audio.setFilePath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.RELATIVE_PATH)));
                audio.setGenre(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.GENRE)));
                audio.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)));
                audio.setYear(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.YEAR)));

                audioList.add(audio);
            }
        }


        AudioFileListFragmentBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.audio_file_list_fragment,container,false);
        binding.audioFileList.setLayoutManager(new LinearLayoutManager(
                binding.getRoot().getContext()));
        binding.audioFileList.setAdapter(new AudioFileListAdapter(audioList));
        return binding.getRoot();
    }
}
