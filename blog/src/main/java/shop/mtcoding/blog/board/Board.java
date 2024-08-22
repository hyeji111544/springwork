package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.blog.user.User;

import java.sql.Timestamp;

@NoArgsConstructor // 빈생성자
@Getter // private 설정이기 때문에 게터, 세터가 필요함
@Setter
@Table(name = "board_tb")
@Entity // DB에서 조회하면 자동 매핑이 됨
public class Board {
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto_increment 열의 증가를 자동으로 시켜줌
    @Id // PK (DB 테이블의 기준이 되는 키를 설정하는것)
    private Integer id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    // 생성자
    @Builder
    public Board(Integer id, String title, String content, Timestamp createdAt, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.user = user;
    }
}
