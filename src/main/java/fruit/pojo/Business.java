package fruit.pojo;

public class Business {

    private Integer businessId;
    private String businessName;
    private String businessExplain;



    public Integer getBusinessId() {
        return businessId;
    }
    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }
    public String getBusinessName() {
        return businessName;
    }
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
    public String getBusinessExplain() {
        return businessExplain;
    }
    public void setBusinessExplain(String businessExplain) {
        this.businessExplain = businessExplain;
    }

    @Override
    public String toString() {
        return "\n交易编号：" + this.businessId +
                "\n交易名称：" + this.businessName +
                "\n交易介绍：" + this.businessExplain;
    }
}

