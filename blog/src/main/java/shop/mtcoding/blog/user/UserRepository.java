package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepository {

    private final EntityManager em;

    public User findByUsernameAndPassword(String username, String password) {
        Query query = em.createQuery("select u from User u where u.username = :username and u.password = :password", User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        User user = (User) query.getSingleResult();
        return user;
    }

    //레포지토리에는 기능 명 넣지x, 하이버네이트로 넣을거임
    @Transactional
    public void save(User user) {
        System.out.println("전 : " + user.getUsername());
        em.persist(user);
        System.out.println("후 : " + user.getUsername());
    }
}
