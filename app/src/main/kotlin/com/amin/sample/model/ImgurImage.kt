package com.amin.sample.model


import com.google.gson.annotations.SerializedName

data class ImgurImage(
    @SerializedName("account_id")
    val accountId: Int,
    @SerializedName("account_url")
    val accountUrl: String,
    @SerializedName("ad_config")
    val adConfig: AdConfig,
    @SerializedName("ad_type")
    val adType: Int,
    @SerializedName("ad_url")
    val adUrl: String,
    @SerializedName("comment_count")
    val commentCount: Int,
    @SerializedName("cover")
    val cover: String,
    @SerializedName("cover_height")
    val coverHeight: Int,
    @SerializedName("cover_width")
    val coverWidth: Int,
    @SerializedName("datetime")
    val datetime: Int,
    @SerializedName("description")
    val description: Any,
    @SerializedName("downs")
    val downs: Int,
    @SerializedName("favorite")
    val favorite: Boolean,
    @SerializedName("favorite_count")
    val favoriteCount: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("images")
    val images: List<Image>,
    @SerializedName("images_count")
    val imagesCount: Int,
    @SerializedName("in_gallery")
    val inGallery: Boolean,
    @SerializedName("in_most_viral")
    val inMostViral: Boolean,
    @SerializedName("include_album_ads")
    val includeAlbumAds: Boolean,
    @SerializedName("is_ad")
    val isAd: Boolean,
    @SerializedName("is_album")
    val isAlbum: Boolean,
    @SerializedName("layout")
    val layout: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("nsfw")
    val nsfw: Boolean,
    @SerializedName("points")
    val points: Int,
    @SerializedName("privacy")
    val privacy: String,
    @SerializedName("score")
    val score: Int,
    @SerializedName("section")
    val section: String,
    @SerializedName("tags")
    val tags: List<Any>,
    @SerializedName("title")
    val title: String,
    @SerializedName("topic")
    val topic: String,
    @SerializedName("topic_id")
    val topicId: Int,
    @SerializedName("ups")
    val ups: Int,
    @SerializedName("views")
    val views: Int,
    @SerializedName("vote")
    val vote: Any
) {
    data class Image(
        @SerializedName("account_id")
        val accountId: Any,
        @SerializedName("account_url")
        val accountUrl: Any,
        @SerializedName("ad_type")
        val adType: Int,
        @SerializedName("ad_url")
        val adUrl: String,
        @SerializedName("animated")
        val animated: Boolean,
        @SerializedName("bandwidth")
        val bandwidth: Long,
        @SerializedName("comment_count")
        val commentCount: Any,
        @SerializedName("datetime")
        val datetime: Int,
        @SerializedName("description")
        val description: Any,
        @SerializedName("downs")
        val downs: Any,
        @SerializedName("edited")
        val edited: String,
        @SerializedName("favorite")
        val favorite: Boolean,
        @SerializedName("favorite_count")
        val favoriteCount: Any,
        @SerializedName("has_sound")
        val hasSound: Boolean,
        @SerializedName("height")
        val height: Int,
        @SerializedName("id")
        val id: String,
        @SerializedName("in_gallery")
        val inGallery: Boolean,
        @SerializedName("in_most_viral")
        val inMostViral: Boolean,
        @SerializedName("is_ad")
        val isAd: Boolean,
        @SerializedName("link")
        val link: String,
        @SerializedName("nsfw")
        val nsfw: Any,
        @SerializedName("points")
        val points: Any,
        @SerializedName("score")
        val score: Any,
        @SerializedName("section")
        val section: Any,
        @SerializedName("size")
        val size: Int,
        @SerializedName("tags")
        val tags: List<Any>,
        @SerializedName("title")
        val title: Any,
        @SerializedName("type")
        val type: String,
        @SerializedName("ups")
        val ups: Any,
        @SerializedName("views")
        val views: Int,
        @SerializedName("vote")
        val vote: Any,
        @SerializedName("width")
        val width: Int
    )

    data class AdConfig(
        @SerializedName("highRiskFlags")
        val highRiskFlags: List<Any>,
        @SerializedName("safeFlags")
        val safeFlags: List<String>,
        @SerializedName("showsAds")
        val showsAds: Boolean,
        @SerializedName("unsafeFlags")
        val unsafeFlags: List<Any>
    )
}