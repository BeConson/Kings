package com.coco.kings.Service.Impl;

import com.coco.kings.Dao.KingsRepository;
import com.coco.kings.Exception.NotFoundException;
import com.coco.kings.Service.KingsService;
import com.coco.kings.Util.MarkDownUtil;
import com.coco.kings.bean.Kings;
import com.coco.kings.bean.KingsQuery;
import com.coco.kings.bean.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

/**
 * @author 康森
 * @date 2020/4/1 17 : 54 : 02
 * @description kings 内容实现
 */
@Service
public class KingsServiceImpl implements KingsService {

    @Autowired
    private KingsRepository kingsRepository;

    @Override
    public Kings getKing(Long id) {
        return kingsRepository.getOne(id);
    }

    @Override
    public Kings getKingConcert(Long id) {
        Kings kings = kingsRepository.getOne(id);
        if (kings == null){
            throw new NotFoundException("资讯不存在");
        }
        Kings k = new Kings();
        BeanUtils.copyProperties(kings,k);
        String content = k.getContent();
        k.setContent(MarkDownUtil.MarkDownToHtmlExtensions(content));
        return k;
    }

    @Override
    public Long countAllinformation() {
        return kingsRepository.count();
    }

    @Override
    public List<Kings> listKingsTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"updateTime");
        Pageable pageable = PageRequest.of(0,size,sort);
        return kingsRepository.findTop(pageable);
    }

    @Override
    public Map<String, List<Kings>> Allinformation() {
        List<String> years = kingsRepository.findGroupYear();
        Map<String , List<Kings>> map = new HashMap<>();
        for (String year : years){
            map.put(year,kingsRepository.findByYear(year));
        }
        return map;
    }

    @Override
    public Page<Kings> listKings(Pageable pageable, KingsQuery kingsQuery) {
        return kingsRepository.findAll(new Specification<Kings>() {
            @Override
            public Predicate toPredicate(Root<Kings> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(kingsQuery.getTitle()) && kingsQuery.getTitle() != null){
                    predicates.add(criteriaBuilder.like(root.<String>get("title"),"%"+kingsQuery.getTitle()+"%"));
                }
                if (kingsQuery.getTypeId()!=null){
                    predicates.add(criteriaBuilder.equal(root.<Type>get("type").get("id"),kingsQuery.getTypeId()));
                }
                if (kingsQuery.isRecommend()){
                    predicates.add(criteriaBuilder.equal(root.<Boolean>get("recommend"),kingsQuery.isRecommend()));
                }
                criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }

    @Override
    public Page<Kings> listKings(Pageable pageable) {
        return kingsRepository.findAll(pageable);
    }

    @Override
    public Page<Kings> listKings(String SearchData, Pageable pageable) {
        return kingsRepository.findBySearchData(SearchData,pageable);
    }

    @Transactional
    @Override
    public Kings saveKings(Kings kings) {
        if (kings.getId() == null){
            kings.setCreateTime(new Date());
            kings.setUpdateTime(new Date());
        }else {
            kings.setUpdateTime(new Date());
        }
        return kingsRepository.save(kings);
    }

    @Transactional
    @Override
    public Kings updateBlog(Long id, Kings kings) {
        Kings k = kingsRepository.getOne(id);
        if (k==null){
            throw new NotFoundException("该博客不存在");
        }
        BeanUtils.copyProperties(k,kings);
        return kingsRepository.save(k);
    }

    @Transactional
    @Override
    public void deleteKings(Long id) {
        kingsRepository.deleteById(id);
    }
}
