package com.kadirkuruca.offlinecaching.util

import kotlinx.coroutines.flow.*


inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>, //Responsible for getting the data from database
    crossinline fetch: suspend () -> RequestType, //Responsible for getting the data from rest api
    crossinline saveFetchResult: suspend (RequestType) -> Unit, //Responsible for save the rest api data to database,
    crossinline shouldFetch: (ResultType) -> Boolean = { true } //This will decide that fetching data from rest api or not
) = flow{
    val data = query().first()

    val flow = if(shouldFetch(data)){
        emit(Resource.Loading(data))

        try {
            saveFetchResult(fetch())
            query().map { Resource.Success(it) }
        }
        catch (throwable: Throwable){
            query().map { Resource.Error(throwable, it) }
        }
    }
    else{
         query().map { Resource.Success(it) }
    }

    emitAll(flow)
}