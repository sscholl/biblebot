
package org.sscholl.rocketchat.api;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "type",
        "event",
        "enabled",
        "name",
        "channel",
        "username",
        "urls",
        "scriptEnabled"
})
public class IntegrationCreateRequest {

    @JsonProperty("type")
    private String type;
    @JsonProperty("event")
    private String event;
    @JsonProperty("enabled")
    private Boolean enabled;
    @JsonProperty("name")
    private String name;
    @JsonProperty("channel")
    private String channel;
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
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
    public String getChannel() {
        return channel;
    }

    @JsonProperty("channel")
    public void setChannel(String channel) {
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

    @JsonProperty("token")
    public String getToken() {
        return token;
    }

    @JsonProperty("token")
    public void setToken(String token) {
        this.token = token;
    }

    @JsonProperty("emoji")
    public String getEmoji() {
        return emoji;
    }

    @JsonProperty("emoji")
    public void setEmoji(String emoji) {
        this.emoji = emoji;
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
        return "IntegrationCreateRequest{" +
                "type='" + type + '\'' +
                ", event='" + event + '\'' +
                ", enabled=" + enabled +
                ", name='" + name + '\'' +
                ", channel='" + channel + '\'' +
                ", username='" + username + '\'' +
                ", token='" + token + '\'' +
                ", emoji='" + emoji + '\'' +
                ", urls=" + urls +
                ", scriptEnabled=" + scriptEnabled +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
