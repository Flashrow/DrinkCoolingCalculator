package pl.flashrow.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import pl.flashrow.data.repository.CalculatorRepository
import pl.flashrow.data.repository.CalculatorRepositoryImpl

@Module
@InstallIn
abstract class DataModule {
    @Binds
    internal abstract fun bindsCalculatorRepository(
        calculatorRepository: CalculatorRepositoryImpl
    ): CalculatorRepository
}