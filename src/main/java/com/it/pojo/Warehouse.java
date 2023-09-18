package com.it.pojo;

public class Warehouse {
    private Integer id;
    private Integer product_id;
    private String product_name;
    private String product_ean;
    private String product_price;
    private String product_specification;
    private Integer product_quantity;
    private String name;

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", product_id=" + product_id +
                ", product_name='" + product_name + '\'' +
                ", product_ean='" + product_ean + '\'' +
                ", product_price='" + product_price + '\'' +
                ", product_specification='" + product_specification + '\'' +
                ", product_quantity=" + product_quantity +
                ", name='" + name + '\'' +
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

    public Integer getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(Integer product_quantity) {
        this.product_quantity = product_quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
