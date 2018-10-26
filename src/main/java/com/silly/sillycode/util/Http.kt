package com.silly.sillycode.util

import com.google.gson.Gson
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate


fun <T> get(url: String, classOfT: Class<T>): T {
    return request(url, LinkedMultiValueMap<String, String>(), classOfT, HttpMethod.GET)
}

fun <T> post(url: String, classOfT: Class<T>, params: MultiValueMap<String, String>): T {
    return request(url, params, classOfT, HttpMethod.POST)
}

private fun <T> request(url: String, params: MultiValueMap<String, String>, classOfT: Class<T>, method: HttpMethod): T {
    return try {
        val client = RestTemplate()
        val headers = HttpHeaders()
        //  请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
        headers.contentType = MediaType.APPLICATION_FORM_URLENCODED
        val requestEntity = HttpEntity(params, headers)
        //  执行HTTP请求
        val response = client.exchange(url, method, requestEntity, String::class.java)
        // json解析
        if (classOfT.name == "java.lang.String") response.body as T
        else Gson().fromJson(response.body, classOfT)
    } catch (e: Exception) {
        e.printStackTrace()
        return classOfT.newInstance()
    }
}