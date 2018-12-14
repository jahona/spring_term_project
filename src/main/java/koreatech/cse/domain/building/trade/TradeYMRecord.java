package koreatech.cse.domain.building.trade;

public class TradeYMRecord {
    private int id;

    private int minimumDeal;

    private int maximumDeal;

    private int averageDeal;

    private int minimumTradeId;

    private int maximumTradeId;

    private String regionalCode;

    private String dealYM;

    private int tradeCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMinimumDeal() {
        return minimumDeal;
    }

    public void setMinimumDeal(int minimumDeal) {
        this.minimumDeal = minimumDeal;
    }

    public int getMaximumDeal() {
        return maximumDeal;
    }

    public void setMaximumDeal(int maximumDeal) {
        this.maximumDeal = maximumDeal;
    }

    public int getAverageDeal() {
        return averageDeal;
    }

    public void setAverageDeal(int averageDeal) {
        this.averageDeal = averageDeal;
    }

    public int getMinimumTradeId() {
        return minimumTradeId;
    }

    public void setMinimumTradeId(int minimumTradeId) {
        this.minimumTradeId = minimumTradeId;
    }

    public int getMaximumTradeId() {
        return maximumTradeId;
    }

    public void setMaximumTradeId(int maximumTradeId) {
        this.maximumTradeId = maximumTradeId;
    }

    public String getRegionalCode() {
        return regionalCode;
    }

    public void setRegionalCode(String regionalCode) {
        this.regionalCode = regionalCode;
    }

    public String getDealYM() {
        return dealYM;
    }

    public void setDealYM(String dealYM) {
        this.dealYM = dealYM;
    }

    public int getTradeCount() {
        return tradeCount;
    }

    public void setTradeCount(int tradeCount) {
        this.tradeCount = tradeCount;
    }

    @Override
    public String toString() {
        return "TradeYMRecord{" +
                "id=" + id +
                ", minimumDeal=" + minimumDeal +
                ", maximumDeal=" + maximumDeal +
                ", averageDeal=" + averageDeal +
                ", minimumTradeId=" + minimumTradeId +
                ", maximumTradeId=" + maximumTradeId +
                ", regionalCode='" + regionalCode + '\'' +
                ", DealYM='" + dealYM + '\'' +
                ", tradeCount=" + tradeCount +
                '}';
    }
}
