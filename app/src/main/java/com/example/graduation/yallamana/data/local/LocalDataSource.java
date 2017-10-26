package com.example.graduation.yallamana.data.local;


import com.example.graduation.yallamana.data.DataSource;
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

public class LocalDataSource extends DataSource {
    private static LocalDataSource localDataSource;

    private LocalDataSource() {
        super();
    }

    public static LocalDataSource getInstance() {
        if (localDataSource == null) {
            localDataSource = new LocalDataSource();
        }
        return localDataSource;
    }

    @Override
    public Observable<Boolean> getToken(String username) {
        return null;
    }

//    @Override
//    public Observable<User> getUser(String username) {
//        return null;
//    }
//
//    @Override
//    public Observable<User> addUser(User user) {
//        return null;
//    }
//
//    @Override
//    public Observable<User> updateUser(User user) {
//        return null;
//    }
//
//    @Override
//    public Observable<List<Post>> getPosts(String username, String limit, String offset) {
//        return null;
//    }
//
//    @Override
//    public Observable<List<Post>> getPosts(String username) {
//        return null;
//    }
//
//    @Override
//    public Observable<Post> addPost(String username, Post post) {
//        return null;
//    }
//
//    @Override
//    public Observable<Void> deletePost(long postId) {
//        return null;
//    }
//
//    @Override
//    public Observable<PostReaction> reactPost(String username, long postId, PostReaction postReaction) {
//        return null;
//    }
//
//    @Override
//    public Observable<Comment> addComment(String username, long postId, Comment comment) {
//        return null;
//    }
//
//    @Override
//    public Observable<Void> deleteComment(long commentId) {
//        return null;
//    }
//
//    @Override
//    public Observable<List<Comment>> getComments(String username, long postId, String limit, String offset) {
//        return null;
//    }
//
//    @Override
//    public Observable<List<Comment>> getComments(String username, long postId) {
//        return null;
//    }
//
//    @Override
//    public Observable<CommentReaction> reactComment(String username, long postId, CommentReaction commentReaction) {
//        return null;
//    }
}
