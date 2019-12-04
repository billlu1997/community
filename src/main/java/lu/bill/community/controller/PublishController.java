package lu.bill.community.controller;

import lu.bill.community.dto.QuestionDTO;
import lu.bill.community.mapper.QuestionMapper;
import lu.bill.community.model.Question;
import lu.bill.community.model.User;
import lu.bill.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
//    @Autowired
//    private QuestionMapper questionMapper;

    @Autowired
    private QuestionService questionService;

//    @Autowired
//    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Long id,
                       Model model,
                       HttpServletRequest request) {
        QuestionDTO question = questionService.getById(id);
        if(request.getSession().getAttribute("user") == null){
            // not logged in
            return "redirect:/";
        }
        User currentUser = (User) request.getSession().getAttribute("user");
        if(currentUser.getId() != question.getCreator())
            return "redirect:/";
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("id", question.getId());
        return "publish";
    }



    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "tag", required = false) String tag,
            @RequestParam(value = "id", required = false) Long id,
            HttpServletRequest request,
            Model model
    ) {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        if(title == null || title.equals("")) {
            model.addAttribute("error", "invalid title");
            return "publish";
        }if(description == null || description.equals("")) {
            model.addAttribute("error", "invalid description");
            return "publish";
        }if(tag == null || tag.equals("")) {
            model.addAttribute("error", "invalid tag");
            return "publish";
        }
        User user = (User)request.getSession().getAttribute("user");


        if(user == null) {
            model.addAttribute("error", "no login");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        question.setId(id);
        questionService.createOrUpdate(question);

        return "redirect:/";
    }

}
