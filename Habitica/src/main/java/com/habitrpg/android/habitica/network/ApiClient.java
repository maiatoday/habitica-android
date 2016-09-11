package com.habitrpg.android.habitica.network;

import android.support.v7.app.AlertDialog;

import com.amplitude.api.Amplitude;
import com.crashlytics.android.Crashlytics;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.habitrpg.android.habitica.BuildConfig;
import com.habitrpg.android.habitica.models.Task;
import com.habitrpg.android.habitica.models.User;
import com.habitrpg.android.habitica.old.HostConfig;
import com.habitrpg.android.habitica.old.database.CheckListItemExcludeStrategy;
import com.magicmicky.habitrpgwrapper.lib.utils.BooleanAsIntAdapter;
import com.magicmicky.habitrpgwrapper.lib.utils.DateDeserializer;
import com.raizlabs.android.dbflow.structure.ModelAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class ApiClient implements Action1<Throwable> {

    private final ApiService apiService;
    final Observable.Transformer apiCallTransformer =
            observable -> ((Observable) observable).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError(this);
    private final GsonConverterFactory gsonConverter;
    public final HostConfig hostConfig;
    private final Retrofit retrofitAdapter;
    private AlertDialog displayedAlert;

    private String languageCode;

    //private OnHabitsAPIResult mResultListener;
    //private HostConfig mConfig;
    public ApiClient(GsonConverterFactory gsonConverter, HostConfig hostConfig) {
        this.gsonConverter = gsonConverter;
        this.hostConfig = hostConfig;
        Crashlytics.getInstance().core.setUserIdentifier(this.hostConfig.getUser());
        Crashlytics.getInstance().core.setUserName(this.hostConfig.getUser());
        Amplitude.getInstance().setUserId(this.hostConfig.getUser());

        Interceptor remove_data_interceptor = chain -> {
            Response response = chain.proceed(chain.request());
            String stringJson = response.body().string();
            JSONObject jsonObject = null;
            String dataString = null;
            try {
                jsonObject = new JSONObject(stringJson);
                if (jsonObject.has("data")) {
                    dataString = jsonObject.getString("data");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            MediaType contentType = response.body().contentType();
            ResponseBody body = null;

            if (dataString != null) {
                body = ResponseBody.create(contentType, dataString);
            } else {
                body = ResponseBody.create(contentType, stringJson);
            }
            Crashlytics.setString("last_api_call", response.request().url().toString());
            return response.newBuilder().body(body).build();
        };

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        String userAgent = System.getProperty("http.agent");

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(remove_data_interceptor)
                .addInterceptor(logging)
                .addNetworkInterceptor(chain -> {
                    Request original = chain.request();
                    if (this.hostConfig.getUser() != null) {
                        Request.Builder builder = original.newBuilder()
                                .header("x-api-key", this.hostConfig.getApi())
                                .header("x-api-user", this.hostConfig.getUser())
                                .header("x-client", "habitica-android");
                        if (userAgent != null) {
                            builder = builder.header("user-agent", userAgent);
                        }
                        Request request = builder.method(original.method(), original.body())
                                .build();
                        return chain.proceed(request);
                    } else {
                        return chain.proceed(original);
                    }
                })
                .build();

        com.magicmicky.habitrpgwrapper.lib.api.Server server = new com.magicmicky.habitrpgwrapper.lib.api.Server(this.hostConfig.getAddress());
        retrofitAdapter = new Retrofit.Builder()
                .client(client)
                .baseUrl(server.toString())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(gsonConverter)
                .build();
        this.apiService = retrofitAdapter.create(ApiService.class);
    }

    public static GsonConverterFactory createGsonFactory() {
        //Exclusion strategy needed for DBFlow https://github.com/Raizlabs/DBFlow/issues/121
        Gson gson = new GsonBuilder()
                .setExclusionStrategies(new CheckListItemExcludeStrategy())
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaredClass().equals(ModelAdapter.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .registerTypeAdapter(Boolean.class, new BooleanAsIntAdapter())
                .registerTypeAdapter(boolean.class, new BooleanAsIntAdapter())
                .registerTypeAdapter(Date.class, new DateDeserializer())
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create();
        return GsonConverterFactory.create(gson);
    }

    @Override
    public void call(Throwable throwable) {

    }

    @SuppressWarnings("unchecked")
    public <T> Observable.Transformer<T, T> configureApiCallObserver() {
        return (Observable.Transformer<T, T>) apiCallTransformer;
    }

    public Observable<List<Task>> getUserTasks() {
        return this.apiService.getTasks().compose(this.configureApiCallObserver());
    }

    public Observable<User> getUser() {
        return this.apiService.getUser().compose(this.configureApiCallObserver());
    }
}
