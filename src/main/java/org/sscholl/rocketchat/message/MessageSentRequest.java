
package org.sscholl.rocketchat.message;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "bot",
        "channel_id",
        "channel_name",
        "message_id",
        "timestamp",
        "user_id",
        "user_name",
        "text",
        "alias"
})
public class MessageSentRequest {

    @JsonProperty("channel_id")
    private String channelId;
    @JsonProperty("channel_name")
    private String channelName;
    @JsonProperty("message_id")
    private String messageId;
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("text")
    private String text;
    @JsonProperty("alias")
    private String alias;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("channel_id")
    public String getChannelId() {
        return channelId;
    }

    @JsonProperty("channel_id")
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @JsonProperty("channel_name")
    public String getChannelName() {
        return channelName;
    }

    @JsonProperty("channel_name")
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    @JsonProperty("message_id")
    public String getMessageId() {
        return messageId;
    }

    @JsonProperty("message_id")
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @JsonProperty("timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("user_id")
    public String getUserId() {
        return userId;
    }

    @JsonProperty("user_id")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonProperty("user_name")
    public String getUserName() {
        return userName;
    }

    @JsonProperty("user_name")
    public void setUserName(String userName) {
        this.userName = userName;
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
        return "MessageSentRequest{" +
                "channelId='" + channelId + '\'' +
                ", channelName='" + channelName + '\'' +
                ", messageId='" + messageId + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", text='" + text + '\'' +
                ", alias='" + alias + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
