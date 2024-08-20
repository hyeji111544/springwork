package shop.mtcoding.blog3.board;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@NoArgsConstructor
@Setter
@Getter
@Table(name = "board_tb")
@Entity
public class Board {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    private Timestamp createAt;

    public Board(Integer id, String title, String content, Timestamp createAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createAt = createAt;
    }
}
