package com.zm.mall.domain.business.product;

/**
 * EasyUI树形展现产品分类
 * Created by Bochao on 2017/3/23.
 */
public class EasyUITreeCatInfo extends CategoryInfo {
    /**
     *  用在EasyUI中表示Tree的打开状态
     */
    private String state;

    /**
     * 分类按区域展现-数据回显
     */
    private boolean selected;

    /**
     *  用在EasyUI中异步加载子分类
     */
    public Integer getParentId() {
        return super.getParentCategoryId();
    }

    public Integer getId() {
        return super.getCategoryId();
    }

    /**
     * ComboTree展现
     * @return
     */
    public String getText() {
        return this.getName();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
