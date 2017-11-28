package com.example.graduation.yallamana.data;


import com.example.graduation.yallamana.data.local.LocalDataSource;
import com.example.graduation.yallamana.data.remote.RemoteDataSource;
import com.example.graduation.yallamana.util.network.api.Comment;
import com.example.graduation.yallamana.util.network.api.CommentReaction;
import com.example.graduation.yallamana.util.network.api.Post;
import com.example.graduation.yallamana.util.network.api.PostReaction;
import com.example.graduation.yallamana.util.network.api.User;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Mais
 */


public class DataRepository {
    private static DataRepository dataRepository;
    private DataSource remoteDataSource;
    private DataSource localDataSource;
    private Boolean remote = true;

    private DataRepository() {
        this.remoteDataSource = RemoteDataSource.getInstance();
        this.localDataSource = LocalDataSource.getInstance();
    }

    public static DataRepository getInstance() {
        if (dataRepository == null) {
            dataRepository = new DataRepository();
        }
        return dataRepository;
    }

    public Observable<Boolean> getToken(String usernumber) {
        if (remote)
            return remoteDataSource.getToken(usernumber);
        else
            return localDataSource.getToken(usernumber);
    }

//    public Observable<User> getUser(String username) {
//        if (remote)
//            return remoteDataSource.getUser(username);
//        else
//            return localDataSource.getUser(username);
//    }
//
//    public Observable<User> addUser(User user) {
//        if (remote)
//            return remoteDataSource.addUser(user);
//        else
//            return localDataSource.addUser(user);
//    }
//
//    public Observable<User> updateUser(User user) {
//        if (remote)
//            return remoteDataSource.updateUser(user);
//        else
//            return localDataSource.updateUser(user);
//    }
//
//    public Observable<List<Post>> getPosts(String username, String limit, String offset) {
//        if (remote)
//            return remoteDataSource.getPosts(username, limit, offset);
//        else
//            return localDataSource.getPosts(username, limit, offset);
//    }
//
//    public Observable<List<Post>> getPosts(String username) {
//        if (remote)
//            return remoteDataSource.getPosts(username);
//        else
//            return localDataSource.getPosts(username);
//    }
//
//    public Observable<Post> addPost(String username, Post post) {
//        if (remote)
//            return remoteDataSource.addPost(username, post);
//        else
//            return localDataSource.addPost(username, post);
//    }
//
//    public Observable<Void> deletePost(long postId) {
//        if (remote)
//            return remoteDataSource.deletePost(postId);
//        else
//            return localDataSource.deletePost(postId);
//    }
//
//    public Observable<PostReaction> reactPost(String username, long postId, PostReaction postReaction) {
//        if (remote)
//            return remoteDataSource.reactPost(username, postId, postReaction);
//        else
//            return localDataSource.reactPost(username, postId, postReaction);
//    }
//
//    public Observable<Comment> addComment(String username, long postId, Comment comment) {
//        if (remote)
//            return remoteDataSource.addComment(username, postId, comment);
//        else
//            return localDataSource.addComment(username, postId, comment);
//    }
//
//    public Observable<Void> deleteComment(long commentId) {
//        if (remote)
//            return remoteDataSource.deleteComment(commentId);
//        else
//            return localDataSource.deleteComment(commentId);
//    }
//
//    public Observable<List<Comment>> getComments(String username, long postId, String limit, String offset) {
//        if (remote)
//            return remoteDataSource.getComments(username, postId, limit, offset);
//        else
//            return localDataSource.getComments(username, postId, limit, offset);
//    }
//
//    public Observable<List<Comment>> getComments(String username, long postId) {
//        if (remote)
//            return remoteDataSource.getComments(username, postId);
//        else
//            return localDataSource.getComments(username, postId);
//    }
//
//    public Observable<CommentReaction> reactComment(String username, long postId, CommentReaction commentReaction) {
//        if (remote)
//            return remoteDataSource.reactComment(username, postId, commentReaction);
//        else
//            return localDataSource.reactComment(username, postId, commentReaction);
//    }

    public void destroyInstance() {
        dataRepository = null;
    }
}
