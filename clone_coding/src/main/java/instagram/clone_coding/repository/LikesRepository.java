package instagram.clone_coding.repository;

import instagram.clone_coding.domain.Board;
import instagram.clone_coding.domain.Likes;
import instagram.clone_coding.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    List<Likes> findByMember(Long member);
    List<Likes> findByBoardId(Long boardId);

    Likes findByBoardAndMember(Board board, Member member);

    Long countByBoardId(Long BoardId);


}
