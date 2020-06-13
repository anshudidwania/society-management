package ad.society.usermanagement.util;

public enum UserTypeEnum {
    RESIDENT(1,"House resident"),
    VENDOR_MAID(2,"House cleaner"),
    VENDOR_ELECTRICIAN(3,"Electrician"),
    VENDOR_PLUMBER(4,"Plumber"),
    VENDOR_DAIRY(5,"Dairy product supplier"),
    VENDOR_GROCERY(6,"Grocery supplier");

    private Integer id;
    private String description;

    UserTypeEnum(int id, String desc) {
        this.id = id;
        this.description = desc;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
