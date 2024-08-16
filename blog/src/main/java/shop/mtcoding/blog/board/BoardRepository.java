package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository // @Repository 를 붙이면 스프링이 new 해서 IoC(컬렉션 List 자료형 같은거) 에 저장한다
public class BoardRepository {

    @Autowired // IoC에 있는 객체를 찾아온다.
    private EntityManager em;

    public BoardRepository() {
        System.out.println("BoardRepository 생성자");
    }

    //1. insert 하기
    @Transactional
    public void save(String title, String content) {
        Query query = em.createNativeQuery("insert into board_tb (title, content, created_at) values (?, ?,now())");
        query.setParameter(1, title); // position : ? 의 순서
        query.setParameter(2, content); // 이 쿼리를
        query.executeUpdate();
    }
}
