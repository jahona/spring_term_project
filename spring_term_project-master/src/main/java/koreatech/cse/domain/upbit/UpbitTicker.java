
package koreatech.cse.domain.upbit;

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
    "market",
    "trade_date",
    "trade_time",
    "trade_date_kst",
    "trade_time_kst",
    "trade_timestamp",
    "opening_price",
    "high_price",
    "low_price",
    "trade_price",
    "prev_closing_price",
    "change",
    "change_price",
    "change_rate",
    "signed_change_price",
    "signed_change_rate",
    "trade_volume",
    "acc_trade_price",
    "acc_trade_price_24h",
    "acc_trade_volume",
    "acc_trade_volume_24h",
    "highest_52_week_price",
    "highest_52_week_date",
    "lowest_52_week_price",
    "lowest_52_week_date",
    "timestamp"
})
public class UpbitTicker {

    @JsonProperty("market")
    private String market;
    @JsonProperty("trade_date")
    private String tradeDate;
    @JsonProperty("trade_time")
    private String tradeTime;
    @JsonProperty("trade_date_kst")
    private String tradeDateKst;
    @JsonProperty("trade_time_kst")
    private String tradeTimeKst;
    @JsonProperty("trade_timestamp")
    private Long tradeTimestamp;
    @JsonProperty("opening_price")
    private Integer openingPrice;
    @JsonProperty("high_price")
    private Integer highPrice;
    @JsonProperty("low_price")
    private Integer lowPrice;
    @JsonProperty("trade_price")
    private Integer tradePrice;
    @JsonProperty("prev_closing_price")
    private Integer prevClosingPrice;
    @JsonProperty("change")
    private String change;
    @JsonProperty("change_price")
    private Integer changePrice;
    @JsonProperty("change_rate")
    private Double changeRate;
    @JsonProperty("signed_change_price")
    private Integer signedChangePrice;
    @JsonProperty("signed_change_rate")
    private Double signedChangeRate;
    @JsonProperty("trade_volume")
    private Double tradeVolume;
    @JsonProperty("acc_trade_price")
    private Double accTradePrice;
    @JsonProperty("acc_trade_price_24h")
    private Double accTradePrice24h;
    @JsonProperty("acc_trade_volume")
    private Double accTradeVolume;
    @JsonProperty("acc_trade_volume_24h")
    private Double accTradeVolume24h;
    @JsonProperty("highest_52_week_price")
    private Integer highest52WeekPrice;
    @JsonProperty("highest_52_week_date")
    private String highest52WeekDate;
    @JsonProperty("lowest_52_week_price")
    private Integer lowest52WeekPrice;
    @JsonProperty("lowest_52_week_date")
    private String lowest52WeekDate;
    @JsonProperty("timestamp")
    private Long timestamp;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("market")
    public String getMarket() {
        return market;
    }

    @JsonProperty("market")
    public void setMarket(String market) {
        this.market = market;
    }

    @JsonProperty("trade_date")
    public String getTradeDate() {
        return tradeDate;
    }

    @JsonProperty("trade_date")
    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    @JsonProperty("trade_time")
    public String getTradeTime() {
        return tradeTime;
    }

    @JsonProperty("trade_time")
    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    @JsonProperty("trade_date_kst")
    public String getTradeDateKst() {
        return tradeDateKst;
    }

    @JsonProperty("trade_date_kst")
    public void setTradeDateKst(String tradeDateKst) {
        this.tradeDateKst = tradeDateKst;
    }

    @JsonProperty("trade_time_kst")
    public String getTradeTimeKst() {
        return tradeTimeKst;
    }

    @JsonProperty("trade_time_kst")
    public void setTradeTimeKst(String tradeTimeKst) {
        this.tradeTimeKst = tradeTimeKst;
    }

    @JsonProperty("trade_timestamp")
    public Long getTradeTimestamp() {
        return tradeTimestamp;
    }

    @JsonProperty("trade_timestamp")
    public void setTradeTimestamp(Long tradeTimestamp) {
        this.tradeTimestamp = tradeTimestamp;
    }

    @JsonProperty("opening_price")
    public Integer getOpeningPrice() {
        return openingPrice;
    }

    @JsonProperty("opening_price")
    public void setOpeningPrice(Integer openingPrice) {
        this.openingPrice = openingPrice;
    }

    @JsonProperty("high_price")
    public Integer getHighPrice() {
        return highPrice;
    }

    @JsonProperty("high_price")
    public void setHighPrice(Integer highPrice) {
        this.highPrice = highPrice;
    }

    @JsonProperty("low_price")
    public Integer getLowPrice() {
        return lowPrice;
    }

    @JsonProperty("low_price")
    public void setLowPrice(Integer lowPrice) {
        this.lowPrice = lowPrice;
    }

    @JsonProperty("trade_price")
    public Integer getTradePrice() {
        return tradePrice;
    }

    @JsonProperty("trade_price")
    public void setTradePrice(Integer tradePrice) {
        this.tradePrice = tradePrice;
    }

    @JsonProperty("prev_closing_price")
    public Integer getPrevClosingPrice() {
        return prevClosingPrice;
    }

    @JsonProperty("prev_closing_price")
    public void setPrevClosingPrice(Integer prevClosingPrice) {
        this.prevClosingPrice = prevClosingPrice;
    }

    @JsonProperty("change")
    public String getChange() {
        return change;
    }

    @JsonProperty("change")
    public void setChange(String change) {
        this.change = change;
    }

    @JsonProperty("change_price")
    public Integer getChangePrice() {
        return changePrice;
    }

    @JsonProperty("change_price")
    public void setChangePrice(Integer changePrice) {
        this.changePrice = changePrice;
    }

    @JsonProperty("change_rate")
    public Double getChangeRate() {
        return changeRate;
    }

    @JsonProperty("change_rate")
    public void setChangeRate(Double changeRate) {
        this.changeRate = changeRate;
    }

    @JsonProperty("signed_change_price")
    public Integer getSignedChangePrice() {
        return signedChangePrice;
    }

    @JsonProperty("signed_change_price")
    public void setSignedChangePrice(Integer signedChangePrice) {
        this.signedChangePrice = signedChangePrice;
    }

    @JsonProperty("signed_change_rate")
    public Double getSignedChangeRate() {
        return signedChangeRate;
    }

    @JsonProperty("signed_change_rate")
    public void setSignedChangeRate(Double signedChangeRate) {
        this.signedChangeRate = signedChangeRate;
    }

    @JsonProperty("trade_volume")
    public Double getTradeVolume() {
        return tradeVolume;
    }

    @JsonProperty("trade_volume")
    public void setTradeVolume(Double tradeVolume) {
        this.tradeVolume = tradeVolume;
    }

    @JsonProperty("acc_trade_price")
    public Double getAccTradePrice() {
        return accTradePrice;
    }

    @JsonProperty("acc_trade_price")
    public void setAccTradePrice(Double accTradePrice) {
        this.accTradePrice = accTradePrice;
    }

    @JsonProperty("acc_trade_price_24h")
    public Double getAccTradePrice24h() {
        return accTradePrice24h;
    }

    @JsonProperty("acc_trade_price_24h")
    public void setAccTradePrice24h(Double accTradePrice24h) {
        this.accTradePrice24h = accTradePrice24h;
    }

    @JsonProperty("acc_trade_volume")
    public Double getAccTradeVolume() {
        return accTradeVolume;
    }

    @JsonProperty("acc_trade_volume")
    public void setAccTradeVolume(Double accTradeVolume) {
        this.accTradeVolume = accTradeVolume;
    }

    @JsonProperty("acc_trade_volume_24h")
    public Double getAccTradeVolume24h() {
        return accTradeVolume24h;
    }

    @JsonProperty("acc_trade_volume_24h")
    public void setAccTradeVolume24h(Double accTradeVolume24h) {
        this.accTradeVolume24h = accTradeVolume24h;
    }

    @JsonProperty("highest_52_week_price")
    public Integer getHighest52WeekPrice() {
        return highest52WeekPrice;
    }

    @JsonProperty("highest_52_week_price")
    public void setHighest52WeekPrice(Integer highest52WeekPrice) {
        this.highest52WeekPrice = highest52WeekPrice;
    }

    @JsonProperty("highest_52_week_date")
    public String getHighest52WeekDate() {
        return highest52WeekDate;
    }

    @JsonProperty("highest_52_week_date")
    public void setHighest52WeekDate(String highest52WeekDate) {
        this.highest52WeekDate = highest52WeekDate;
    }

    @JsonProperty("lowest_52_week_price")
    public Integer getLowest52WeekPrice() {
        return lowest52WeekPrice;
    }

    @JsonProperty("lowest_52_week_price")
    public void setLowest52WeekPrice(Integer lowest52WeekPrice) {
        this.lowest52WeekPrice = lowest52WeekPrice;
    }

    @JsonProperty("lowest_52_week_date")
    public String getLowest52WeekDate() {
        return lowest52WeekDate;
    }

    @JsonProperty("lowest_52_week_date")
    public void setLowest52WeekDate(String lowest52WeekDate) {
        this.lowest52WeekDate = lowest52WeekDate;
    }

    @JsonProperty("timestamp")
    public Long getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
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
