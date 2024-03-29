package lu.bill.community.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lu.bill.community.dto.PaginationDTO;
import lu.bill.community.dto.QuestionDTO;
import lu.bill.community.mapper.QuestionMapper;
import lu.bill.community.mapper.UserMapper;
import lu.bill.community.model.Question;
import lu.bill.community.model.User;
import lu.bill.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

//    @Autowired
//    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {

//        Cookie[] cookies = request.getCookies();
//
//        if(cookies != null) {
//            for(Cookie cookie: cookies) {
//                if(cookie.getName().equals("token")){
//                    String token = cookie.getValue();
//                    User user = userMapper.findByToken(token);
//                    if(user != null){
//                        request.getSession().setAttribute("user", user);
//                       break;
//                    }
//                }
//            }
//        }


        PaginationDTO pagination = questionService.list(page, size);
        model.addAttribute("pagination", pagination);

        return "index";
    }
}
