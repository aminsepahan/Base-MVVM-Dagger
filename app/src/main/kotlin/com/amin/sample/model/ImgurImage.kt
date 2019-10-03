package com.amin.sample.model


import com.google.gson.annotations.SerializedName

data class ImgurImage(
    @SerializedName("account_id")
    val accountId: Int,
    @SerializedName("account_url")
    val accountUrl: String,
    @SerializedName("comment_count")
    val commentCount: String,
    val cover: String,
    @SerializedName("cover_height")
    val coverHeight: Int,
    @SerializedName("cover_width")
    val coverWidth: Int,
    val width: Int,
    val height: Int,
    val datetime: Int,
    val description: Any,
    val downs: String,
    val favorite: Boolean,
    @SerializedName("favorite_count")
    val favoriteCount: Int,
    val id: String,
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
    val layout: String,
    val link: String,
    val points: Int,
    val privacy: String,
    val score: Int,
    val section: String,
    val tags: List<Any>,
    val title: String,
    val topic: String,
    @SerializedName("topic_id")
    val topicId: Int,
    val ups: String,
    val views: String,
    val vote: Any
) {

    fun getImageLink(): String? =
        if (isAlbum && images.isNotEmpty()) {
            "https://i.imgur.com/${cover}.jpg"
        } else link


    data class Image(
        @SerializedName("account_id")
        val accountId: Any,
        @SerializedName("account_url")
        val accountUrl: Any,
        @SerializedName("ad_type")
        val adType: Int,
        @SerializedName("ad_url")
        val adUrl: String,
        val animated: Boolean,
        val bandwidth: Long,
        @SerializedName("comment_count")
        val commentCount: Any,
        val datetime: Int,
        val description: Any,
        val downs: Any,
        val edited: String,
        val favorite: Boolean,
        @SerializedName("favorite_count")
        val favoriteCount: Any,
        @SerializedName("has_sound")
        val hasSound: Boolean,
        val height: Int,
        val id: String,
        @SerializedName("in_gallery")
        val inGallery: Boolean,
        @SerializedName("in_most_viral")
        val inMostViral: Boolean,
        @SerializedName("is_ad")
        val isAd: Boolean,
        val link: String,
        val size: Int,
        val tags: List<Any>,
        val title: Any,
        val type: String,
        val mp4: String,
        val gifv: String,
        val ups: Any,
        val views: Int,
        val width: Int
    )

}