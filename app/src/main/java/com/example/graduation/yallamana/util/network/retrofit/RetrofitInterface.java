package com.example.graduation.yallamana.util.network.retrofit;

/**
 * Created by Mais
 */


import com.example.graduation.yallamana.util.network.api.CheckUser;
import com.example.graduation.yallamana.util.network.api.Cities;
import com.example.graduation.yallamana.util.network.api.Comment;
import com.example.graduation.yallamana.util.network.api.Comment1;
import com.example.graduation.yallamana.util.network.api.Example;
import com.example.graduation.yallamana.util.network.api.NewTrip;
import com.example.graduation.yallamana.util.network.api.NewUser;
import com.example.graduation.yallamana.util.network.api.Trip;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by Mais
 */


public interface RetrofitInterface {
    // check if user register or not
    @POST("api/auth/login")
    Call<Example> getTokenLogin(@Body CheckUser checkUser);

    // when user sign up . send info
    @Multipart
    @POST("api/auth/signup")
    Call<Example> setUserInfo(@Part MultipartBody.Part file,@Part ("name") RequestBody  name,
                              @Part ("phone")RequestBody number,@Part ("email") RequestBody  email,
                              @Part ("gender")RequestBody gender,@Part ("type")RequestBody type);

    @Multipart
    @POST("api/auth/signup")
    Call<Example> uploadRiderFile(@Part MultipartBody.Part file, @Header("Authorization") String token);
//    @POST("api/auth/signup")
//    Call<Example> uploadDriverFile(@Part MultipartBody.Part file1,@Part MultipartBody.Part file2,@Part MultipartBody.Part file3,@Part MultipartBody.Part file4,@Part MultipartBody.Part file5, @Header("Authorization") String token);

    // get cities
    @GET("api/city")
    Call<Example> getCities();

    @GET("api/trip")
    Call<Example> getTrips(@Header("Authorization") String token);

    @GET("api/trip/{id}")
    Call<Example> getTripInfo(@Header("Authorization") String token, @Path("id") int id );

    @POST("api/trip/offer")
    Call<Trip> setOfferTrip(@Header("Authorization") String token, @Body NewTrip offer);

    @POST("api/trip/request")
    Call<Trip> setRequestTrip(@Header("Authorization") String token, @Body NewTrip request);

    @POST("api/trip/{id}/reserve")
    Call<Example> reverseTrip(@Header("Authorization") String token, @Path("id") int id );
    @POST("api/trip/{id}/cancel-reservation")
    Call<Example> cancleTrip(@Header("Authorization") String token, @Path("id") int id );
    @GET("api/trip/my-trips")
    Call<Example> getMyTrips(@Header("Authorization") String token);
    @GET("api/post/my_post")
    Call<Example> getMyPosts(@Header("Authorization") String token);
    @GET("api/post")
    Call<Example> getAllPosts(@Header("Authorization") String token);

    @DELETE("api/post/{id}")
    Call<Example> deletPost(@Header("Authorization") String token,@Path("id") int id);
    @POST("api/post")
    Call<Example> addPost(@Header("Authorization") String token,@Body  Comment1 comment );
    @POST("api/comment")
    Call<Example> addNewComment(@Header("Authorization") String token, @Body Comment1 comment);
//
//    @GET(" api/comment")
//    Call<Example> getComments(@Header("Authorization") String token);
//    Observable<Boolean> getToken(@Body String usernumber);@GET("/exalt/users/username/{user}")
// //   Observable<User> getUser(@Path("user") String username);
//
//    @POST("/exalt/users")
//    Observable<User> addUser(@Body User user);
//
//    @PUT("/exalt/users")
//    Observable<User> updateUser(@Body User user);
//
//    @GET("/exalt/users/{user}/posts/{limit}/{offset}")
//    Observable<List<Post>> getPosts(@Path("user") String username, @Path("limit") String limit, @Path("offset") String offset);
//
//    @GET("/exalt/users/{user}/posts")
//    Observable<List<Post>> getPosts(@Path("user") String username);
//
//    @POST("/exalt/users/{user}/posts")
//    Observable<Post> addPost(@Path("user") String username, @Body Post post);
//
//    @DELETE("/exalt/posts/{id}")
//    Observable<Void> deletePost(@Path("id") long postId);
//
//
//    @POST("/exalt/users/{user}/posts/{postId}")
//    Observable<Comment> addComment(@Path("user") String username, @Path("postId") long postId, @Body Comment comment);
//
//    @DELETE("/exalt/comments/{id}")
//    Observable<Void> deleteComment(@Path("id") long commentId);
//
//    @GET("/exalt/users/{user}/posts/{postId}/comments/{limit}/{offset}")
//    Observable<List<Comment>> getComments(@Path("user") String username, @Path("postId") long postId, @Path("limit") String limit, @Path("offset") String offset);
//
//    @GET("/exalt/users/{user}/posts/{postId}/comments")
//    Observable<List<Comment>> getComments(@Path("user") String username, @Path("postId") long postId);


}
