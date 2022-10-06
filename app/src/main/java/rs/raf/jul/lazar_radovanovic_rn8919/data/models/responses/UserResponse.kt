package rs.raf.jul.lazar_radovanovic_rn8919.data.models.responses

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class UserResponse(
    val email: String,
    val firstName: String,
    val gender: String,
    val image: String,
    val lastName: String,
    val username: String
)