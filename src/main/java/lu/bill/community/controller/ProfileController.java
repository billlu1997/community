package lu.bill.community.controller;


import lu.bill.community.dto.PaginationDTO;
import lu.bill.community.mapper.UserMapper;
import lu.bill.community.model.User;
import lu.bill.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size) {

        User user = (User)request.getSession().getAttribute("user");
        if(user == null) {
            return "redirect:/";
        }

        if(action.equals("questions")) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "My Questions");
        } else if(action.equals("replies")) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "Replies");
        }

        PaginationDTO paginationDTO = questionService.list(user.getId(), page, size);
        model.addAttribute("pagination", paginationDTO);
        return "profile";
    }
}
