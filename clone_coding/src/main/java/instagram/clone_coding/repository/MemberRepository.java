package instagram.clone_coding.repository;

import instagram.clone_coding.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsUsersByEmail(String email);
    boolean existsUsersByNickname(String nickname);
    Member findByEmail(String email);
}