package com.coco.kings.Service;

import com.coco.kings.bean.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author 康森
 * @date 2020/4/1 19 : 32 : 57
 * @description 分类业务接口
 */
public interface TypeService {

    Type getTypeByName(String name);

    Type saveType(Type type);

    Type getType(Long id);

    Page<Type> listType(Pageable pageable);

    List<Type> listType();

    List<Type> listTypeTop(Integer size);

    Type updateType(Long id , Type type);

    void deleteType(Long id);
}
