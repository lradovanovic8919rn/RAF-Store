package rs.raf.jul.lazar_radovanovic_rn8919.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.koin.experimental.builder.create
import rs.raf.jul.lazar_radovanovic_rn8919.data.datasources.local.StoreDataBase
import rs.raf.jul.lazar_radovanovic_rn8919.data.datasources.remote.StoreService
import rs.raf.jul.lazar_radovanovic_rn8919.data.repository.StoreRepository
import rs.raf.jul.lazar_radovanovic_rn8919.data.repository.StoreRepositoryImplementation
import rs.raf.jul.lazar_radovanovic_rn8919.presentation.viewmodel.StoreViewModel

val storeModule = module {

    viewModel {StoreViewModel(storeRepository = get())}

    single<StoreRepository>{StoreRepositoryImplementation(cartDao = get() , local = get() , storeService = get())}

    single<StoreService> { create(retrofit = get()) }

    single { get<StoreDataBase>().getStoreDao() }

    single { get<StoreDataBase>().getCartDao() }

}