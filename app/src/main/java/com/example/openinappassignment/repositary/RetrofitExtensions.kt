package com.example.openinappassignment.repositary

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

fun Interceptor.Chain.proceedByCheckingCache(request: Request): Response {

    val response = proceed(request)
    if (response.code == 200) {
        val res = response.body?.string()
        return Response.Builder().code(200).request(request).protocol(Protocol.HTTP_2)
            .message("Saved to cache")
            .body(res!!.toResponseBody("application/json".toMediaTypeOrNull()))
            .addHeader("content-type", "application/json").build()
    }
    return response

}