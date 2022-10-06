package rs.raf.jul.lazar_radovanovic_rn8919.data.models.entities

data class Cart (
        var id: Int,
        val product : String,
        val count: Int = 1,
        val price : Int
        )