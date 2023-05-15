package instagram.clone_coding.repository;

import instagram.clone_coding.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Member, Long> {
    Member findByEmailAndPassword(String email, String password);
    Optional<Member> findByEmail(String email);
    boolean existsUsersByEmail(String email);
}