package com.oksusu.susu.category.application

import com.oksusu.susu.category.domain.CategoryAssignment
import com.oksusu.susu.category.domain.vo.CategoryAssignmentType
import com.oksusu.susu.category.infrastructure.CategoryAssignmentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CategoryAssignmentService(
    private val categoryAssignmentRepository: CategoryAssignmentRepository,
) {
    @Transactional
    fun saveSync(categoryAssignment: CategoryAssignment): CategoryAssignment {
        return categoryAssignmentRepository.save(categoryAssignment)
    }

    @Transactional
    fun deleteByTargetIdAndTargetTypeSync(targetId: Long, targetType: CategoryAssignmentType) {
        categoryAssignmentRepository.deleteByTargetIdAndTargetType(targetId, targetType)
    }

    @Transactional
    fun deleteAllByTargetTypeAndTargetIdIn(targetType: CategoryAssignmentType, targetIds: List<Long>) {
        categoryAssignmentRepository.deleteAllByTargetTypeAndTargetIdIn(targetType, targetIds)
    }

    suspend fun findByIdAndTypeOrNull(targetId: Long, targetType: CategoryAssignmentType): CategoryAssignment? {
        return withContext(Dispatchers.IO) {
            categoryAssignmentRepository.findByTargetIdAndTargetType(targetId, targetType)
        }
    }

    suspend fun findAllByTypeAndIdIn(
        targetType: CategoryAssignmentType,
        targetIds: List<Long>,
    ): List<CategoryAssignment> {
        return withContext(Dispatchers.IO) {
            categoryAssignmentRepository.findAllByTargetTypeAndTargetIdIn(targetType, targetIds)
        }
    }
}
