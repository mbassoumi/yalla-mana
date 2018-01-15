package com.example.graduation.yallamana.data.remote;



import com.example.graduation.yallamana.data.DataSource;
import com.example.graduation.yallamana.util.network.retrofit.RetrofitInterface;
import com.example.graduation.yallamana.util.network.retrofit.RxErrorHandlingCallAdapterFactory;

import java.io.IOException;

import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Mais
 */

public class RemoteDataSource extends DataSource {
    private static RemoteDataSource remoteDataSource;
    private Retrofit retrofit;
    private RetrofitInterface api;

    private RemoteDataSource() {
        super();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

 httpClient.addInterceptor(chain -> {
            Request original = chain.request();

            Request request = original.newBuilder()
                    .header("Accept", "application/vnd.yourapi.v1.full+json")
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(request);

        });
        OkHttpClient client = httpClient.build();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://yalla-mana.appspot.com/")
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .client(client)
                .build();

        api = retrofit.create(RetrofitInterface.class);
    }

    public static RemoteDataSource getInstance() {
        if (remoteDataSource == null) {
            remoteDataSource = new RemoteDataSource();
        }
        return remoteDataSource;
    }
//
//    @Override
//    public Observable<Boolean> getToken(String usernumber) {
//        return api.getToken(usernumber);
//    }

//    @Override
//    public Observable<User> getUser(String username) {
//        return api.getUser(username);
//    }
//
//    @Override
//    public Observable<User> addUser(User user) {
//        return api.addUser(user);
//    }
//
//    @Override
//    public Observable<User> updateUser(User user) {
//        return api.updateUser(user);
//    }
//
//    @Override
//    public Observable<List<Post>> getPosts(String username, String limit, String offset) {
//        return api.getPosts(username, limit, offset);
//    }
//
//    @Override
//    public Observable<List<Post>> getPosts(String username) {
//        return api.getPosts(username);
//    }
//
//    @Override
//    public Observable<Post> addPost(String username, Post post) {
//        return api.addPost(username, post);
//    }
//
//    @Override
//    public Observable<Void> deletePost(long postId) {
//        return api.deletePost(postId);
//    }
//
//    @Override
//    public Observable<PostReaction> reactPost(String username, long postId, PostReaction postReaction) {
//        return api.reactPost(username, postId, postReaction);
//    }
//
//    @Override
//    public Observable<Comment> addComment(String username, long postId, Comment comment) {
//        return api.addComment(username, postId, comment);
//    }
//
//    @Override
//    public Observable<Void> deleteComment(long commentId) {
//        return api.deleteComment(commentId);
//    }
//
//    @Override
//    public Observable<List<Comment>> getComments(String username, long postId, String limit, String offset) {
//        return api.getComments(username, postId, limit, offset);
//    }
//
//    @Override
//    public Observable<List<Comment>> getComments(String username, long postId) {
//        return api.getComments(username, postId);
//    }
//
//    @Override
//    public Observable<CommentReaction> reactComment(String username, long postId, CommentReaction commentReaction) {
//        return api.reactComment(username, postId, commentReaction);
//    }

}