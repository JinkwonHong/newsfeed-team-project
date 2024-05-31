package com.teamsparta.exhibitionnewsfeed.domain.likes.service

import com.teamsparta.exhibitionnewsfeed.domain.auth.AuthUser
import com.teamsparta.exhibitionnewsfeed.domain.likes.dto.PostLikeResponse

interface LikeService {
    fun likePost(postId: Long, authUser: AuthUser)
    fun removePostLike(likeId: Long, postId: Long)

    fun getLikesByPostId(postId: Long): List<PostLikeResponse>

    fun likeComment(postId: Long, commentId: Long, userId: Long)
    fun removeCommentLike(postId: Long, commentId: Long, userId: Long, likeId: Long)
    fun getLikesCount(postId: Long, commentId: Long)
}