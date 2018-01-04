
package org.sscholl.rocketchat.api;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "alias",
        "msg",
        "attachments",
        "parseUrls",
        "groupable",
        "avatar",
        "ts",
        "user",
        "rid",
        "mentions",
        "channels",
        "_updatedAt",
        "_id"
})
public class Message {

    @JsonProperty("alias")
    private String alias;
    @JsonProperty("msg")
    private String msg;
    @JsonProperty("attachments")
    private List<Attachment> attachments = null;
    @JsonProperty("parseUrls")
    private Boolean parseUrls;
    @JsonProperty("groupable")
    private Boolean groupable;
    @JsonProperty("avatar")
    private String avatar;
    @JsonProperty("ts")
    private String ts;
    @JsonProperty("user")
    private User user;
    @JsonProperty("rid")
    private String rid;
    @JsonProperty("mentions")
    private List<Object> mentions = null;
    @JsonProperty("channels")
    private List<Object> channels = null;
    @JsonProperty("_updatedAt")
    private String updatedAt;
    @JsonProperty("_id")
    private String id;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("alias")
    public String getAlias() {
        return alias;
    }

    @JsonProperty("alias")
    public void setAlias(String alias) {
        this.alias = alias;
    }

    @JsonProperty("msg")
    public String getMsg() {
        return msg;
    }

    @JsonProperty("msg")
    public void setMsg(String msg) {
        this.msg = msg;
    }

    @JsonProperty("attachments")
    public List<Attachment> getAttachments() {
        return attachments;
    }

    @JsonProperty("attachments")
    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    @JsonProperty("parseUrls")
    public Boolean getParseUrls() {
        return parseUrls;
    }

    @JsonProperty("parseUrls")
    public void setParseUrls(Boolean parseUrls) {
        this.parseUrls = parseUrls;
    }

    @JsonProperty("groupable")
    public Boolean getGroupable() {
        return groupable;
    }

    @JsonProperty("groupable")
    public void setGroupable(Boolean groupable) {
        this.groupable = groupable;
    }

    @JsonProperty("avatar")
    public String getAvatar() {
        return avatar;
    }

    @JsonProperty("avatar")
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @JsonProperty("ts")
    public String getTs() {
        return ts;
    }

    @JsonProperty("ts")
    public void setTs(String ts) {
        this.ts = ts;
    }

    @JsonProperty("user")
    public User getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(User user) {
        this.user = user;
    }

    @JsonProperty("rid")
    public String getRid() {
        return rid;
    }

    @JsonProperty("rid")
    public void setRid(String rid) {
        this.rid = rid;
    }

    @JsonProperty("mentions")
    public List<Object> getMentions() {
        return mentions;
    }

    @JsonProperty("mentions")
    public void setMentions(List<Object> mentions) {
        this.mentions = mentions;
    }

    @JsonProperty("channels")
    public List<Object> getChannels() {
        return channels;
    }

    @JsonProperty("channels")
    public void setChannels(List<Object> channels) {
        this.channels = channels;
    }

    @JsonProperty("_updatedAt")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("_updatedAt")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("_id")
    public String getId() {
        return id;
    }

    @JsonProperty("_id")
    public void setId(String id) {
        this.id = id;
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
        return "Message{" +
                "alias='" + alias + '\'' +
                ", msg='" + msg + '\'' +
                ", attachments=" + attachments +
                ", parseUrls=" + parseUrls +
                ", groupable=" + groupable +
                ", avatar='" + avatar + '\'' +
                ", ts='" + ts + '\'' +
                ", user=" + user +
                ", rid='" + rid + '\'' +
                ", mentions=" + mentions +
                ", channels=" + channels +
                ", updatedAt='" + updatedAt + '\'' +
                ", id='" + id + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
