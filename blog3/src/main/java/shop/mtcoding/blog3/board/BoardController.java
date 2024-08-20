package shop.mtcoding.blog3.board;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    BoardRepository boardRepository;

    // 왜 RequestParam 없는데 에러가 안나지?
    @PostMapping("/board/{id}/update")
    public String update(@PathVariable("id") int id, String title, String content) {
        boardRepository.updateById(title, content, id);
        return "redirect:/board/" + id;
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        boardRepository.deleteById(id);
        return "redirect:/board";
    }

    @PostMapping("/board/save")
    public String save(String title, String content) {
        boardRepository.save(title, content);
        return "redirect:/board";
    }

    @GetMapping("/board")
    public String list(HttpServletRequest request) {
        List<Board> boards = boardRepository.findAll();
        request.setAttribute("models", boards);
        return "board/list";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable("id") Integer id, HttpServletRequest request) {
        Board board = boardRepository.findById(id);
        request.setAttribute("model", board);
        return "board/detail";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable("id") int id, HttpServletRequest request) {
        Board board = boardRepository.findById(id);
        request.setAttribute("model", board);
        return "board/update-form";
    }
}
