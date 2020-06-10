package com.coco.kings.web.admin;

import com.coco.kings.Service.KingsService;
import com.coco.kings.Service.TagService;
import com.coco.kings.Service.TypeService;
import com.coco.kings.bean.Kings;
import com.coco.kings.bean.KingsQuery;
import com.coco.kings.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author 康森
 * @date 2020/4/1 11 : 02 : 27
 * @description 后台
 */
@Controller
@RequestMapping("/kings")
public class KingsController {

    @Autowired
    private KingsService kingsService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;


    @GetMapping("/kings")
    public String kings() {
        return "admin/admin2";
    }

    @GetMapping("/WriteKings")
    public String kings(@PageableDefault(size = 5, sort = {"updateTime"},
            direction = Sort.Direction.DESC) Pageable pageable, KingsQuery kingsQuery, Model model) {
        model.addAttribute("types",typeService.listType());
        model.addAttribute("page", kingsService.listKings(pageable, kingsQuery));
        return "admin/WriteKings";
    }

    @PostMapping("/WriteKings/Search")
    public String search(@PageableDefault(size = 5, sort = {"updateTime"},
            direction = Sort.Direction.DESC) Pageable pageable, KingsQuery kingsQuery, Model model) {
        model.addAttribute("page",kingsService.listKings(pageable, kingsQuery));
        return "admin/WriteKings :: kingsList";
    }

    @GetMapping("/WriteKings/input")
    public String WriteKingsInput(Model model) {
        modelAddAttribute(model);
        model.addAttribute("kings",new Kings());
        return "admin/WriteKings-input";
    }

    private void modelAddAttribute(Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagService.listTag());
    }

    @GetMapping("/WriteKings/{id}/input")
    public String editWriteKingsInput(@PathVariable Long id, Model model) {
        modelAddAttribute(model);
        Kings kings = kingsService.getKing(id);
        kings.init();
        model.addAttribute("kings",kings);
        return "admin/WriteKings-input";
    }

    @PostMapping("/WriteKings")
    public String post(Kings kings, HttpSession session , RedirectAttributes attributes){
        kings.setUser((User) session.getAttribute("user"));
        kings.setType(typeService.getType(kings.getType().getId()));
        kings.setTags(tagService.listTag(kings.getTagIds()));
        Kings k = kingsService.saveKings(kings);
        if (k == null) {
            attributes.addFlashAttribute("message", "更新数据失败耶");
        } else {
            attributes.addFlashAttribute("message", "更新数据成功啦");
        }
        return "redirect:/kings/WriteKings";
    }

    @GetMapping("/WriteKings/{id}/delete")
    public String delete(@PathVariable Long id ,RedirectAttributes attributes){
        kingsService.deleteKings(id);
        attributes.addFlashAttribute("message", "删除成功耶");
        return "redirect:/kings/WriteKings";
    }

}
