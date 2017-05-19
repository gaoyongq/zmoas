package com.zm.mall.domain.system.coupon;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Bochao on 2017/2/28.
 */
public class ProductCategory {
    private Long id;
    @JsonProperty("text")
    private String name;
    private Long parentId; //
    private String state; //'closed' : 'open'
    @JsonProperty("children")
    private List<Product> productList;

    public ProductCategory() {
    }

    public ProductCategory(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductCategory that = (ProductCategory) o;

        if (!id.equals(that.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
