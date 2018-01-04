
package org.sscholl.rocketchat.api;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "ts",
        "channel",
        "message",
        "success"
})
public class PostMessageResponse {

    @JsonProperty("ts")
    private Long ts;
    @JsonProperty("channel")
    private String channel;
    @JsonProperty("message")
    private Message message;
    @JsonProperty("success")
    private Boolean success;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("ts")
    public Long getTs() {
        return ts;
    }

    @JsonProperty("ts")
    public void setTs(Long ts) {
        this.ts = ts;
    }

    @JsonProperty("channel")
    public String getChannel() {
        return channel;
    }

    @JsonProperty("channel")
    public void setChannel(String channel) {
        this.channel = channel;
    }

    @JsonProperty("message")
    public Message getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(Message message) {
        this.message = message;
    }

    @JsonProperty("success")
    public Boolean getSuccess() {
        return success;
    }

    @JsonProperty("success")
    public void setSuccess(Boolean success) {
        this.success = success;
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
        return "PostMessageResponse{" +
                "ts=" + ts +
                ", channel='" + channel + '\'' +
                ", message=" + message +
                ", success=" + success +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
