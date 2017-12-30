package com.example.graduation.yallamana.util.network.retrofit;

/**
 * Created by Mais
 */


import com.example.graduation.yallamana.util.network.api.CheckUser;
import com.example.graduation.yallamana.util.network.api.Example;
import com.example.graduation.yallamana.util.network.api.NewUser;


import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Mais
 */
import retrofit2.Call;


public interface RetrofitInterface {
    // check if user register or not
    @POST("api/auth/login")
    Call<Example> getTokenLogin(@Body CheckUser checkUser);

    // when user sign up . send info
    @POST("api/auth/signup")
    Call<NewUser> setUserInfo(@Body NewUser newuser);

//    @POST("/api/auth/login")
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
