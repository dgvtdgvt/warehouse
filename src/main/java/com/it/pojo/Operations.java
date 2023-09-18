package com.it.pojo;

public class Operations {
    private Integer id;
    private Integer product_id;
    private String product_name;
    private String product_ean;
    private String product_price;
    private String product_specification;
    private Integer quantity;
    private String warehouse_name;
    private String type;
    private String user;
    private String time;

    @Override
    public String toString() {
        return "Operations{" +
                "id=" + id +
                ", product_id=" + product_id +
                ", product_name='" + product_name + '\'' +
                ", product_ean='" + product_ean + '\'' +
                ", product_price='" + product_price + '\'' +
                ", product_specification='" + product_specification + '\'' +
                ", quantity=" + quantity +
                ", warehouse_name='" + warehouse_name + '\'' +
                ", type='" + type + '\'' +
                ", user='" + user + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_ean() {
        return product_ean;
    }

    public void setProduct_ean(String product_ean) {
        this.product_ean = product_ean;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_specification() {
        return product_specification;
    }

    public void setProduct_specification(String product_specification) {
        this.product_specification = product_specification;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getWarehouse_name() {
        return warehouse_name;
    }

    public void setWarehouse_name(String warehouse_name) {
        this.warehouse_name = warehouse_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
