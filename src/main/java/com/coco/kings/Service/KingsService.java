package com.coco.kings.Service;

import com.coco.kings.bean.Kings;
import com.coco.kings.bean.KingsQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @author 康森
 * @date 2020/4/1 17 : 46 : 38
 * @description kings 内容接口
 */
public interface KingsService {

    Kings getKing(Long id);

    Kings getKingConcert(Long id);

    Long countAllinformation();

    List<Kings> listKingsTop(Integer size);

    Map<String,List<Kings>> Allinformation();

    Page<Kings> listKings(Pageable pageable , KingsQuery kingsQuery);

    Page<Kings> listKings(Pageable pageable);

    Page<Kings> listKings(String SearchData , Pageable pageable);

    Kings saveKings(Kings kings);

    Kings updateBlog(Long id, Kings kings);

    void deleteKings(Long id);

}
