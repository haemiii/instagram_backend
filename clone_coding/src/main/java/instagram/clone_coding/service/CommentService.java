package instagram.clone_coding.service;

import instagram.clone_coding.Dto.CommentDto;
import instagram.clone_coding.domain.Board;
import instagram.clone_coding.domain.Comment;
import instagram.clone_coding.config.jwt.JwtTokenProvider;
import instagram.clone_coding.domain.CustomUserDetails;
import instagram.clone_coding.domain.Member;
import instagram.clone_coding.repository.BoardRepository;
import instagram.clone_coding.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailService userDetailService;


    public Comment createComment(CommentDto commentDto, HttpServletRequest request, Long id) {
        String token = extractTokenFromHeader(request);
        String email = jwtTokenProvider.extractEmailFromToken(token);

        CustomUserDetails userDetails = (CustomUserDetails) userDetailService.loadUserByUsername(email);
        Member member = userDetails.getMember();


        Optional<Board> board = boardRepository.findById(id);

        Comment comment = Comment.builder()
                .content(commentDto.getContent())
                .board(board.get())
                .member(member)
                .build();

        commentRepository.save(comment);
        return comment;
    }

    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }

    private String extractTokenFromHeader(HttpServletRequest request) { // 토큰 추출
        String headerValue = request.getHeader("Authorization");
        if (headerValue != null && headerValue.startsWith("Bearer ")) {
            return headerValue.substring(7);
        }
        throw new IllegalArgumentException("Invalid authorization header");
    }

}
