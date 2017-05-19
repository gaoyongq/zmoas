package com.zm.mall.domain.business.orders;

import com.zm.mall.domain.business.product.SKUInfo;
import com.zm.mall.domain.business.product.SKUItem;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/8.
 */
public class SkuData implements Serializable{
    private String key;
    private SKUInfo skuInfo;
    private String pluteformid;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public SKUInfo getSkuInfo() {
        return skuInfo;
    }

    public void setSkuInfo(SKUInfo skuInfo) {
        this.skuInfo = skuInfo;
    }

    public String getPluteformid() {
        return pluteformid;
    }

    public void setPluteformid(String pluteformid) {
        this.pluteformid = pluteformid;
    }

    public SkuData(String key, SKUInfo skuInfo) {
        this.key = key;
        this.skuInfo = skuInfo;
    }

    public SkuData() {
    }

    @Override
    public String toString() {
        return "SkuData{" +
                "key='" + key + '\'' +
                ", skuInfo=" + skuInfo +
                '}';
    }
}
