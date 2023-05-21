package instagram.clone_coding.controller;

import instagram.clone_coding.Dto.BoardUploadDto;
import instagram.clone_coding.domain.Board;
import instagram.clone_coding.domain.CustomUserDetails;
import instagram.clone_coding.domain.Member;
import instagram.clone_coding.service.BoardService;
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
    private final CustomUserDetails userDetails;
    
    @PostMapping("/register")
    public ResponseEntity<String> registerBoard(@RequestBody BoardUploadDto boardUploadDto, HttpServletRequest request){
        Board registedBoard = boardService.registerBoard(boardUploadDto, request);
        return ResponseEntity.ok("등록 완료" + registedBoard);
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
