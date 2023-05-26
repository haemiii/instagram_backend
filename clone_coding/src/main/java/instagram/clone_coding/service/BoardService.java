package instagram.clone_coding.service;

import instagram.clone_coding.Dto.BoardUploadDto;
import instagram.clone_coding.config.jwt.JwtTokenProvider;
import instagram.clone_coding.domain.Board;
import instagram.clone_coding.domain.CustomUserDetails;
import instagram.clone_coding.domain.Member;
import instagram.clone_coding.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailService userDetailService;

    // 게시물 저장
    public Board registerBoard(BoardUploadDto boardUploadDto, HttpServletRequest request){
        String token = extractTokenFromHeader(request);
        System.out.println("token = " + token);
        String email = jwtTokenProvider.extractEmailFromToken(token);
        System.out.println("email = " + email);

        CustomUserDetails userDetails = (CustomUserDetails) userDetailService.loadUserByUsername(email);
        Member member = userDetails.getMember();
        System.out.println("member = " + member);

        Board registered = Board.builder()
                .image(boardUploadDto.getImage())
                .text(boardUploadDto.getText())
                .member(member)
                .build();

        boardRepository.save(registered);
        return registered;
    }

    private String extractTokenFromHeader(HttpServletRequest request) { // 토큰 추출
        String headerValue = request.getHeader("Authorization");
        if (headerValue != null && headerValue.startsWith("Bearer ")) {
            return headerValue.substring(7);
        }
        throw new IllegalArgumentException("Invalid authorization header");
    }




    // 전체 게시물 반환
}
