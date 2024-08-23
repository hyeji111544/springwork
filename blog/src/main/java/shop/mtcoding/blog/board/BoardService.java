package shop.mtcoding.blog.board;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog.core.error.ex.Exception403;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public List<Board> 게시글목록보기() {
        List<Board> boardList = boardRepository.findAll();
        return boardList;
    }

    @Transactional
    public void 게시글삭제(int id, User userSession) {
        // 1. 컨트롤러로 부터 게시글 id 받기

        // 2. 게시글 존재 여부 확인 (404)
        Board board = boardRepository.findById(id);
        // 3. 내가 쓴 글인지 확인 (권한없음 403)

        if (board.getUser().getId() != userSession.getId()) {
            throw new Exception403("본인이 작성한 게시글이 아닙니다");
        }
        // 4. 게시글 삭제
        boardRepository.deleteById(id);
    }

    @Transactional
    public void 게시글쓰기(BoardRequest.SaveDTO saveDTO, User sessionUser) {
        boardRepository.save(saveDTO.toEntity(sessionUser));
    }

    public BoardResponse.DetailDTO 상세보기(int id, User sessionUser) {

        Board board = boardRepository.findById(id); // 조인 (Board - User)

        return new BoardResponse.DetailDTO(board, sessionUser);
    }
}
