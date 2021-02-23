package com.logistics.plan.constant;

public enum FileTypeEnum {

    MAIL("mail", "com.logistics.plan.domain.entity.Mail"),
    NODE("node", "com.logistics.plan.domain.entity.Node"),
    VEHICLE("mail", "com.logistics.plan.domain.entity.Vehicle");


    private String type;

    private String packageName;

    public String getType() {
        return type;
    }

    public String getPackageName() {
        return packageName;
    }

    FileTypeEnum(String type, String packageName) {
        this.type = type;
        this.packageName = packageName;
    }
}
