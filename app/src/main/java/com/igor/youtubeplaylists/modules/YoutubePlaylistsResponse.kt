package com.igor.youtubeplaylists.modules

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class YoutubePlaylistsResponse(

	@SerializedName("kind")
	@Expose
	val kind: String? = null,

	@SerializedName("pageInfo")
	@Expose
	val pageInfo: PageInfo? = null,

	@SerializedName("etag")
	@Expose
	val etag: String? = null,

	@SerializedName("items")
	@Expose
	val items: List<ItemsItem?>? = null
)

data class PageInfo(

	@SerializedName("totalResults")
	@Expose
	val totalResults: Int? = null,

	@SerializedName("resultsPerPage")
	@Expose
	val resultsPerPage: Int? = null
)

data class Snippet(

	@SerializedName("playlistId")
	@Expose
	val playlistId: String? = null,

	@SerializedName("resourceId")
	@Expose
	val resourceId: ResourceId? = null,

	@SerializedName("publishedAt")
	@Expose
	val publishedAt: String? = null,

	@SerializedName("description")
	@Expose
	val description: String? = null,

	@SerializedName("position")
	@Expose
	val position: Int? = null,

	@SerializedName("title")
	@Expose
	val title: String? = null,

	@SerializedName("thumbnails")
	@Expose
	val thumbnails: Thumbnails? = null,

	@SerializedName("channelId")
	@Expose
	val channelId: String? = null,

	@SerializedName("channelTitle")
	@Expose
	val channelTitle: String? = null
)

data class Medium(

	@SerializedName("width")
	@Expose
	val width: Int? = null,

	@SerializedName("url")
	@Expose
	val url: String? = null,

	@SerializedName("height")
	@Expose
	val height: Int? = null
)

data class Localized(

	@SerializedName("description")
	@Expose
	val description: String? = null,

	@SerializedName("title")
	@Expose
	val title: String? = null
)

data class JsonMemberDefault(

	@SerializedName("width")
	@Expose
	val width: Int? = null,

	@SerializedName("url")
	@Expose
	val url: String? = null,

	@SerializedName("height")
	@Expose
	val height: Int? = null
)

data class Thumbnails(

	@SerializedName("standard")
	@Expose
	val standard: Standard? = null,

	@SerializedName("default")
	@Expose
	val jsonMemberDefault: JsonMemberDefault? = null,

	@SerializedName("high")
	@Expose
	val high: High? = null,

	@SerializedName("maxres")
	@Expose
	val maxres: Maxres? = null,

	@SerializedName("medium")
	@Expose
	val medium: Medium? = null
)

data class ContentDetails(

	@SerializedName("itemCount")
	@Expose
	val itemCount: Int? = null,

	@SerializedName("videoPublishedAt")
	@Expose
	val videoPublishedAt: String? = null,

	@SerializedName("videoId")
	@Expose
	val videoId: String? = null
)

data class High(

	@SerializedName("width")
	@Expose
	val width: Int? = null,

	@SerializedName("url")
	@Expose
	val url: String? = null,

	@SerializedName("height")
	@Expose
	val height: Int? = null
)

data class ItemsItem(

	@SerializedName("snippet")
	@Expose
	val snippet: Snippet? = null,

	@SerializedName("kind")
	@Expose
	val kind: String? = null,

	@SerializedName("playlistItems")
	@Expose
	val playlistItems: PlaylistItems? = null,

	@SerializedName("etag")
	@Expose
	val etag: String? = null,

	@SerializedName("id")
	@Expose
	val id: String? = null,

	@SerializedName("contentDetails")
	@Expose
	val contentDetails: ContentDetails? = null
)

data class ResourceId(

	@SerializedName("kind")
	@Expose
	val kind: String? = null,

	@SerializedName("videoId")
	@Expose
	val videoId: String? = null
)

data class Maxres(

	@SerializedName("width")
	@Expose
	val width: Int? = null,

	@SerializedName("url")
	@Expose
	val url: String? = null,

	@SerializedName("height")
	@Expose
	val height: Int? = null
)

data class Standard(

	@SerializedName("width")
	@Expose
	val width: Int? = null,

	@SerializedName("url")
	@Expose
	val url: String? = null,

	@SerializedName("height")
	@Expose
	val height: Int? = null
)

data class PlaylistItems(

	@SerializedName("kind")
	@Expose
	val kind: String? = null,

	@SerializedName("nextPageToken")
	@Expose
	val nextPageToken: String? = null,

	@SerializedName("pageInfo")
	@Expose
	val pageInfo: PageInfo? = null,

	@SerializedName("etag")
	@Expose
	val etag: String? = null,

	@SerializedName("items")
	@Expose
	val items: List<ItemsItem?>? = null
)
