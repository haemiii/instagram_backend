package instagram.clone_coding.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
// @Table(uniqueConstraints = @UniqueConstraint(columnNames = {"board_id", "member_id"}))
public class Likes {
    @Id
    @GeneratedValue
    @Column(name = "like_id")
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

}
