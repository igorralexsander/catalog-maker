package com.igorralexsander.infraestructure.clients

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.coroutines.flow.asFlow
import org.jsoup.Jsoup

class B2WPageMapper {


    fun mapToDomain(html:String): List<B2WProduct> {
        val json = extractJsonDataFromHtml(html)
        if (json != null){
            val searchObject = json.getAsJsonObject("ROOT_QUERY").entrySet()
                .first { it.key.startsWith("search")}
                .value.asJsonObject
            return searchObject.getAsJsonArray("products").map {
                B2WProduct(
                    it.asJsonObject.get("product").asJsonObject.get("id").asString,
                    it.asJsonObject.get("product").asJsonObject.get("name").asString
                )
            }
        }
        return emptyList()
    }

    private fun extractJsonDataFromHtml(html:String): JsonObject? {
        val htmlDoc = Jsoup.parse(html)
        val pageElement = htmlDoc.select("script")
            .map { it.html().toString() }
            .filter { it.startsWith("window.__APOLLO_STATE__") }
            .map { it.replaceBefore("{","") }
            .firstOrNull()
        if (pageElement != null){
            val gson = Gson()
            return gson.fromJson(pageElement, JsonObject::class.java)
        }
        return null
    }
}