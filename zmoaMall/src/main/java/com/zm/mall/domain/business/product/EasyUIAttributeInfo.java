package com.zm.mall.domain.business.product;

/**
 * 为了解决SpringMVC接收参数时，如果实体类属性有基本数据类型boolean，
 * SpringMVC接收布尔类型的参数时，页面上的1代表true，0代表false。
 * Created by Bochao on 2017/3/28.
 */
public class EasyUIAttributeInfo extends AttributeInfo {

    public Integer getUseAttributeImage() {
        if (isUseAttributeImage() != null && isUseAttributeImage()) {
            return 1;
        } else {
            return 0;
        }
    }

    public void setUseAttributeImage(Boolean useAttributeImage) {
        super.setUseAttributeImage(useAttributeImage);
    }
}
