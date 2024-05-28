package com.teamsparta.exhibitionnewsfeed.domain.newsfeed.post.service

import com.teamsparta.exhibitionnewsfeed.domain.newsfeed.post.dto.CreatePostRequest
import com.teamsparta.exhibitionnewsfeed.domain.newsfeed.post.dto.PostResponse
import com.teamsparta.exhibitionnewsfeed.domain.newsfeed.post.dto.PostsResponse
import com.teamsparta.exhibitionnewsfeed.domain.newsfeed.post.dto.PostsResponse.Companion.toResponse
import com.teamsparta.exhibitionnewsfeed.domain.newsfeed.post.dto.toEntity
import com.teamsparta.exhibitionnewsfeed.domain.newsfeed.post.repository.PostRepository
import com.teamsparta.exhibitionnewsfeed.domain.user.repository.UserRepository
import com.teamsparta.exhibitionnewsfeed.exception.ModelNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PostServiceImpl(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository
) : PostService {
    override fun getPostById(postId: Long): PostResponse {
        val foundPost = postRepository.findByIdOrNull(postId) ?: throw ModelNotFoundException("Post", postId)

        return PostResponse.from(foundPost)
    }

    override fun createPost(request: CreatePostRequest): PostsResponse {
        // 로그인 구현 후 nickname 가져오는 방식 수정
        val user = userRepository.findByIdOrNull(1L)

        return postRepository.save(request.toEntity(user)).toResponse()
    }
}