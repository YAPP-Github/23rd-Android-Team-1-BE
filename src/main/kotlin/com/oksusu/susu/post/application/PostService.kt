package com.oksusu.susu.post.application

import com.oksusu.susu.exception.ErrorCode
import com.oksusu.susu.exception.InvalidRequestException
import com.oksusu.susu.exception.NotFoundException
import com.oksusu.susu.extension.withMDCContext
import com.oksusu.susu.post.domain.Post
import com.oksusu.susu.post.domain.vo.PostType
import com.oksusu.susu.post.infrastructure.repository.PostRepository
import com.oksusu.susu.post.infrastructure.repository.model.PostAndUserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostService(
    private val postRepository: PostRepository,
) {
    @Transactional
    fun saveSync(post: Post): Post {
        return postRepository.save(post)
    }

    @Transactional
    fun saveAllSync(posts: List<Post>): List<Post> {
        return postRepository.saveAll(posts)
    }

    suspend fun findByIdOrThrow(id: Long): Post {
        return findByIdOrNull(id) ?: throw NotFoundException(ErrorCode.NOT_FOUND_POST_ERROR)
    }

    suspend fun findByIdOrNull(id: Long): Post? {
        return withContext(Dispatchers.IO.withMDCContext()) { postRepository.findByIdOrNull(id) }
    }

    suspend fun findByIdAndIsActiveAndTypeOrThrow(id: Long, isActive: Boolean, type: PostType): Post {
        return withContext(Dispatchers.IO.withMDCContext()) {
            postRepository.findByIdAndIsActiveAndType(id, true, PostType.VOTE)
        } ?: throw NotFoundException(ErrorCode.NOT_FOUND_POST_ERROR)
    }

    suspend fun validateExist(id: Long) {
        withContext(Dispatchers.IO.withMDCContext()) {
            postRepository.existsById(id)
        }.takeIf { isExist -> isExist } ?: throw InvalidRequestException(ErrorCode.NOT_FOUND_POST_ERROR)
    }

    suspend fun validateAuthority(id: Long, uid: Long) {
        withContext(Dispatchers.IO.withMDCContext()) {
            findByIdOrThrow(id)
        }.takeIf { post -> post.uid == uid } ?: throw InvalidRequestException(ErrorCode.NO_AUTHORITY_ERROR)
    }

    suspend fun existsById(id: Long): Boolean {
        return withContext(Dispatchers.IO.withMDCContext()) { postRepository.existsById(id) }
    }

    suspend fun findAllByUid(uid: Long): List<Post> {
        return withContext(Dispatchers.IO.withMDCContext()) {
            postRepository.findAllByUid(uid)
        }
    }

    suspend fun getPostAndCreator(id: Long, type: PostType): PostAndUserModel {
        return withContext(Dispatchers.IO.withMDCContext()) {
            postRepository.getPostAndCreator(id, type)
        } ?: throw NotFoundException(ErrorCode.NOT_FOUND_POST_ERROR)
    }
}
