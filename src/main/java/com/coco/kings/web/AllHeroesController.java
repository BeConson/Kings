package com.coco.kings.web;

import com.coco.kings.Service.KingsService;
import com.coco.kings.Service.TagService;
import com.coco.kings.Service.TypeService;
import com.coco.kings.bean.KingsQuery;
import com.coco.kings.bean.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author 康森
 * @date 2020/4/7 20 : 33 : 38
 * @description 所有英雄页请求
 */
@Controller
public class AllHeroesController {

    @Autowired
    private KingsService kingsService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/AllHeroes/{id}")
    public String AllHeroes(@PageableDefault(size = 10000, sort = {"updateTime"},
            direction = Sort.Direction.DESC) Pageable pageable, Model model, @PathVariable Long id) {
        List<Type> types = typeService.listTypeTop(1000);
        if (id ==-1){
            id = types.get(0).getId();
        }
        KingsQuery kingsQuery = new KingsQuery();
        kingsQuery.setTypeId(id);
        model.addAttribute("types",types);
        model.addAttribute("page",kingsService.listKings(pageable, kingsQuery));
        return "AllHeroes";
    }

}
