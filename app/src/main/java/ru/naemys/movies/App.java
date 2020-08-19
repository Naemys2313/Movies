package ru.naemys.movies;

import android.app.Application;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.naemys.movies.services.MovieService;

public class App extends Application {
    private static App instance;

    private MovieService movieService;

    public static App getInstance() {
        return instance;
    }

    public MovieService getMovieService() {
        return movieService;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        initRetrofit();
    }

    private void initRetrofit() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder()
                                .addHeader("X-API-KEY",
                                        "000bd7fe-15b3-4df9-b247-55e1aff53dbd")
                                .build();

                        return chain.proceed(request);
                    }
                })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://kinopoiskapiunofficial.tech/")
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        movieService = retrofit.create(MovieService.class);
    }
}
