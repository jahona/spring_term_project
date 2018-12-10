package koreatech.cse.domain.building.trade;

public class TradeItem {
    private String dealAmount;

    private String buildingArea;

    private String buildingUse;

    private String buildYear;

    private String classification;

    private String dealYear;

    private String plottage;

    private String dong;

    private String sigungu;

    private String landUse;

    private String dealMonth;

    private String buildingType;

    private String dealDay;

    private String regionalCode;

    private String floor;

    public String getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(String dealAmount) {
        this.dealAmount = dealAmount;
    }

    public String getBuildingArea() {
        return buildingArea;
    }

    public void setBuildingArea(String buildingArea) {
        this.buildingArea = buildingArea;
    }

    public String getBuildingUse() {
        return buildingUse;
    }

    public void setBuildingUse(String buildingUse) {
        this.buildingUse = buildingUse;
    }

    public String getBuildYear() {
        return buildYear;
    }

    public void setBuildYear(String buildYear) {
        this.buildYear = buildYear;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getDealYear() {
        return dealYear;
    }

    public void setDealYear(String dealYear) {
        this.dealYear = dealYear;
    }

    public String getPlottage() {
        return plottage;
    }

    public void setPlottage(String plottage) {
        this.plottage = plottage;
    }

    public String getDong() {
        return dong;
    }

    public void setDong(String dong) {
        this.dong = dong;
    }

    public String getSigungu() {
        return sigungu;
    }

    public void setSigungu(String sigungu) {
        this.sigungu = sigungu;
    }

    public String getLandUse() {
        return landUse;
    }

    public void setLandUse(String landUse) {
        this.landUse = landUse;
    }

    public String getDealMonth() {
        return dealMonth;
    }

    public void setDealMonth(String dealMonth) {
        this.dealMonth = dealMonth;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public String getDealDay() {
        return dealDay;
    }

    public void setDealDay(String dealDay) {
        this.dealDay = dealDay;
    }

    public String getRegionalCode() {
        return regionalCode;
    }

    public void setRegionalCode(String regionalCode) {
        this.regionalCode = regionalCode;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    @Override
    public String toString() {
        return "TradeItem{" +
                "dealAmount='" + dealAmount + '\'' +
                ", buildingArea='" + buildingArea + '\'' +
                ", buildingUse='" + buildingUse + '\'' +
                ", buildYear='" + buildYear + '\'' +
                ", classification='" + classification + '\'' +
                ", dealYear='" + dealYear + '\'' +
                ", plottage='" + plottage + '\'' +
                ", dong='" + dong + '\'' +
                ", sigungu='" + sigungu + '\'' +
                ", landUse='" + landUse + '\'' +
                ", dealMonth='" + dealMonth + '\'' +
                ", buildingType='" + buildingType + '\'' +
                ", dealDay='" + dealDay + '\'' +
                ", regionalCode='" + regionalCode + '\'' +
                ", floor='" + floor + '\'' +
                '}';
    }
}
