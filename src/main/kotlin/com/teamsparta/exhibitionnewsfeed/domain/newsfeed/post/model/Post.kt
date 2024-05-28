package com.teamsparta.exhibitionnewsfeed.domain.newsfeed.post.model

import com.teamsparta.exhibitionnewsfeed.domain.user.model.User
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
class Post (

    @Column(name = "title")
    var title: String,

    @Column(name = "content")
    var content: String,

    @CreationTimestamp
    @Column(updatable = false)
    val createdAt : LocalDateTime = LocalDateTime.now(),

    @ManyToOne
    val user : User
)
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}