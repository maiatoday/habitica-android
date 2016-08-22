package com.habitrpg.android.habitica.network;

import com.habitrpg.android.habitica.models.Task;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by MagicMicky on 10/06/2014.
 */
public interface ApiService {

    @GET("tasks/user")
    Observable<List<Task>> getTasks();

    @GET("tasks/{id}")
    Observable<Task> getTask(@Path("id") String id);

    @POST("tasks/{id}/move/to/{position}")
    Observable<Void> postTaskNewPosition(@Path("id") String id, @Path("position") String position);

    @POST("tasks/{taskId}/checklist/{itemId}/score")
    Observable<Task> scoreChecklistItem(@Path("taskId") String taskId, @Path("itemId") String itemId);

    @POST("tasks/user")
    Observable<Task> createItem(@Body Task item);

    @POST("tasks/user")
    Observable<List<Task>> createTasks(@Body List<Task> tasks);

    @PUT("tasks/{id}")
    Observable<Task> updateTask(@Path("id") String id, @Body Task item);

    @DELETE("tasks/{id}")
    Observable<Void> deleteTask(@Path("id") String id);

}
