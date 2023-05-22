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
public class Board {

    @Id @GeneratedValue
    @Column(name="board_id")
    private Long id;

    @Lob
    private byte[] image;

    private String text;

    @ManyToOne
    @JoinColumn(name= "member_id")
    private Member member;

    private int heart;

    @OneToMany(mappedBy = "comment_id")
    private Comment comment;
}
