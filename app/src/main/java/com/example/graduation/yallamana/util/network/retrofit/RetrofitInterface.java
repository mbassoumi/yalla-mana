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
    
    @POST("api/auth/signup")
    Call<Example> setUserInfo(@Body NewUser newUser);
    
//    @POST("api/auth/signup")
  //  Call<Example> uploadRiderFile(@Part MultipartBody.Part file, @Header("Authorization") String token);
//    @POST("api/auth/signup")
//    Call<Example> uploadDriverFile(@Part MultipartBody.Part file1,@Part MultipartBody.Part file2,@Part MultipartBody.Part file3,@Part MultipartBody.Part file4,@Part MultipartBody.Part file5, @Header("Authorization") String token);

    // get citie
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
    @POST("api/trip/{id}/accept")
    Call<Example> acceptTrip(@Header("Authorization") String token, @Path("id") int id );
    @GET("api/trip/my-trips")
    Call<Example> getMyTrips(@Header("Authorization") String token);
    @GET("api/post/my_post")
    Call<Example> getMyPosts(@Header("Authorization") String token);
    @GET("api/post")
    Call<Example> getAllPosts(@Header("Authorization") String token);

    @DELETE("api/post/{id}")
    Call<Example> deletPost(@Header("Authorization") String token,@Path("id") int id);
    @POST("api/post")
    Call<Example> addPost(@Header("Authorization") String token,@Body  String body );
    @POST("api/comment")
    Call<Example> addNewComment(@Header("Authorization") String token, @Body Comment1 comment);

}
