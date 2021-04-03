package fr.cpe.sfireinc;

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
        List<AudioFile> fakeList = new ArrayList<>();
        AudioFileListFragmentBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.audio_file_list_fragment,container,false);
        binding.audioFileList.setLayoutManager(new LinearLayoutManager(
                binding.getRoot().getContext()));

        AudioFile Astronaute = new AudioFile();
        Astronaute.setTitle("Astronaute");
        Astronaute.setAlbum("Astronaute - Single");
        Astronaute.setArtist("An'Om & Vayn");
        Astronaute.setGenre("Hip-Hop/Rap");
        Astronaute.setYear(2018);
        Astronaute.setDuration(439);
        fakeList.add(Astronaute);

        AudioFile JulithVsKerubim = new AudioFile();
        JulithVsKerubim.setTitle("Julith Vs Kérubim");
        JulithVsKerubim.setAlbum("Dofus Livre 1: Julith");
        JulithVsKerubim.setArtist("Guillaume Houzé");
        JulithVsKerubim.setGenre("Miscellaneous");
        JulithVsKerubim.setYear(2015);
        JulithVsKerubim.setDuration(295);
        fakeList.add(JulithVsKerubim);

        binding.audioFileList.setAdapter(new AudioFileListAdapter(fakeList));
        return binding.getRoot();
    }
}
