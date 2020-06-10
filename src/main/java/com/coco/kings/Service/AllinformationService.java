package com.coco.kings.Service;

import com.coco.kings.bean.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author 康森
 * @date 2020/4/9 18 : 24 : 29
 * @description 所有资讯接口
 */
public interface AllinformationService {

    Page<Tag> listTag(Pageable pageable);
}
