package com.zhangpan.constant;

/**
 * 需和字典类型表一致
 * @author zhangpan
 * @date 2018年11月22日
 */
public enum DictTypeEnum {
    
    /**
     * 文章类型
     */
    ARTICLETYPE("articleType"),
    
    /**
     * 城市
     */
    CITY("city"),
    
    /**
     * 性别
     */
    SEX("sex");
    
    private String value;
    private int index;
    
    private DictTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    
}
