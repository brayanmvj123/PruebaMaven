
package com.bdb.opaloshare.persistence.model.CDTCancelaciones.RequestCancelacionCDT;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "canal",
    "infoCLiente",
    "numCdt",
    "fecha",
    "infoOficina",
    "infoTrans"
})
@Generated("jsonschema2pojo")
public class RequestCdt implements Serializable
{

    @JsonProperty("canal")
    public String canal;
    @JsonProperty("infoCLiente")
    public List<InfoCliente> infoCLiente = null;
    @JsonProperty("numCdt")
    public String numCdt;
    @JsonProperty("fecha")
    public String fecha;
    @JsonProperty("infoOficina")
    public InfoOficina infoOficina;
    @JsonProperty("infoTrans")
    public InfoTrans infoTrans;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 8607109042923786817L;

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
