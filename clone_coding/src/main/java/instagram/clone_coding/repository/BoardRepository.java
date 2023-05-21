package instagram.clone_coding.repository;

import instagram.clone_coding.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByMemberId(Long memberId);
}
