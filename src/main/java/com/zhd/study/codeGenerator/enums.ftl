package com.zhd.ultimate.sociology.entity.enums;


public enum ${firstUpColumnName}Enum {
    <#list columnCommentMap?keys as key>
    S${key}(${key}, "${columnCommentMap[key]}"),
    </#list>
    ;


    public int code;
    public String msg;

    ${firstUpColumnName}Enum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg(int code) {
        ${firstUpColumnName}Enum[] values = values();
        for (${firstUpColumnName}Enum ${columnName}Enum : values) {
            if (${columnName}Enum.code == code) {
                return ${columnName}Enum.msg;
            }
        }
        return "";
    }

}
