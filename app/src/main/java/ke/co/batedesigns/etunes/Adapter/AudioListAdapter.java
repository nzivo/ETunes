package ke.co.batedesigns.etunes.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ke.co.batedesigns.etunes.MainActivity;
import ke.co.batedesigns.etunes.Model.AudioModel;
import ke.co.batedesigns.etunes.R;

public class AudioListAdapter extends RecyclerView.Adapter<AudioListAdapter.AudioViewHolder> {
    private List<AudioModel> audioDataSet;
    private LayoutInflater mInflater;
    Context mContext;

    public AudioListAdapter(Context context, List<AudioModel> audioModelList){

        mInflater = LayoutInflater.from(context);
        mContext = context;
        audioDataSet = audioModelList;
    }

    @NonNull
    @Override
    public AudioViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // create a new view
        View v = mInflater.inflate(R.layout.audio_list_row, viewGroup, false);

        AudioViewHolder vh = new AudioViewHolder(mContext,audioDataSet,v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AudioViewHolder audioViewHolder, int i) {
        String album = "Album: ";
        audioViewHolder.mTextView.setText(audioDataSet.get(i).getaName());
        audioViewHolder.artistTextView.setText(audioDataSet.get(i).getaArtist());
        audioViewHolder.albumTextView.setText(audioDataSet.get(i).getaAlbum());
    }

    @Override
    public int getItemCount() {
        return audioDataSet.size();
    }

    public static class AudioViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        Context nContext;
        List<AudioModel> audioList;
        public TextView mTextView;
        public TextView albumTextView;
        public TextView artistTextView;
        public TextView yearTextView;

        public AudioViewHolder(Context context,List<AudioModel> audioModelList, View v) {
            super(v);
            nContext = context;
            audioList = audioModelList;
            mTextView = v.findViewById(R.id.audioName);
            albumTextView = v.findViewById(R.id.albumName);
            artistTextView = v.findViewById(R.id.artistName);
            yearTextView = v.findViewById(R.id.year);

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){

            int itemPosition = getAdapterPosition();

            Intent intent = new Intent(nContext, MainActivity.class);
            intent.putExtra("audio",audioList.get(itemPosition));

            nContext.startActivity(intent);

        }
    }
}
