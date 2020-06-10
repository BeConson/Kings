package com.coco.kings.bean;

/**
 * @author 康森
 * @date 2020/4/4 18 : 35 : 16
 * @description kingsQuery 实体类
 */
public class KingsQuery {

    private String title;
    private Long typeId;
    private boolean recommend;

    public KingsQuery() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }
}
