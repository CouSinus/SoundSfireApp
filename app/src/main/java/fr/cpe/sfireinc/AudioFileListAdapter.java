package fr.cpe.sfireinc;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.cpe.sfireinc.databinding.AudioFileItemBinding;

public class AudioFileListAdapter extends
        RecyclerView.Adapter<AudioFileListAdapter.ViewHolder> {

    List<AudioFile> audioFileList;
    public AudioFileListAdapter(List<AudioFile> fileList) {
        assert fileList != null;
        audioFileList = fileList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AudioFileItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.audio_file_item, parent,false);

        return new ViewHolder(binding);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AudioFile file = audioFileList.get(position);
        holder.viewModel.setAudioFile(file);
    }
    @Override
    public int getItemCount() {
        return audioFileList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private AudioFileItemBinding binding;
        private AudioFileViewModel viewModel = new AudioFileViewModel();
        ViewHolder(AudioFileItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.setAudioFileViewModel(viewModel);
            this.binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Current playing: " + ViewHolder.this.viewModel.getTitle(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
