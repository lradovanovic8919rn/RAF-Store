package rs.raf.jul.lazar_radovanovic_rn8919.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.jul.lazar_radovanovic_rn8919.data.models.entities.CartEntity
import rs.raf.jul.lazar_radovanovic_rn8919.data.models.entities.ProductEntity

@Dao
abstract class CartDao {
    @Insert( onConflict = OnConflictStrategy.IGNORE )
    abstract fun insertIgnore(entity: CartEntity): Long

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(entity: CartEntity): Completable

    @Query("UPDATE cart SET count = count + :value WHERE product = :name")
    abstract fun updateSum(name: String, value: Int):Completable

    @Query("DELETE FROM cart")
    abstract fun deleteAll(): Completable

    @Query("SELECT SUM(price*count) FROM cart")
    abstract fun getFinalPrice(): Observable<Int?>

    @Query("SELECT * FROM cart WHERE product = :name")
    abstract fun getCartItemByName(name: String): Observable<CartEntity?>


    @Query("SELECT * FROM cart")
    abstract fun getCartInventory(): Observable<List<CartEntity>>



}