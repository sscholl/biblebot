package org.sscholl.rocketchat.api;


import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "integrations",
        "offset",
        "items",
        "total",
        "success"
})
public class IntegrationsResponse {

    @JsonProperty("integrations")
    private List<Integration> integrations = null;
    @JsonProperty("offset")
    private Integer offset;
    @JsonProperty("items")
    private Integer items;
    @JsonProperty("total")
    private Integer total;
    @JsonProperty("success")
    private Boolean success;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("integrations")
    public List<Integration> getIntegrations() {
        return integrations;
    }

    @JsonProperty("integrations")
    public void setIntegrations(List<Integration> integrations) {
        this.integrations = integrations;
    }

    @JsonProperty("offset")
    public Integer getOffset() {
        return offset;
    }

    @JsonProperty("offset")
    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @JsonProperty("items")
    public Integer getItems() {
        return items;
    }

    @JsonProperty("items")
    public void setItems(Integer items) {
        this.items = items;
    }

    @JsonProperty("total")
    public Integer getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(Integer total) {
        this.total = total;
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
        return "IntegrationsResponse{" +
                "integrations=" + integrations +
                ", offset=" + offset +
                ", items=" + items +
                ", total=" + total +
                ", success=" + success +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}