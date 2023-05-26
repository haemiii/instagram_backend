package instagram.clone_coding.controller;

import instagram.clone_coding.Dto.BoardUploadDto;
import instagram.clone_coding.Dto.CommentDto;
import instagram.clone_coding.domain.Board;
import instagram.clone_coding.domain.Comment;
import instagram.clone_coding.domain.CustomUserDetails;
import instagram.clone_coding.domain.Member;
import instagram.clone_coding.service.BoardService;
import instagram.clone_coding.service.CommentService;
import instagram.clone_coding.service.LikesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;
    private final LikesService likesService;
    private final CustomUserDetails userDetails;
    
    @PostMapping("/register")
    public ResponseEntity<String> registerBoard(@RequestBody BoardUploadDto boardUploadDto, HttpServletRequest request){
        Board registedBoard = boardService.registerBoard(boardUploadDto, request);
        return ResponseEntity.ok("등록 완료" + registedBoard);
    }

    @PostMapping("/{boardId}/comment")
    public ResponseEntity<String> createComment(@RequestBody CommentDto commentDto, HttpServletRequest request) {
        // 요청 본문으로부터 필요한 데이터를 CommentDto 객체로 전달받습니다.
        // CommentDto는 댓글 생성에 필요한 데이터를 담고 있는 DTO 클래스입니다.

        // CommentDto를 사용하여 Comment 객체를 생성하고, CommentService를 통해 댓글을 생성합니다.
        Comment createdComment = commentService.createComment(commentDto, request);

        // 생성된 댓글을 클라이언트에게 응답합니다.
        return ResponseEntity.ok("댓글 생성 완료: " + createdComment);
    }




//    @GetMapping("/user")
//    public ResponseEntity<String> getUserInfo() {
//        System.out.println("=======GetUserInfo========");
//        Member member = userDetails.getMember();
//        String email = member.getEmail();
//
//        return ResponseEntity.ok("Member: " + member + "User email: " + email);
//    }
}
