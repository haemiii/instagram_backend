package instagram.clone_coding.controller;

import instagram.clone_coding.Dto.UserJoinDto;
import instagram.clone_coding.Dto.UserLoginDto;
import instagram.clone_coding.domain.CustomUserDetails;
import instagram.clone_coding.domain.Member;
import instagram.clone_coding.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
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
    public ResponseEntity<String> loginSuccess(@RequestBody UserLoginDto userLoginDto) {
        String token = memberService.login(userLoginDto);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/userinfo")
    public String getUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
        System.out.println("userDetails = " + userDetails);
        String username = userDetails.getUsername();
        return username;
    }

}
