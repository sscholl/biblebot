
package org.sscholl.rocketchat.api;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "roomId",
        "channel",
        "text",
        "alias",
        "emoji",
        "avatar",
        "attachments"
})
public class PostMessageRequest {

    @JsonProperty("roomId")
    private String roomId;
    @JsonProperty("channel")
    private String channel;
    @JsonProperty("text")
    private String text;
    @JsonProperty("alias")
    private String alias;
    @JsonProperty("emoji")
    private String emoji;
    @JsonProperty("avatar")
    private String avatar;
    @JsonProperty("attachments")
    private List<Attachment> attachments = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("roomId")
    public String getRoomId() {
        return roomId;
    }

    @JsonProperty("roomId")
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @JsonProperty("channel")
    public String getChannel() {
        return channel;
    }

    @JsonProperty("channel")
    public void setChannel(String channel) {
        this.channel = channel;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonProperty("alias")
    public String getAlias() {
        return alias;
    }

    @JsonProperty("alias")
    public void setAlias(String alias) {
        this.alias = alias;
    }

    @JsonProperty("emoji")
    public String getEmoji() {
        return emoji;
    }

    @JsonProperty("emoji")
    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    @JsonProperty("avatar")
    public String getAvatar() {
        return avatar;
    }

    @JsonProperty("avatar")
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @JsonProperty("attachments")
    public List<Attachment> getAttachments() {
        return attachments;
    }

    @JsonProperty("attachments")
    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
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
        return "PostMessageRequest{" +
                "roomId='" + roomId + '\'' +
                ", channel='" + channel + '\'' +
                ", text='" + text + '\'' +
                ", alias='" + alias + '\'' +
                ", emoji='" + emoji + '\'' +
                ", avatar='" + avatar + '\'' +
                ", attachments=" + attachments +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
