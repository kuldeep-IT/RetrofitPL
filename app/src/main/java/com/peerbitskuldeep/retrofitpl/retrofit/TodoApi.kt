package com.peerbitskuldeep.retrofitpl.retrofit

import com.peerbitskuldeep.retrofitpl.Todo
import retrofit2.Response
import retrofit2.http.GET

interface TodoApi {

//    https://jsonplaceholder.typicode.com/todos

    @GET("/todos")
    suspend fun getTodos(): Response<List<Todo>>

}