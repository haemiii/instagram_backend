package instagram.clone_coding.repository;

import instagram.clone_coding.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByMember(Long member);
    List<Comment> findByBoardId(Long boardId);
}
