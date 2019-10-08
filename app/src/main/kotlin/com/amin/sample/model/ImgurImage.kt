package com.amin.sample.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
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
    val title: String,
    val topic: String,
    @SerializedName("topic_id")
    val topicId: Int,
    val ups: String,
    val views: String
) : Parcelable {

    fun getImageLink(): String? =
        if (isAlbum && images.isNotEmpty()) {
            "https://i.imgur.com/${cover}.jpg"
        } else link

    @Parcelize
    data class Image(
        val animated: Boolean,
        val bandwidth: Long,
        @SerializedName("comment_count")
        val commentCount: String,
        val datetime: Int,
        val description: String,
        val downs: String,
        val edited: String,
        val favorite: Boolean,
        @SerializedName("favorite_count")
        val favoriteCount: String,
        @SerializedName("has_sound")
        val hasSound: Boolean,
        val height: Int,
        val id: String,
        @SerializedName("is_ad")
        val isAd: Boolean,
        val link: String,
        val size: Int,
        val tags: List<String>,
        val title: String,
        val type: String,
        val mp4: String,
        val gifv: String,
        val ups: String,
        val views: Int,
        val width: Int
    ) : Parcelable

}