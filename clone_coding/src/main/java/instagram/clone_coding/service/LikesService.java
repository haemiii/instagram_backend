package instagram.clone_coding.service;

import instagram.clone_coding.config.jwt.JwtTokenProvider;
import instagram.clone_coding.domain.Board;
import instagram.clone_coding.domain.CustomUserDetails;
import instagram.clone_coding.domain.Likes;
import instagram.clone_coding.domain.Member;
import instagram.clone_coding.repository.BoardRepository;
import instagram.clone_coding.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class LikesService {

    private final BoardRepository boardRepository;
    private final LikesRepository likesRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailService userDetailService;

    public Likes likeBoard(HttpServletRequest request, Long id) {
        String token = extractTokenFromHeader(request);
        String email = jwtTokenProvider.extractEmailFromToken(token);

        CustomUserDetails userDetails = (CustomUserDetails) userDetailService.loadUserByUsername(email);
        Member member = userDetails.getMember();

        Optional<Board> board = boardRepository.findById(id);


        Likes likes = Likes.builder()
                .board(board.get())
                .member(member)
                .build();

        likesRepository.save(likes);

        return likes;
    }

    public Long countLike(Long boardId) {

        return likesRepository.countByBoardId(boardId);
    }

    public void unlikeBoard(Board board, Member member) {
        // 해당 게시물에 대한 사용자의 좋아요 정보를 조회
        Likes likes = likesRepository.findByBoardAndMember(board, member);

        // 좋아요 정보가 존재하는 경우 삭제
        if (likes != null) {
            likesRepository.delete(likes);
        }
    }

    private String extractTokenFromHeader(HttpServletRequest request) { // 토큰 추출
        String headerValue = request.getHeader("Authorization");
        if (headerValue != null && headerValue.startsWith("Bearer ")) {
            return headerValue.substring(7);
        }
        throw new IllegalArgumentException("Invalid authorization header");
    }

}
