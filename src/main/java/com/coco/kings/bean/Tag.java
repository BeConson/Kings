package com.coco.kings.bean;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 康森
 * @date 2020/3/31 16 : 04 : 11
 * @description 标签实体类
 */
@Entity
@Table(name = "t_tag")
public class Tag {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "请正确输入分类名称哦")
    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<Kings> kings = new ArrayList<>();

    public List<Kings> getKings() {
        return kings;
    }

    public void setKings(List<Kings> kings) {
        this.kings = kings;
    }

    public Tag() {
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
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
}
