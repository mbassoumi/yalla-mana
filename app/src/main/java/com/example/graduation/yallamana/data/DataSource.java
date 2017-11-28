package com.example.graduation.yallamana.data;


/**
 * Created by Mais
 */
import com.example.graduation.yallamana.util.network.api.Comment;
import com.example.graduation.yallamana.util.network.api.CommentReaction;
import com.example.graduation.yallamana.util.network.api.Post;
import com.example.graduation.yallamana.util.network.api.PostReaction;
import com.example.graduation.yallamana.util.network.api.User;

import java.util.List;

import io.reactivex.Observable;


public abstract class DataSource {

    public DataSource() {
    }

    public abstract Observable<Boolean> getToken(String usernumber);

//    public abstract Observable<User> getUser(String username);
//
//    public abstract Observable<User> addUser(User user);
//
//    public abstract Observable<User> updateUser(User user);
//
//    public abstract Observable<List<Post>> getPosts(String username, String limit, String offset);
//
//    public abstract Observable<List<Post>> getPosts(String username);
//
//    public abstract Observable<Post> addPost(String username, Post post);
//
//    public abstract Observable<Void> deletePost(long postId);
//
//    public abstract Observable<PostReaction> reactPost(String username, long postId, PostReaction postReaction);
//
//    public abstract Observable<Comment> addComment(String username, long postId, Comment comment);
//
//    public abstract Observable<Void> deleteComment(long commentId);
//
//    public abstract Observable<List<Comment>> getComments(String username, long postId, String limit, String offset);
//
//    public abstract Observable<List<Comment>> getComments(String username, long postId);
//
//    public abstract Observable<CommentReaction> reactComment(String username, long postId, CommentReaction commentReaction);

}
