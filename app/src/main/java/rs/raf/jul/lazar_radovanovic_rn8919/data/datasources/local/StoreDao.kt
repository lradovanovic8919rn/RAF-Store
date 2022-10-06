package rs.raf.jul.lazar_radovanovic_rn8919.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.jul.lazar_radovanovic_rn8919.data.models.entities.ProductEntity

@Dao
abstract class StoreDao {

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<ProductEntity>): Completable

    @Query("DELETE FROM products")
    abstract fun deleteAll()

    @Transaction
    open fun deleteAndInsertAll(entities: List<ProductEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }

    @Query("SELECT * FROM products")
    abstract fun getResults(): Observable<List<ProductEntity>>
}