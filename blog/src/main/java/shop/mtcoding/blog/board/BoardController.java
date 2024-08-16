package shop.mtcoding.blog.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// SRP : 식별자 요청받기 , 응답하기
@Controller // 식별자 요청 받을 수 있다.
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @PostMapping("/board/save")
    public String save(String title, String content) { //스프링 기본 전략 = x-www-form-urlencoded 작성 /보드 레포지토리 객체는 ioc 에 있음 따라서 autowired 함
        boardRepository.save(title, content);
        return "redirect:/board";
    }

    //get, post, put, delete 를 통해 접근 가능
    @GetMapping("/board")
    public String list() {
        return "board/list";
    }

    @GetMapping("/board/1")
    public String detail() {
        return "board/detail";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }

    @GetMapping("/board/1/update-form")
    public String updateForm() {
        return "board/update-form";
    }

}
