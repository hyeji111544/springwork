package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

//@SpringBootTest // C R em h2 -> 모든 레이어를 메모리에 올리고 테스트 할 떄 사용
@DataJpaTest // h2, em
@Import(BoardRepository.class) // br
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private EntityManager em;

    //테스트메서드에서는 매개변수를 사용할 수 없다
    // 메서드명_test  : 컨벤션
    // 따라가기, 코드들 조금씩 수정하면서 분석해 보기, 이론적 공부하기, 깊게 공부하기 해야 좋은 개발자가 될 수 있따.

    @Test
    public void updateByIdV2_test() {
        // given
        int id = 1;
        Board board = boardRepository.findById(id);

        // when
        board.setTitle("제목10");
        board.setContent("내용10");

        // 트랜잭션이 종료되면 flush()
        em.flush();
    }

    @Test

    public void findByIdV2_test() {
        int id = 1;

        Board board = boardRepository.findByIdV2(id);
        System.out.println(board.getUser().getUsername());
    }

    @Test
    public void updateById_test() {
        //given
        int id = 1;
        String title = "제목 1 변경";
        String content = "내용 1 변경";
        // when
        boardRepository.updateById(title, content, id);
        // then
        Board board = boardRepository.findById(id);
        Assertions.assertThat(board.getTitle()).isEqualTo("제목 1 변경");
    }

    @Test
    public void deleteById_test() {
        // given
        int id = 1;
        // when
        boardRepository.findById(id);

        // eye
        try {
            boardRepository.findById(id);
        } catch (Exception e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("게시글 id를 찾을 수 없습니다");
        }

        // then (코드)


    }

    @Test
    public void findById_test() {
        // given
        int id = 1;
        // when
        Board board = boardRepository.findById(id);
        // eye(then : 원래는 검증이 필요함 초보라서 눈으로 먼저 확인했어요)
        System.out.println(board.getId());
        System.out.println(board.getTitle());
        System.out.println(board.getContent());
    }

    @Test
    public void findAll_test() {

        System.out.println("1. 첫번째 조회");
        List<Board> boardList = boardRepository.findAll();
        System.out.println("--------------------");
        // eye

        System.out.println("userId : " + boardList.get(0).getUser().getId());

        System.out.println("2. 레이지 로딩");
        System.out.println("userName : " + boardList.get(0).getUser().getUsername());
    }


    @Test
    public void save_test() {
        // 1. given (매개변수를 강제로 만들어주는것)
        String title = "제목";
        String content = "내용 1";
        // 2. when
        boardRepository.save(Board.builder().title(title).content(content).build());
        // 3. eye(눈으로 확인)
    }
}
