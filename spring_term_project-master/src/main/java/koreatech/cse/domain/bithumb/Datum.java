
package koreatech.cse.domain.bithumb;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "cont_no",
    "transaction_date",
    "type",
    "units_traded",
    "price",
    "total"
})
public class Datum {

    @JsonProperty("cont_no")
    private String contNo;
    @JsonProperty("transaction_date")
    private String transactionDate;
    @JsonProperty("type")
    private String type;
    @JsonProperty("units_traded")
    private String unitsTraded;
    @JsonProperty("price")
    private String price;
    @JsonProperty("total")
    private String total;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("cont_no")
    public String getContNo() {
        return contNo;
    }

    @JsonProperty("cont_no")
    public void setContNo(String contNo) {
        this.contNo = contNo;
    }

    @JsonProperty("transaction_date")
    public String getTransactionDate() {
        return transactionDate;
    }

    @JsonProperty("transaction_date")
    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("units_traded")
    public String getUnitsTraded() {
        return unitsTraded;
    }

    @JsonProperty("units_traded")
    public void setUnitsTraded(String unitsTraded) {
        this.unitsTraded = unitsTraded;
    }

    @JsonProperty("price")
    public String getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(String price) {
        this.price = price;
    }

    @JsonProperty("total")
    public String getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(String total) {
        this.total = total;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
