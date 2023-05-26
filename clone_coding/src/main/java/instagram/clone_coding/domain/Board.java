package instagram.clone_coding.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "board", cascade = CascadeType.PERSIST)
    private List<Comment> comments;

    @OneToMany(mappedBy = "board", cascade = CascadeType.PERSIST)
    private List<Likes> likes;
}
