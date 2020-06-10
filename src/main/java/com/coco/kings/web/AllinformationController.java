package com.coco.kings.web;

import com.coco.kings.Service.KingsService;
import com.coco.kings.Service.TagService;
import com.coco.kings.Service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 康森
 * @date 2020/4/8 20 : 24 : 45
 * @description 所有资讯页请求
 */
@Controller
public class AllinformationController {

    @Autowired
    private KingsService kingsService;

    @Autowired
    private TagService tagService;


    @GetMapping("/Allinformation")
    public String Allinformation(Model model) {
        model.addAttribute("AllinformationMap",kingsService.Allinformation());
        model.addAttribute("countAllinformation",kingsService.countAllinformation());
        return "Allinformation";
    }

}
