package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository // @Repository 를 붙이면 스프링이 new 해서 IoC(컬렉션 List 자료형 같은거) 에 저장한다
public class BoardRepository {

    private final EntityManager em;

    @Transactional
    public void updateById(String title, String content, int id) {
        Query query = em.createNativeQuery("update board_tb set title =?, content =? where id =?");
        query.setParameter(1, title);
        query.setParameter(2, content);
        query.setParameter(3, id);
        query.executeUpdate();
    }

    @Transactional
    public void deleteById(int id) {
        Query query = em.createNativeQuery("delete from board_tb where id=?");
        query.setParameter(1, id);
        query.executeUpdate();
    }

    public Board findById(int id) {
        //Query query = em.createNativeQuery("select bt.id as bt_id, ut.id as ut_id, * from board_tb bt inner join user_tb ut on bt.user_id = ut.id where bt.id=?", Board.class);
        Query query = em.createQuery("select b from Board b join fetch b.user u where b.id = :id", Board.class); // 객체지향 쿼리
        query.setParameter("id", id);
        try {
            Board board = (Board) query.getSingleResult(); // 다운캐스팅 필요
            return board;
        } catch (Exception e) {
            // 익세션을 내가 잡은것 까지 배움 - 처리 방법은 v2 에서 배우기
            throw new RuntimeException("게시글 id를 찾을 수 없습니다");
            //e.printStackTrace();
        }
    }

    public Board findByIdV2(int id) {
        Query query = em.createNativeQuery("select bt.id, bt.title, bt.content, bt.user_id, bt.created_at, ut.id u_id, ut.username, ut.password, ut.email, ut.created_at u_created_at from board_tb bt inner join user_tb ut on bt.user_id = ut.id where bt.id = ?", Board.class);
        query.setParameter(1, id);
        try {
            Board board = (Board) query.getSingleResult();
            return board;
        } catch (Exception e) {
            e.printStackTrace();
            // 익세션을 내가 잡은것 까지 배움 - 처리 방법은 v2에서 배우기
            throw new RuntimeException("게시글 id를 찾을 수 없습니다");
        }
    }

    public List<Board> findAll() {
        Query query = em.createNativeQuery("select * from board_tb order by id desc", Board.class); // 보드객체로 매핑해서 받아옴
        List<Board> boardList = query.getResultList();
        return boardList;
    }
    

    //1. insert 하기
    @Transactional
    public void save(Board board) {
        em.persist(board);
    }
}
