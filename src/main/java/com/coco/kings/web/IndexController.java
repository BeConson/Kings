package com.coco.kings.web;


import com.coco.kings.Service.KingsService;
import com.coco.kings.Service.TagService;
import com.coco.kings.Service.TypeService;
import com.coco.kings.bean.KingsQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author 康森
 * @date 2020/3/29 22 : 29 : 16
 * @description 首页请求
 */
@Controller
public class IndexController {

    @Autowired
    private KingsService kingsService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(@PageableDefault(size = 6, sort = {"updateTime"},
            direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("page",kingsService.listKings(pageable));
        model.addAttribute("types",typeService.listTypeTop(5));
        model.addAttribute("tags",tagService.listTagTop(10));
        model.addAttribute("recommendKings",kingsService.listKingsTop(5));
        return "index";
    }

    @GetMapping("/information/{id}")
    public String information(@PathVariable Long id, Model model) {
        model.addAttribute("kings",kingsService.getKingConcert(id));
        return "information";
    }

    @GetMapping("/AboutGame")
    public String AboutGame(){
        return "AboutGame";
    }

    @PostMapping("/Search")
    public String search(@PageableDefault(size = 6, sort = {"updateTime"},
            direction = Sort.Direction.DESC) Pageable pageable, Model model ,
            @RequestParam String SearchData){
        model.addAttribute("page",kingsService.listKings("%"+SearchData+"%",pageable));
        model.addAttribute("SearchData",SearchData);
        return "SearchInformation";
    }

    @GetMapping("/Search")
    public String search1(@PageableDefault(size = 6, sort = {"updateTime"},
            direction = Sort.Direction.DESC) Pageable pageable, Model model
                        ){
        return "SearchInformation";
    }

}
