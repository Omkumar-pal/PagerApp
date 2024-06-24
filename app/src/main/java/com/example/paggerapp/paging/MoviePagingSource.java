package com.example.paggerapp.paging;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingSource;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.RxPagingSource;

import com.example.paggerapp.api.APIClient;
import com.example.paggerapp.model.Movie;
import com.example.paggerapp.model.MovieResponse;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MoviePagingSource extends RxPagingSource<Integer, Movie> {
    @NonNull
    @Override
    public Single<LoadResult<Integer, Movie>> loadSingle(@NonNull LoadParams<Integer> loadParams) {
        try {
            int page = loadParams.getKey() != null ? loadParams.getKey() : 1;
            return APIClient.getApiInterface().getMoviesByPage(page)
                    .subscribeOn(Schedulers.io())
                    .map(MovieResponse::getResults)
                    .map(movies -> toLoadResult(movies, page))
                    .onErrorReturn(LoadResult.Error::new); // Corrected line
        } catch (Exception e) {
            return Single.just(new LoadResult.Error(e));
        } // Removed extra parenthesis
    }

    @Nullable
    @Override
    public Integer getRefreshKey(@NonNull PagingState<Integer, Movie> pagingState) {
        return 0;
    }

    private PagingSource.LoadResult<Integer, Movie> toLoadResult(List<Movie> movies, int page) {
        return new PagingSource.LoadResult.Page(movies, page == 1 ? null : page - 1, page + 1);
    }
}