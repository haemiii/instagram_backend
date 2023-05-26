package instagram.clone_coding.service;

import instagram.clone_coding.config.jwt.JwtTokenProvider;
import instagram.clone_coding.domain.Board;
import instagram.clone_coding.domain.Likes;
import instagram.clone_coding.domain.Member;
import instagram.clone_coding.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LikesService {

    private final LikesRepository likesRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailService userDetailService;

    public void likeBoard(Board board, Member member) {

        Likes likes = Likes.builder()
                .board(board)
                .member(member)
                .build();

        likesRepository.save(likes);
    }

    public void unlikeBoard(Board board, Member member) {
        // 해당 게시물에 대한 사용자의 좋아요 정보를 조회
        Likes likes = likesRepository.findByBoardAndMember(board, member);

        // 좋아요 정보가 존재하는 경우 삭제
        if (likes != null) {
            likesRepository.delete(likes);
        }
    }

}
