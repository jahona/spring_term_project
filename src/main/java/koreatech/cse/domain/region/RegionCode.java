package koreatech.cse.domain.region;

public class RegionCode {
    private int id;
    private String code;
    private String state;
    private String city;
    private String sub1;
    private String sub2;
    private String created_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() { return code; }

    public void setCode(String code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSub1() { return sub1; }

    public void setSub1(String sub1) {
        this.sub1 = sub1;
    }

    public String getSub2() {
        return sub2;
    }

    public void setSub2(String sub2) {
        this.sub2 = sub2;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "RegionCode{" +
                "code=" + code +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", sub1='" + sub1 + '\'' +
                ", sub2='" + sub2 + '\'' +
                ", created_at=" + created_at +
                '}';
    }
}
