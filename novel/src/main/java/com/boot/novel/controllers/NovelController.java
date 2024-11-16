package com.boot.novel.controllers;

import com.boot.novel.models.Novel;
import com.boot.novel.services.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/novels")
public class NovelController {

    @Autowired
    private NovelService novelService;

    // Hiển thị danh sách các tiểu thuyết
    @GetMapping
    public String getAllNovels(Model model) {
        List<Novel> novels = novelService.getAllNovels();
        model.addAttribute("novels", novels);
        return "novels/list"; // Trả về trang danh sách
    }

    // Hiển thị chi tiết tiểu thuyết
    @GetMapping("/{id}")
    public String getNovelById(@PathVariable Long id, Model model) {
        Novel novel = novelService.getNovelById(id);
        model.addAttribute("novel", novel);
        return "novels/detail"; // Trả về trang chi tiết
    }

    // Hiển thị form tạo mới tiểu thuyết
    @GetMapping("/new")
    public String createNovelForm(Model model) {
        model.addAttribute("novel", new Novel()); // Thêm một đối tượng mới vào form
        return "novels/form"; // Trả về trang form tạo tiểu thuyết
    }

    // Xử lý form tạo mới tiểu thuyết
//    @PostMapping
//    public String saveNovel(@Valid @ModelAttribute Novel novel, BindingResult result) {
//        if (result.hasErrors()) {
//            return "novels/form"; // Nếu có lỗi, quay lại trang form
//        }
//        novelService.saveNovel(novel); // Lưu tiểu thuyết vào cơ sở dữ liệu
//        return "redirect:/novels"; // Điều hướng về trang danh sách sau khi lưu
//    }

    // Xóa tiểu thuyết
    @GetMapping("/delete/{id}")
    public String deleteNovel(@PathVariable Long id) {
        novelService.deleteNovel(id); // Xóa tiểu thuyết theo id
        return "redirect:/novels"; // Điều hướng về trang danh sách sau khi xóa
    }
}
