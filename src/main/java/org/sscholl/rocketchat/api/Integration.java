package org.sscholl.rocketchat.api;


import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "_id",
        "type",
        "event",
        "enabled",
        "name",
        "channel",
        "username",
        "urls",
        "scriptEnabled",
        "userId",
        "_createdAt",
        "_createdBy",
        "_updatedAt"
})
public class Integration {

    @JsonProperty("_id")
    private String id;
    @JsonProperty("type")
    private String type;
    @JsonProperty("event")
    private String event;
    @JsonProperty("enabled")
    private Boolean enabled;
    @JsonProperty("name")
    private String name;
    @JsonProperty("channel")
    private List<String> channel = null;
    @JsonProperty("username")
    private String username;
    @JsonProperty("token")
    private String token;
    @JsonProperty("emoji")
    private String emoji;
    @JsonProperty("urls")
    private List<String> urls = null;
    @JsonProperty("scriptEnabled")
    private Boolean scriptEnabled;
    @JsonProperty("userId")
    private String userId;
    @JsonProperty("_createdAt")
    private String createdAt;
    @JsonProperty("_createdBy")
    private CreatedBy createdBy;
    @JsonProperty("_updatedAt")
    private String updatedAt;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getChannel() {
        return channel;
    }

    public void setChannel(List<String> channel) {
        this.channel = channel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public Boolean getScriptEnabled() {
        return scriptEnabled;
    }

    public void setScriptEnabled(Boolean scriptEnabled) {
        this.scriptEnabled = scriptEnabled;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public CreatedBy getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(CreatedBy createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
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
        return "Integration{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", event='" + event + '\'' +
                ", enabled=" + enabled +
                ", name='" + name + '\'' +
                ", channel=" + channel +
                ", username='" + username + '\'' +
                ", emoji='" + emoji + '\'' +
                ", urls=" + urls +
                ", scriptEnabled=" + scriptEnabled +
                ", userId='" + userId + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", createdBy=" + createdBy +
                ", updatedAt='" + updatedAt + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}