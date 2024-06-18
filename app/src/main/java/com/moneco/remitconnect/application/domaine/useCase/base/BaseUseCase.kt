package com.moneco.remitconnect.application.domaine.useCase.base
interface BaseUseCase<In, Out> {
    suspend fun execute(input: In): Out
}