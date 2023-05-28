package instagram.clone_coding.repository;

import instagram.clone_coding.domain.Board;
import instagram.clone_coding.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Board findByMember(Long member);

}
