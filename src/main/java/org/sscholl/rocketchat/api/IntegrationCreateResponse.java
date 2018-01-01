package org.sscholl.rocketchat.api;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "integration",
        "success"
})
public class IntegrationCreateResponse {

    @JsonProperty("integration")
    private Integration integration;
    @JsonProperty("success")
    private Boolean success;
    @JsonIgnore
    @JsonProperty("error")
    private String error;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("integration")
    public Integration getIntegration() {
        return integration;
    }

    @JsonProperty("integration")
    public void setIntegration(Integration integration) {
        this.integration = integration;
    }

    @JsonProperty("success")
    public Boolean getSuccess() {
        return success;
    }

    @JsonProperty("success")
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @JsonProperty("error")
    public String getError() {
        return error;
    }

    @JsonProperty("error")
    public void setError(String error) {
        this.error = error;
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
        return "IntegrationCreateResponse{" +
                "integration=" + integration +
                ", success=" + success +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
