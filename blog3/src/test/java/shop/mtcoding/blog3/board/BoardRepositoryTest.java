package shop.mtcoding.blog3.board;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({BoardRepository.class})
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void updateById_Test() {
        int id = 1;
        String title = "제목1 변경";
        String content = "내용1 변경";

        boardRepository.updateById(title,content,id);

        Board board = boardRepository.findById(id);
        Assertions.assertThat(board.getTitle()).isEqualTo("제목1 변경");
    }

    @Test
    public void deleteById_test(){
        int id = 1;
        Board board = boardRepository.findById(id);

        Assertions.assertThat(board.getTitle()).isEqualTo("제목1");
        boardRepository.deleteById(id);

    }

    @Test
    public void findById_test(){
        int id = 1;
        Board board = boardRepository.findById(id);
        System.out.println(board.getId());
        System.out.println(board.getTitle());
        System.out.println(board.getContent());

    }

    @Test
    void findAll_test(){
        List<Board> boardList = boardRepository.findAll();

        System.out.println(boardList.size());
        for(Board board : boardList){
            System.out.println("타이틀 : " + board.getTitle() + " | 내용 : " + board.getContent());
        }
    }

    @Test
    void save_test() {
        String title = "제목1";
        String content = "내용1";

        boardRepository.save(title, content);

    }
}