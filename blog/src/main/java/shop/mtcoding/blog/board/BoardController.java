package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.mtcoding.blog.core.error.ex.Exception401;
import shop.mtcoding.blog.user.User;

import java.util.List;

// SRP : 식별자 요청받기 , 응답하기
@RequiredArgsConstructor
@Controller // 식별자 요청 받을 수 있다.
public class BoardController {

    private final HttpSession session; //싱글톤 객체라 IoC 에 넣기 가능
    private final BoardService boardService;

    // url : http://localhost:8080/board/1/update
    // body : title=제목 1 변경 & content = 내용 1 변경
    // content-type : x-www-form-urlencoded
    @PostMapping("/board/{id}/update")
    public String update(@PathVariable("id") int id, @RequestParam("title") String title, @RequestParam("content") String content) {
        // 앱이 os 로 데이터 내릴때 바이트 스트림으로 내리고 그럼 패킷으로 바뀜, 버퍼를 사용해서 던지면 데이터 한번에 던지면 됨. 흘러 나가서 공간 생길 떄 까지 안기다려도 o
        //브라우저라는 앱에서 던질 떄 (글스기 완료 ㅂ저튼 부를떄)
        //boardRepository.updateById(title, content, id);
        return "redirect:/board/" + id;
    }

    @PostMapping("/board/{id}/delete")
    public String deltete(@PathVariable("id") int id) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser == null) {
            throw new Exception401("로그인 되지 않은 사용자입니다.");
        }

        boardService.게시글삭제(id, sessionUser);
        return "redirect:/board";
    }

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO saveDTO) { //스프링 기본 전략 = x-www-form-urlencoded 작성 /보드 레포지토리 객체는 ioc 에 있음 따라서 autowired 함
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser == null) {
            throw new Exception401("로그인 되지 않은 사용자입니다.");
        }

        boardService.게시글쓰기(saveDTO, sessionUser);
        return "redirect:/board";
    }

    //get, post, put, delete 를 통해 접근 가능
    @GetMapping("/board")
    public String list(HttpServletRequest request) {
        List<Board> boardList = boardService.게시글목록보기();
        request.setAttribute("models", boardList);

        return "board/list";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable("id") Integer id, HttpServletRequest request) {
        //    Board board = boardRepository.findById(id);
        //    request.setAttribute("model", board);
        //    request.setAttribute("isOwner", false);
        User sessionUser = (User) session.getAttribute("sessionUser");

        BoardResponse.DetailDTO detailDTO = boardService.상세보기(id, sessionUser);

        request.setAttribute("model", detailDTO);
        return "board/detail";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable("id") int id, HttpServletRequest request) {
        //Board board = boardRepository.findById(id);
        //request.setAttribute("model", board);
        return "board/update-form";
    }

    @GetMapping("/test/board/1")
    public void testBoard() {
        //List<Board> boardList = boardRepository.findAll();
        System.out.println("---------------------------------------------");
        //System.out.println(boardList.get(2).getUser().getPassword());
        System.out.println("---------------------------------------------");
    }

}
