package com.example.retrofitrxjavademo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.retrofitrxjavademo.model.Language;
import com.example.retrofitrxjavademo.model.Movie;
import com.example.retrofitrxjavademo.model.MovieResopnse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private final static String API_KEY = "2b054de6d61b4c4e590162f41c45bd80";
    private final static String IMAGE_URL = "https://image.tmdb.org/t/p/w500/";

    ApiInterface apiInterface;
    List<Movie> movies;
    List<Language> languageList;

    int item = 1;
    int count = 1;

    ImageView image;
    TextView title, subtitle, description, rating, release_date, language, tv_count;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = findViewById(R.id.image);

        tv_count = findViewById(R.id.tv_count);

        title = findViewById(R.id.title);
        subtitle = findViewById(R.id.subtitle);
        description = findViewById(R.id.description);
        rating = findViewById(R.id.rating);
        release_date = findViewById(R.id.release_date);
        language = findViewById(R.id.language);

        tv_count.setText("Movie: " + item);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        compositeDisposable.add(Observable.interval(2, TimeUnit.SECONDS)
                .flatMap(n -> apiInterface.getTopRatedMovie(API_KEY))
                .repeat()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieResopnse>() {
                    @Override
                    public void accept(MovieResopnse movieResopnse) throws Exception {
                        movies = movieResopnse.getResults();
                        Log.d(TAG, "onResponse: Number of movies received: " + movies.size());

                        if (item == 19)
                            item = 1;

                        tv_count.setText("Movie: " + item);
                        item++;
                        Picasso.get()
                                .load(IMAGE_URL + movies.get(item).getPosterPath())
                                .into(image);

                        title.setText(movies.get(item).getOriginalTitle());
                        subtitle.setText(movies.get(item).getTitle());
                        description.setText(movies.get(item).getOverview());
                        rating.setText("" + movies.get(item).getVoteAverage());
                        release_date.setText("Release: " + movies.get(item).getReleaseDate());
                    }
                }, this::onError));


      /*  final Call<List<Language>> callLanguage = apiInterface.getLanguages(API_KEY);

        Call<MovieResopnse> call = apiInterface.getTopRatedMovie(API_KEY);
        call.enqueue(new Callback<MovieResopnse>() {
            @Override
            public void onResponse(Call<MovieResopnse> call, Response<MovieResopnse> response) {
                movies = response.body().getResults();
                Log.d(TAG, "onResponse: Number of movies received: " + movies.size());

                Picasso.get()
                        .load(IMAGE_URL + movies.get(item).getPosterPath())
                        .into(image);

                title.setText(movies.get(item).getOriginalTitle());
                subtitle.setText(movies.get(item).getTitle());
                description.setText(movies.get(item).getOverview());
                rating.setText("" + movies.get(item).getVoteAverage());
                release_date.setText("Release: " + movies.get(item).getReleaseDate());

                // Request another api after getting language code from the first one.
                callLanguage.enqueue(new Callback<List<Language>>() {
                    @Override
                    public void onResponse(Call<List<Language>> call, Response<List<Language>> response) {
//                languageList = new ArrayList<>();
                        Log.d(TAG, " Request url : " + call.request().url().toString());
                        languageList = response.body();

                        for (Language language1 : languageList) {
                            if (language1.getIso6391().contains(movies.get(item).getOriginalLanguage()))
                                language.setText(language1.getEnglishName());
                        }
                        Log.d(TAG, "onResponse: " + languageList.size() + " Languages");
//                Log.d(TAG, "onResponse: "+languageList.get(0).getEnglishName());
                    }

                    @Override
                    public void onFailure(Call<List<Language>> call, Throwable t) {
                        // Log error here since request failed
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onFailure: " + t.getMessage());
                        Log.d(TAG, "onFailure: request url : " + call.request().url().toString());
                    }
                });


            }

            @Override
            public void onFailure(Call<MovieResopnse> call, Throwable t) {
                // Log error here since request failed
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: " + t.getMessage());
                Log.d(TAG, "onFailure: request url : " + call.request().url().toString());

            }
        });*/


    }

    private void onError(Throwable throwable) {
        Toast.makeText(this, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onError: " + throwable.getMessage());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}