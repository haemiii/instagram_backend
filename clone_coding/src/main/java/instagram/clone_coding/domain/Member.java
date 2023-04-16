package instagram.clone_coding.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @Column(name= "member_id")
    private String id;

    private String name;

    private String password;

    private String email;
}
