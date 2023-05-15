package instagram.clone_coding.controller;

import instagram.clone_coding.Dto.UserJoinDto;
import instagram.clone_coding.Dto.UserLoginDto;
import instagram.clone_coding.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<String> join(@Valid @RequestBody UserJoinDto userJoinDto) {
        memberService.join(userJoinDto);
        return ResponseEntity.ok("가입 성공");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginSuccess(@RequestBody UserLoginDto userLoginDto){
        String token = memberService.login(userLoginDto);
        return ResponseEntity.ok(token);
    }

}
