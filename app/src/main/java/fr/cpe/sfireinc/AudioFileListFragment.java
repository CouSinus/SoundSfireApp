package fr.cpe.sfireinc;

// Useful Link -> https://developer.android.com/training/data-storage/shared/media

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
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
import java.util.Objects;

import fr.cpe.sfireinc.databinding.AudioFileListFragmentBinding;

public class AudioFileListFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        List<AudioFile> audioList = new ArrayList<>();

        AudioFileListFragmentBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.audio_file_list_fragment,container,false);
        binding.audioFileList.setLayoutManager(new LinearLayoutManager(
                binding.getRoot().getContext()));


        List<Uri> uriAudio = new ArrayList<>();

        uriAudio.add(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
        uriAudio.add(MediaStore.Audio.Media.INTERNAL_CONTENT_URI);

        Log.e("InfoUri", uriAudio.toString());

        String[] audioProjection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.ARTIST,
                //MediaStore.Audio.Media.GENRE,
                MediaStore.Audio.Media.YEAR,
        };

        for (Uri uri : uriAudio) {
            Log.e("InfoUri", uri.toString());
            Cursor cursor = Objects.requireNonNull(this.getContext()).getContentResolver().query(uri, audioProjection, null, null, null);

            assert cursor != null;
            int titleColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE);
            int durationColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION);
            int dataColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
            int albumColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM);
            int artistColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.ARTIST);
            //int genreColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.GENRE);
            int yearColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.YEAR);

            while (cursor.moveToNext()) {
                /*
                String path = cursor.getString(0);
                Uri audioFileUri = ContentUris.withAppendedId(
                        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        cursor.getLong(1));
                        **/

                audioList.add(new AudioFile.AudioBuilder(cursor.getString(titleColumn), cursor.getInt(durationColumn), cursor.getString(dataColumn))
                        .album(cursor.getString(albumColumn))
                        .artist(cursor.getString(artistColumn))
                        //.genre(cursor.getString(genreColumn))
                        .year(cursor.getInt(yearColumn))
                        .build()
                );
            }

            cursor.close();
        }

        binding.audioFileList.setAdapter(new AudioFileListAdapter(audioList));
        return binding.getRoot();
    }
}