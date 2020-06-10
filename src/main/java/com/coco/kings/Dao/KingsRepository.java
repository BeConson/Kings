package com.coco.kings.Dao;

import com.coco.kings.bean.Kings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author 康森
 * @date 2020/4/1 17 : 54 : 46
 * @description kings 内容查询接口
 */
public interface KingsRepository extends JpaRepository<Kings, Long>, JpaSpecificationExecutor<Kings> {

    @Query("select k from Kings k where k.recommend = true ")
    List<Kings> findTop(Pageable pageable);

    @Query("select k from Kings k where k.title like ?1 or k.content like ?1")
    Page<Kings> findBySearchData(String SearchData, Pageable pageable);

    @Query("select function('date_format',k.updateTime,'%Y') as year from Kings k group by function('date_format',k.updateTime,'%Y') order by year desc")
    List<String> findGroupYear();

    @Query("select k from Kings k where function('date_format',k.updateTime,'%Y') = ?1")
    List<Kings> findByYear(String year);

}