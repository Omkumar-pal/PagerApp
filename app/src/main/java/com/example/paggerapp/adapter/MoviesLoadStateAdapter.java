package com.example.paggerapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.LoadState;
import androidx.paging.LoadStateAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paggerapp.R;
import com.example.paggerapp.databinding.LoadStateItemBinding;

public class MoviesLoadStateAdapter extends LoadStateAdapter<MoviesLoadStateAdapter.LoadStateViewHolder> {

    private View.OnClickListener mRetryCallback;
    public MoviesLoadStateAdapter(@NonNull View.OnClickListener mRetryCallback) {
        this.mRetryCallback = mRetryCallback;
    }

    @Override
    public void onBindViewHolder(@NonNull LoadStateViewHolder loadStateViewHolder, @NonNull LoadState loadState) {
        loadStateViewHolder.bind(loadState);
    }

    @NonNull
    @Override
    public LoadStateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, @NonNull LoadState loadState) {
        return new LoadStateViewHolder(parent, mRetryCallback);
    }


    public static class LoadStateViewHolder extends RecyclerView.ViewHolder{
        private ProgressBar mProgressBar;
        private TextView mErrorMsg;
        private Button mRetry;

        public LoadStateViewHolder(@NonNull ViewGroup parent, @NonNull View.OnClickListener retryCallback) {

            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.load_state_item, parent, false));
            LoadStateItemBinding loadStateItemBinding = LoadStateItemBinding.bind(itemView);
            mProgressBar = loadStateItemBinding.progressBar;
            mErrorMsg = loadStateItemBinding.errorMsg;
            mRetry = loadStateItemBinding.retryButton;
            mRetry.setOnClickListener(retryCallback);
        }
        public void bind(LoadState loadState){
            if (loadState instanceof LoadState.Error){
                LoadState.Error LoadStateError = (LoadState.Error) loadState;
                mErrorMsg.setText(LoadStateError.getError().getLocalizedMessage());

            }
             mProgressBar.setVisibility(loadState instanceof LoadState.Loading ? View.VISIBLE : View.GONE);
            mErrorMsg.setVisibility(loadState instanceof LoadState.Error ? View.VISIBLE : View.GONE);
            mProgressBar.setVisibility(loadState instanceof LoadState.Error ? View.VISIBLE : View.GONE);
        }
    }
}
