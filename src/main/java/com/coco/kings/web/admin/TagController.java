package com.coco.kings.web.admin;

import com.coco.kings.Service.TagService;
import com.coco.kings.bean.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author 康森
 * @date 2020/4/1 19 : 49 : 37
 * @description 标签页请求
 */
@Controller
@RequestMapping("/kings")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/Tags")
    public String list(@PageableDefault(size = 5,sort = {"id"},
            direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("page",tagService.listTag(pageable));
        return "admin/Tags";
    }

    @GetMapping("/Tags/input")
    public String input(Model model){
        model.addAttribute("tag",new Tag());
            return "admin/Tags-input";
    }

    @GetMapping("Tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("tag",tagService.getTag(id));
        return "admin/Tags-input";
    }

    @PostMapping("/TagsIn")
    public String post(@Valid Tag tag, BindingResult result, RedirectAttributes attributes) {
        Tag tagByName = tagService.getTagByName(tag.getName());
        if (tagByName !=null){
            result.rejectValue("name","nameError","标签名称重复哦");
        }
        if (result.hasErrors()){
            return "admin/Tags-input";
        }
        Tag t = tagService.saveTag(tag);
        if (t == null) {
            attributes.addFlashAttribute("message", "新增标签失败耶");
        } else {
            attributes.addFlashAttribute("message", "新增标签成功耶");
        }
        return "redirect:/kings/Tags";
    }

    @PostMapping("/TagsIn/{id}")
    public String editPost(@PathVariable Long id, @Valid Tag tag, BindingResult result, RedirectAttributes attributes) {
        Tag TagByName = tagService.getTagByName(tag.getName());
        if (TagByName !=null){
            result.rejectValue("name","nameError","标签名称重复哦");
        }
        if (result.hasErrors()){
            return "admin/Tags-input";
        }
        Tag t = tagService.updateTag(id,tag);
        if (t == null) {
            attributes.addFlashAttribute("message", "修改失败耶");
        } else {
            attributes.addFlashAttribute("message", "修改成功啦");
        }
        return "redirect:/kings/Tags";
    }

    @GetMapping("/Tags/{id}/delete")
    public String delete(@PathVariable Long id ,RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功耶");
        return "redirect:/kings/Tags";
    }

}
