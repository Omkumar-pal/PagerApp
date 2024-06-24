package com.example.paggerapp.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.bumptech.glide.RequestManager;
import com.example.paggerapp.R;
import com.example.paggerapp.adapter.MoviesAdapter;
import com.example.paggerapp.adapter.MoviesLoadStateAdapter;
import com.example.paggerapp.databinding.ActivityMainBinding;
import com.example.paggerapp.uitl.GridSpace;
import com.example.paggerapp.uitl.MovieComparator;
import com.example.paggerapp.uitl.Utils;
import com.example.paggerapp.viewmodel.MovieViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    MovieViewModel mainactivityViewModel;
    ActivityMainBinding binding;
    MoviesAdapter moviesAdapter;

    @Inject
    RequestManager requestManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (Utils.API_KEY == null || Utils.API_KEY.isEmpty()){
            Toast.makeText(this, "Error in api key", Toast.LENGTH_SHORT).show();
        }

        moviesAdapter = new MoviesAdapter(new MovieComparator(), requestManager);
        mainactivityViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        initRecyclerViewAndAdapterView();

        //Subscribe to Paging Data
        mainactivityViewModel.moviePagingDataFlowable.subscribe(moviePagingData -> {
            moviesAdapter.submitData(getLifecycle(), moviePagingData);
        });
    }

    private void initRecyclerViewAndAdapterView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
                binding.recyclerViewMovies.setLayoutManager(gridLayoutManager);
        binding.recyclerViewMovies.addItemDecoration(new GridSpace(4,20,true));
        binding.recyclerViewMovies.setAdapter(
                moviesAdapter.withLoadStateFooter(
                        new MoviesLoadStateAdapter(view->moviesAdapter.retry())
                )
        );
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){

            @Override
            public int getSpanSize(int position) {
                return moviesAdapter.getItemViewType(position) == MoviesAdapter.MOVIE_ITEM ? 1 : 2;
            }
        });






    }
}