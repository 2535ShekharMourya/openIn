package com.example.openinappassignment.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Dashboard(
    val status: Boolean,
    val statusCode: Int,
    val message: String,
    @SerializedName("support_whatsapp_number") val supportWhatsappNumber: String,
    @SerializedName("extra_income") val extraIncome: Double,
    @SerializedName("total_links") val totalLinks: Int,
    @SerializedName("total_clicks") val totalClicks: Int,
    @SerializedName("today_clicks") val todayClicks: Int,
    @SerializedName("top_source") val topSource: String,
    @SerializedName("top_location") val topLocation: String,
    val startTime: String,
    @SerializedName("links_created_today") val linksCreatedToday: Int,
    @SerializedName("applied_campaign") val appliedCampaign: Int,
    val data: Data
) : Serializable

data class Data(
    @SerializedName("recent_links") val recentLinks: List<Link>,
    @SerializedName("top_links") val topLinks: List<Link>,
    @SerializedName("favourite_links") val favouriteLinks: List<Any>,
    @SerializedName("overall_url_chart") val overallUrlChart: Map<String, Int>
) : Serializable

data class Link(
    @SerializedName("url_id") val urlId: Int,
    @SerializedName("web_link") val webLink: String,
    @SerializedName("smart_link") val smartLink: String,
    val title: String,
    @SerializedName("total_clicks") val totalClicks: Int,
    @SerializedName("original_image") val originalImage: String,
    val thumbnail: String?,
    @SerializedName("times_ago") val timesAgo: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("domain_id") val domainId: String,
    @SerializedName("url_prefix") val urlPrefix: String?,
    @SerializedName("url_suffix") val urlSuffix: String,
    val app: String,
    @SerializedName("is_favourite") val isFavourite: Boolean
) : Serializable