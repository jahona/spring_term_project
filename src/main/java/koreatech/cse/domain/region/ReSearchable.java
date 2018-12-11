package koreatech.cse.domain.region;


public class ReSearchable {
    private String state;
    private String city;
    private String sub1;
    private String sub2;
    private String orderParam;

    public String getState() { return state; }

    public void setState(String state) { this.state = state; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getSub1() { return sub1; }

    public void setSub1(String sub1) { this.sub1 = sub1; }

    public String getSub2() { return sub2; }

    public void setSub2(String sub2) { this.sub2 = sub2; }

    public String getOrderParam() {
        return orderParam;
    }

    public void setOrderParam(String orderParam) {
        this.orderParam = orderParam;
    }
}
