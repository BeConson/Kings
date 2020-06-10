package com.coco.kings.web.admin;

import com.coco.kings.Service.TypeService;
import com.coco.kings.bean.Type;
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
import java.sql.Types;

/**
 * @author 康森
 * @date 2020/4/1 19 : 49 : 37
 * @description 分类页请求
 */
@Controller
@RequestMapping("/kings")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/Types")
    public String list(@PageableDefault(size = 5,sort = {"id"},
            direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("page",typeService.listType(pageable));
        return "admin/Types";
    }

    @GetMapping("/Types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
            return "admin/Types-input";
    }

    @GetMapping("Types/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("type",typeService.getType(id));
        return "admin/Types-input";
    }

    @PostMapping("/TypesIn")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes) {
        Type typeByName = typeService.getTypeByName(type.getName());
        if (typeByName !=null){
            result.rejectValue("name","nameError","分类名称重复哦");
        }
        if (result.hasErrors()){
            return "admin/Types-input";
        }
        Type t = typeService.saveType(type);
        if (t == null) {
            attributes.addFlashAttribute("message", "新增分类失败耶");
        } else {
            attributes.addFlashAttribute("message", "新增分类成功耶");
        }
        return "redirect:/kings/Types";
    }

    @PostMapping("/TypesIn/{id}")
    public String editPost(@PathVariable Long id, @Valid Type type, BindingResult result, RedirectAttributes attributes) {
        Type typeByName = typeService.getTypeByName(type.getName());
        if (typeByName !=null){
            result.rejectValue("name","nameError","分类名称重复哦");
        }
        if (result.hasErrors()){
            return "admin/Types-input";
        }
        Type t = typeService.updateType(id,type);
        if (t == null) {
            attributes.addFlashAttribute("message", "修改失败耶");
        } else {
            attributes.addFlashAttribute("message", "修改成功啦");
        }
        return "redirect:/kings/Types";
    }

    @GetMapping("/Types/{id}/delete")
    public String delete(@PathVariable Long id ,RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功耶");
        return "redirect:/kings/Types";
    }

}
