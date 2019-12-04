package lu.bill.community.controller;


import lu.bill.community.dto.QuestionDTO;
import lu.bill.community.mapper.QuestionMapper;
import lu.bill.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") String id,
                           Model model) {
        Long questionId = null;
        try {
            questionId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return "/";
        }
        QuestionDTO questionDTO = questionService.getById(questionId);

        model.addAttribute("question", questionDTO);
        return "question";
    }
}
