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

    @JsonIgnore
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

    @JsonProperty("_id")
    public String getId() {
        return id;
    }

    @JsonProperty("_id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("event")
    public String getEvent() {
        return event;
    }

    @JsonProperty("event")
    public void setEvent(String event) {
        this.event = event;
    }

    @JsonProperty("enabled")
    public Boolean getEnabled() {
        return enabled;
    }

    @JsonProperty("enabled")
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("channel")
    public List<String> getChannel() {
        return channel;
    }

    @JsonProperty("channel")
    public void setChannel(List<String> channel) {
        this.channel = channel;
    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("urls")
    public List<String> getUrls() {
        return urls;
    }

    @JsonProperty("urls")
    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    @JsonProperty("scriptEnabled")
    public Boolean getScriptEnabled() {
        return scriptEnabled;
    }

    @JsonProperty("scriptEnabled")
    public void setScriptEnabled(Boolean scriptEnabled) {
        this.scriptEnabled = scriptEnabled;
    }

    @JsonProperty("userId")
    public String getUserId() {
        return userId;
    }

    @JsonProperty("userId")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonProperty("_createdAt")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("_createdAt")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("_createdBy")
    public CreatedBy getCreatedBy() {
        return createdBy;
    }

    @JsonProperty("_createdBy")
    public void setCreatedBy(CreatedBy createdBy) {
        this.createdBy = createdBy;
    }

    @JsonProperty("_updatedAt")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("_updatedAt")
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