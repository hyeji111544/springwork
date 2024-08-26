package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog.core.Hello;
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
    @PostMapping("/api/board/{id}/update")
    public String update(@PathVariable("id") int id, @Valid BoardRequest.UpdateDTO updateDTO, Errors errors) {
        // 앱이 os 로 데이터 내릴때 바이트 스트림으로 내리고 그럼 패킷으로 바뀜, 버퍼를 사용해서 던지면 데이터 한번에 던지면 됨. 흘러 나가서 공간 생길 떄 까지 안기다려도 o
        //브라우저라는 앱에서 던질 떄 (글스기 완료 ㅂ저튼 부를떄)
        User sessionUser = (User) session.getAttribute("sessionUser");

        boardService.게시글수정(id, updateDTO, sessionUser);

        return "redirect:/board/" + id;
    }

    @PostMapping("/api/board/{id}/delete")
    public String deltete(@PathVariable("id") int id) {
        User sessionUser = (User) session.getAttribute("sessionUser");


        boardService.게시글삭제(id, sessionUser);
        return "redirect:/";
    }

    @PostMapping("/api/board/save")
    // saveDTO 안에 타이틀 컨텐츠잇음. 이앞에 어노테이션 밸리드 있다. 이게 실행댈때 밸리드가 있어서
    // 만약 에러나면 errors 에 00 하게됨
    public String save(@Valid BoardRequest.SaveDTO saveDTO, Errors errors) { //스프링 기본 전략 = x-www-form-urlencoded 작성 /보드 레포지토리 객체는 ioc 에 있음 따라서 autowired 함
        User sessionUser = (User) session.getAttribute("sessionUser");


        boardService.게시글쓰기(saveDTO, sessionUser);
        return "redirect:/";
    }

    //get, post, put, delete 를 통해 접근 가능
    @Hello
    @GetMapping("/")
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

    @GetMapping("/api/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }

    @GetMapping("/api/board/{id}/update-form")
    public String updateForm(@PathVariable("id") int id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        Board board = boardService.게시글수정화면(id, sessionUser);
        request.setAttribute("model", board);
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
