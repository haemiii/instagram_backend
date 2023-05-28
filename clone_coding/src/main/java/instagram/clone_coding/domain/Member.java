package instagram.clone_coding.domain;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Member {
    //userdetails : 인증에 사용되는 인터페이스
    // 인증에 필요한 사용자 정보를 제공하는 메소드들을 정의!

    @Id @GeneratedValue
    @Column(name= "member_id")
    private Long id;

    private String name;

    @Column(unique = true)
    private String nickname;

    private String password;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "member", cascade = CascadeType.PERSIST)
    private List<Board> boards;

    @OneToMany(mappedBy = "member", cascade = CascadeType.PERSIST)
    private List<Comment> comments;

    @OneToMany(mappedBy = "member", cascade = CascadeType.PERSIST)
    private List<Likes> likes;


}
