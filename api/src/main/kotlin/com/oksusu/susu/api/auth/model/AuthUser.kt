package com.oksusu.susu.api.auth.model

import com.oksusu.susu.common.exception.ErrorCode
import com.oksusu.susu.common.exception.NoAuthorityException
import com.oksusu.susu.domain.user.domain.vo.AccountRole

/**
 * 모든 유저가 이용 가능한 parameter 입니다.
 */
interface AuthUser {
    /** user id */
    val uid: Long

    /** user context */
    val context: AuthContext

    fun isAuthor(uid: Long): Boolean

    fun isAuthorThrow(uid: Long)

    fun isNotAuthorThrow(uid: Long)
}

class AuthUserImpl(
    override val uid: Long,
    override val context: AuthContext,
) : AuthUser {
    override fun isAuthor(uid: Long): Boolean {
        return this.uid == uid
    }

    override fun isAuthorThrow(uid: Long) {
        if (isAuthor(uid)) {
            throw NoAuthorityException(ErrorCode.NO_AUTHORITY_ERROR)
        }
    }

    override fun isNotAuthorThrow(uid: Long) {
        if (!isAuthor(uid)) {
            throw NoAuthorityException(ErrorCode.NO_AUTHORITY_ERROR)
        }
    }
}

interface AuthContext {
    /** 이름 */
    val name: String

    /** 계정 권한 */
    val role: AccountRole

    /** imageUrl */
    val profileImageUrl: String?
}

class AuthContextImpl(
    override val name: String,
    override val role: AccountRole,
    override val profileImageUrl: String?,
) : AuthContext

const val AUTH_TOKEN_KEY = "X-SUSU-AUTH-TOKEN"

data class AuthUserToken(
    val key: String,
    val value: String,
) {
    fun isInvalid() = key.isBlank() || value.isBlank()

    companion object {
        fun from(value: String): AuthUserToken {
            return AuthUserToken(
                key = AUTH_TOKEN_KEY,
                value = value
            )
        }
    }
}

data class AuthUserTokenPayload(
    val id: Long,
    val aud: String,
    val iss: String,
    val exp: Long,
    val type: String,
)
