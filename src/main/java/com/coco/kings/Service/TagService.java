package com.coco.kings.Service;

import com.coco.kings.bean.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author 康森
 * @date 2020/4/1 19 : 32 : 57
 * @description 标签业务接口
 */
public interface TagService {

    Tag getTagByName(String name);

    Tag saveTag(Tag Tag);

    Tag getTag(Long id);

    Page<Tag> listTag(Pageable pageable);

    List<Tag> listTag();

    List<Tag> listTagTop(Integer size);

    List<Tag> listTag(String ids);

    Tag updateTag(Long id, Tag Tag);

    void deleteTag(Long id);
}
