package com.meiborte.www;

/**
 * ID      类别       账户         类型          金额          时间          备注
 */
public class Bills {
    private String name;
    private String account;
    private String type;//支出，收入
    private double total;
    private String time;
    private String desc;


    public Bills() {
    }

    public Bills(String name, String account, String type, double total, String time, String desc) {
        this.name = name;
        this.account = account;
        this.type = type;
        this.total = total;
        this.time = time;
        this.desc = desc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getAccount() {
        return account;
    }

    public String getType() {
        return type;
    }

    public double getTotal() {
        return total;
    }

    public String getTime() {
        return time;
    }

    public String getDesc() {
        return desc;
    }

}
