package instagram.clone_coding.domain;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    private String content;

    private int heart;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private List<Board> boards;

    // 멤버와의 관계는 1 : n인가..?


}
