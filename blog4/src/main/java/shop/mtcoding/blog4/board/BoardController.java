package shop.mtcoding.blog4.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping("/board")
    public String list(){
        return "board/list";
    }

    @GetMapping("/board/1")
    public String detail(){
        return "board/detail";
    }

    @GetMapping("/board/save-form")
    public String saveForm(){
        return "board/save-form";
    }

    @GetMapping("/board/1/update-form")
    public String updateForm(){
        return "board/update-form";
    }

}
