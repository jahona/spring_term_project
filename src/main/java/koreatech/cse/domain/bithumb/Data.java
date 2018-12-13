
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
        "opening_price",
        "closing_price",
        "min_price",
        "max_price",
        "average_price",
        "units_traded",
        "volume_1day",
        "volume_7day",
        "buy_price",
        "sell_price",
        "24H_fluctate",
        "24H_fluctate_rate",
        "date"
})
public class Data {
    @JsonProperty("opening_price")
    private String openingPrice;
    @JsonProperty("closing_price")
    private String closingPrice;
    @JsonProperty("min_price")
    private String minPrice;
    @JsonProperty("max_price")
    private String maxPrice;
    @JsonProperty("average_price")
    private String averagePrice;
    @JsonProperty("units_traded")
    private String unitsTraded;
    @JsonProperty("volume_1day")
    private String volume1day;
    @JsonProperty("volume_7day")
    private String volume7day;
    @JsonProperty("buy_price")
    private String buyPrice;
    @JsonProperty("sell_price")
    private String sellPrice;
    @JsonProperty("24H_fluctate")
    private String _24HFluctate;
    @JsonProperty("24H_fluctate_rate")
    private String _24HFluctateRate;
    @JsonProperty("date")
    private String date;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("opening_price")
    public String getOpeningPrice() {
        return openingPrice;
    }

    @JsonProperty("opening_price")
    public void setOpeningPrice(String openingPrice) {
        this.openingPrice = openingPrice;
    }

    @JsonProperty("closing_price")
    public String getClosingPrice() {
        return closingPrice;
    }

    @JsonProperty("closing_price")
    public void setClosingPrice(String closingPrice) {
        this.closingPrice = closingPrice;
    }

    @JsonProperty("min_price")
    public String getMinPrice() {
        return minPrice;
    }

    @JsonProperty("min_price")
    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    @JsonProperty("max_price")
    public String getMaxPrice() {
        return maxPrice;
    }

    @JsonProperty("max_price")
    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    @JsonProperty("average_price")
    public String getAveragePrice() {
        return averagePrice;
    }

    @JsonProperty("average_price")
    public void setAveragePrice(String averagePrice) {
        this.averagePrice = averagePrice;
    }

    @JsonProperty("units_traded")
    public String getUnitsTraded() {
        return unitsTraded;
    }

    @JsonProperty("units_traded")
    public void setUnitsTraded(String unitsTraded) {
        this.unitsTraded = unitsTraded;
    }

    @JsonProperty("volume_1day")
    public String getVolume1day() {
        return volume1day;
    }

    @JsonProperty("volume_1day")
    public void setVolume1day(String volume1day) {
        this.volume1day = volume1day;
    }

    @JsonProperty("volume_7day")
    public String getVolume7day() {
        return volume7day;
    }

    @JsonProperty("volume_7day")
    public void setVolume7day(String volume7day) {
        this.volume7day = volume7day;
    }

    @JsonProperty("buy_price")
    public String getBuyPrice() {
        return buyPrice;
    }

    @JsonProperty("buy_price")
    public void setBuyPrice(String buyPrice) {
        this.buyPrice = buyPrice;
    }

    @JsonProperty("sell_price")
    public String getSellPrice() {
        return sellPrice;
    }

    @JsonProperty("sell_price")
    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }

    @JsonProperty("24H_fluctate")
    public String get24HFluctate() {
        return _24HFluctate;
    }

    @JsonProperty("24H_fluctate")
    public void set24HFluctate(String _24HFluctate) {
        this._24HFluctate = _24HFluctate;
    }

    @JsonProperty("24H_fluctate_rate")
    public String get24HFluctateRate() {
        return _24HFluctateRate;
    }

    @JsonProperty("24H_fluctate_rate")
    public void set24HFluctateRate(String _24HFluctateRate) {
        this._24HFluctateRate = _24HFluctateRate;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
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
        return "Data{" +
                "openingPrice='" + openingPrice + '\'' +
                ", closingPrice='" + closingPrice + '\'' +
                ", minPrice='" + minPrice + '\'' +
                ", maxPrice='" + maxPrice + '\'' +
                ", averagePrice='" + averagePrice + '\'' +
                ", unitsTraded='" + unitsTraded + '\'' +
                ", volume1day='" + volume1day + '\'' +
                ", volume7day='" + volume7day + '\'' +
                ", buyPrice='" + buyPrice + '\'' +
                ", sellPrice='" + sellPrice + '\'' +
                ", _24HFluctate='" + _24HFluctate + '\'' +
                ", _24HFluctateRate='" + _24HFluctateRate + '\'' +
                ", date='" + date + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
