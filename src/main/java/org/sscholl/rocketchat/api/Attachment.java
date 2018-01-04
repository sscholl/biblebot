
package org.sscholl.rocketchat.api;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "color",
        "text",
        "ts",
        "thumb_url",
        "message_link",
        "collapsed",
        "author_name",
        "author_link",
        "author_icon",
        "title",
        "title_link",
        "title_link_download",
        "image_url",
        "audio_url",
        "video_url",
        "fields"
})
public class Attachment {

    @JsonProperty("color")
    private String color;
    @JsonProperty("text")
    private String text;
    @JsonProperty("ts")
    private String ts;
    @JsonProperty("thumb_url")
    private String thumbUrl;
    @JsonProperty("message_link")
    private String messageLink;
    @JsonProperty("collapsed")
    private Boolean collapsed;
    @JsonProperty("author_name")
    private String authorName;
    @JsonProperty("author_link")
    private String authorLink;
    @JsonProperty("author_icon")
    private String authorIcon;
    @JsonProperty("title")
    private String title;
    @JsonProperty("title_link")
    private String titleLink;
    @JsonProperty("title_link_download")
    private String titleLinkDownload;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("audio_url")
    private String audioUrl;
    @JsonProperty("video_url")
    private String videoUrl;
    @JsonProperty("fields")
    private List<Field> fields = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("color")
    public String getColor() {
        return color;
    }

    @JsonProperty("color")
    public void setColor(String color) {
        this.color = color;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonProperty("ts")
    public String getTs() {
        return ts;
    }

    @JsonProperty("ts")
    public void setTs(String ts) {
        this.ts = ts;
    }

    @JsonProperty("thumb_url")
    public String getThumbUrl() {
        return thumbUrl;
    }

    @JsonProperty("thumb_url")
    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    @JsonProperty("message_link")
    public String getMessageLink() {
        return messageLink;
    }

    @JsonProperty("message_link")
    public void setMessageLink(String messageLink) {
        this.messageLink = messageLink;
    }

    @JsonProperty("collapsed")
    public Boolean getCollapsed() {
        return collapsed;
    }

    @JsonProperty("collapsed")
    public void setCollapsed(Boolean collapsed) {
        this.collapsed = collapsed;
    }

    @JsonProperty("author_name")
    public String getAuthorName() {
        return authorName;
    }

    @JsonProperty("author_name")
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @JsonProperty("author_link")
    public String getAuthorLink() {
        return authorLink;
    }

    @JsonProperty("author_link")
    public void setAuthorLink(String authorLink) {
        this.authorLink = authorLink;
    }

    @JsonProperty("author_icon")
    public String getAuthorIcon() {
        return authorIcon;
    }

    @JsonProperty("author_icon")
    public void setAuthorIcon(String authorIcon) {
        this.authorIcon = authorIcon;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("title_link")
    public String getTitleLink() {
        return titleLink;
    }

    @JsonProperty("title_link")
    public void setTitleLink(String titleLink) {
        this.titleLink = titleLink;
    }

    @JsonProperty("title_link_download")
    public String getTitleLinkDownload() {
        return titleLinkDownload;
    }

    @JsonProperty("title_link_download")
    public void setTitleLinkDownload(String titleLinkDownload) {
        this.titleLinkDownload = titleLinkDownload;
    }

    @JsonProperty("image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    @JsonProperty("image_url")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @JsonProperty("audio_url")
    public String getAudioUrl() {
        return audioUrl;
    }

    @JsonProperty("audio_url")
    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    @JsonProperty("video_url")
    public String getVideoUrl() {
        return videoUrl;
    }

    @JsonProperty("video_url")
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @JsonProperty("fields")
    public List<Field> getFields() {
        return fields;
    }

    @JsonProperty("fields")
    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "color='" + color + '\'' +
                ", text='" + text + '\'' +
                ", ts='" + ts + '\'' +
                ", thumbUrl='" + thumbUrl + '\'' +
                ", messageLink='" + messageLink + '\'' +
                ", collapsed=" + collapsed +
                ", authorName='" + authorName + '\'' +
                ", authorLink='" + authorLink + '\'' +
                ", authorIcon='" + authorIcon + '\'' +
                ", title='" + title + '\'' +
                ", titleLink='" + titleLink + '\'' +
                ", titleLinkDownload='" + titleLinkDownload + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", audioUrl='" + audioUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", fields=" + fields +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
