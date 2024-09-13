package com.oksusu.susu.api.envelope.presentation

import com.oksusu.susu.api.auth.model.AdminUser
import com.oksusu.susu.api.common.dto.SusuPageRequest
import com.oksusu.susu.api.config.web.SwaggerTag
import com.oksusu.susu.api.envelope.application.EnvelopeFacade
import com.oksusu.susu.api.envelope.model.request.DevSearchEnvelopeRequest
import com.oksusu.susu.api.extension.wrapPage
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springdoc.core.annotations.ParameterObject
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = SwaggerTag.DEV_ENVELOPE_SWAGGER_TAG, description = "개발용 OAuth API")
@RestController
@RequestMapping(value = ["/api/v1/dev/envelopes"], produces = [MediaType.APPLICATION_JSON_VALUE])
class DevEnvelopeResource(
    private val envelopeFacade: EnvelopeFacade,
) {
    @Operation(tags = [SwaggerTag.DEV_SWAGGER_TAG], summary = "hour summary 호출")
    @GetMapping("/hour-summaries")
    suspend fun getHourSummaries(
        adminUser: AdminUser,
        @ParameterObject request: DevSearchEnvelopeRequest,
        @ParameterObject pageRequest: SusuPageRequest,
    ) = envelopeFacade.search(
        uid = request.uid,
        request = request.convert(),
        pageRequest = pageRequest
    ).wrapPage()
}
