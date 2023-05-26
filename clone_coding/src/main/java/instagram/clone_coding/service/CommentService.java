package instagram.clone_coding.service;

import instagram.clone_coding.Dto.CommentDto;
import instagram.clone_coding.domain.Comment;
import instagram.clone_coding.config.jwt.JwtTokenProvider;
import instagram.clone_coding.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailService userDetailService;


    public Comment createComment(CommentDto commentDto, HttpServletRequest content) {
        Comment comment = Comment.builder()
                .content(commentDto.getContent())
                .build();

        return commentRepository.save(comment);
    }

    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }

}
