package rs.raf.jul.lazar_radovanovic_rn8919.data.models.bodies

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class LoginRequestBody(
    val username: String,
    val password: String
)
