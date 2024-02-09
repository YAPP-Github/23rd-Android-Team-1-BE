package com.oksusu.susu.user.infrastructure

import com.oksusu.susu.count.domain.QCount
import com.oksusu.susu.extension.isEquals
import com.oksusu.susu.post.domain.QPost
import com.oksusu.susu.post.domain.QVoteOption
import com.oksusu.susu.post.infrastructure.repository.model.*
import com.oksusu.susu.user.domain.OauthInfo
import com.oksusu.susu.user.domain.QUser
import com.oksusu.susu.user.domain.QUserStatus
import com.oksusu.susu.user.domain.User
import com.oksusu.susu.user.infrastructure.model.QUserAndUserStatusModel
import com.oksusu.susu.user.infrastructure.model.UserAndUserStatusModel
import com.querydsl.jpa.impl.JPAQuery
import jakarta.persistence.EntityManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Repository
interface UserRepository : JpaRepository<User, Long>, UserCustomRepository {
    @Transactional(readOnly = true)
    fun existsByOauthInfo(oauthInfo: OauthInfo): Boolean

    @Transactional(readOnly = true)
    fun findByOauthInfo(oauthInfo: OauthInfo): User?

    @Transactional(readOnly = true)
    fun countByCreatedAtBetween(startAt: LocalDateTime, endAt: LocalDateTime): Long
}

interface UserCustomRepository {
    @Transactional(readOnly = true)
    fun getUserAndUserStatus(uid: Long): UserAndUserStatusModel?
}

class UserCustomRepositoryImpl : UserCustomRepository, QuerydslRepositorySupport(User::class.java) {
    @Autowired
    @Qualifier("susuEntityManager")
    override fun setEntityManager(entityManager: EntityManager) {
        super.setEntityManager(entityManager)
    }

    private val qUser = QUser.user
    private val qUserStatus = QUserStatus.userStatus

    override fun getUserAndUserStatus(uid: Long): UserAndUserStatusModel? {
        return JPAQuery<QUser>(entityManager)
            .select(QUserAndUserStatusModel(qUser, qUserStatus))
            .from(qUser)
            .join(qUserStatus).on(qUser.id.eq(qUserStatus.uid))
            .where(
                qUser.id.isEquals(uid)
            ).fetchFirst()
    }
}
