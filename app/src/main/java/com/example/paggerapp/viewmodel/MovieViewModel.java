package com.example.paggerapp.viewmodel;

import static com.bumptech.glide.Glide.init;

import androidx.lifecycle.ViewModel;
import com.example.paggerapp.model.Movie;
import com.example.paggerapp.paging.MoviePagingSource;

import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;

import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import io.reactivex.rxjava3.core.Flowable;
import kotlinx.coroutines.CoroutineScope;

public class MovieViewModel extends ViewModel {

    public Flowable<PagingData<Movie>> moviePagingDataFlowable;
    
    public MovieViewModel() {
        init();
    }

    private void init() {

        // Defining paging source
        MoviePagingSource moviePagingSource = new MoviePagingSource();
        Pager<Integer, Movie> pager = new Pager<>(new PagingConfig(20,20, false,20, 20*499)
                , () -> moviePagingSource);

        // Getting flowable
        moviePagingDataFlowable = PagingRx.getFlowable(pager);
        CoroutineScope couroutineScope = ViewModelKt.getViewModelScope(this);
        PagingRx.cachedIn(moviePagingDataFlowable, couroutineScope);
    }

}
