
package org.sscholl.rocketchat.message;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "title",
        "title_link",
        "text",
        "image_url",
        "color",
        "footer",
        "mrkdwn_in"
})
public class Attachment {

    @JsonProperty("title")
    private String title;
    @JsonProperty("title_link")
    private String titleLink;
    @JsonProperty("text")
    private String text;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("color")
    private String color;
    @JsonProperty("footer")
    private String footer;
    @JsonProperty("mrkdwn_in")
    private List<String> mrkdwnIn = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonProperty("image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    @JsonProperty("image_url")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @JsonProperty("color")
    public String getColor() {
        return color;
    }

    @JsonProperty("color")
    public void setColor(String color) {
        this.color = color;
    }

    @JsonProperty("footer")
    public String getFooter() {
        return footer;
    }

    @JsonProperty("footer")
    public void setFooter(String footer) {
        this.footer = footer;
    }

    @JsonProperty("mrkdwn_in")
    public List<String> getMrkdwnIn() {
        return mrkdwnIn;
    }

    @JsonProperty("mrkdwn_in")
    public void setMrkdwnIn(List<String> mrkdwnIn) {
        this.mrkdwnIn = mrkdwnIn;
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
                "title='" + title + '\'' +
                ", titleLink='" + titleLink + '\'' +
                ", text='" + text + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", color='" + color + '\'' +
                ", footer='" + footer + '\'' +
                ", mrkdwnIn=" + mrkdwnIn +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
