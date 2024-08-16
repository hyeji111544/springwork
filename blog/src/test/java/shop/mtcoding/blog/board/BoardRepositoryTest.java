package shop.mtcoding.blog.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

//@SpringBootTest // C R em h2 -> 모든 레이어를 메모리에 올리고 테스트 할 떄 사용
@DataJpaTest // h2, em
@Import(BoardRepository.class) // br
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    //테스트메서드에서는 매개변수를 사용할 수 없다
    // 메서드명_test  : 컨벤션
    // 따라가기, 코드들 조금씩 수정하면서 분석해 보기, 이론적 공부하기, 깊게 공부하기 해야 좋은 개발자가 될 수 있따.
    //
    @Test
    public void save_test() {
        // 1. given (매개변수를 강제로 만들어주는것)
        String title = "제목";
        String content = "내용 1";
        // 2. when
        boardRepository.save(title, content);
        // 3. eye(눈으로 확인)

    }
}
