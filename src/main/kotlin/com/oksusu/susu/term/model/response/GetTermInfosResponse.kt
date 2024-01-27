package com.oksusu.susu.term.model.response

import com.oksusu.susu.term.domain.Term

data class GetTermInfosResponse(
    /** 약관 정보 id */
    val id: Long,
    /** 약관 제목 */
    val title: String,
    /** 필수 동의 여부 / 필수 : 1, 선택 : 0 */
    val isEssential: Boolean,
) {
    companion object {
        fun from(term: Term): GetTermInfosResponse {
            return GetTermInfosResponse(
                id = term.id,
                title = term.title,
                isEssential = term.isEssential
            )
        }
    }
}
