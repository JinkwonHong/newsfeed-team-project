package com.teamsparta.exhibitionnewsfeed.domain.likes.service

import com.teamsparta.exhibitionnewsfeed.domain.likes.dto.PostLikeRequest
import com.teamsparta.exhibitionnewsfeed.domain.likes.model.PostLike
import com.teamsparta.exhibitionnewsfeed.domain.likes.repository.PostLikeRepository
import com.teamsparta.exhibitionnewsfeed.domain.newsfeed.post.repository.PostRepository
import com.teamsparta.exhibitionnewsfeed.domain.user.repository.UserRepository
import com.teamsparta.exhibitionnewsfeed.exception.ModelNotFoundException
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class LikeServiceImpl(
    private val postLikeRepository: PostLikeRepository,
    private val postRepository: PostRepository,
    private val userRepository: UserRepository
) : LikeService {

    @Transactional
    override fun likePost(postId: Long, request: PostLikeRequest) {
        val foundPost = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("Post", postId)
        val foundUser =
            userRepository.findByIdOrNull(request.userId) ?: throw ModelNotFoundException("User", request.userId)

        /* if (foundPost.users.id == request.userId) {
            throw RuntimeException("You cannot like your own comment")
        } */

        val alreadyLike = postLikeRepository.findByPostIdAndUserId(postId, request.userId)
        if (alreadyLike != null) {
            throw RuntimeException("이미 좋아요를 눌렀습니다.")
        }
        // foundPost.likeCount += 1

        val like = PostLike(post = foundPost, user = foundUser)
        postLikeRepository.save(like)
    }

    @Transactional
    override fun removePostLike(likeId: Long, postId: Long) {
        postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("Post", postId)

        val like = postLikeRepository.findByIdOrNull(likeId) ?: throw ModelNotFoundException("postLike", likeId)
        postLikeRepository.delete(like)
    }
}