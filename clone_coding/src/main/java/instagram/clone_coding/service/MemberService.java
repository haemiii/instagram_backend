package instagram.clone_coding.service;

import instagram.clone_coding.Dto.UserJoinDto;
import instagram.clone_coding.Dto.UserLoginDto;
import instagram.clone_coding.config.jwt.JwtTokenProvider;
import instagram.clone_coding.domain.Member;
import instagram.clone_coding.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static instagram.clone_coding.domain.Role.USER;


@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    //생성자 주입 -> @RequiredArgsConstructor를 이용하여 생략 가능
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder encoder;


    //회원 가입
    @Transactional
    public void join(UserJoinDto userJoinDto) {
        duplicateUser(userJoinDto);

        Member joinMember = Member.builder()
                .email(userJoinDto.getEmail())
                .nickname(userJoinDto.getNickname())
                .name(userJoinDto.getName())
                .password(encoder.encode(userJoinDto.getPassword()))
                .role(USER)
                .build();

        memberRepository.save(joinMember);
    }

    private void duplicateUser(UserJoinDto userJoinDto) {
        if (memberRepository.existsUsersByEmail(userJoinDto.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 유저입니다.");
        }
        if (memberRepository.existsUsersByNickname(userJoinDto.getNickname())) {
            throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
        }
    }

    // token login
    @Transactional
    public String login(UserLoginDto userLoginDto) {
        // Authentication 객체 생성 : 사용자가 입력한 이메일과 비밀번호를 갖는 토큰 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword());
        //토큰 인증

        try {
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            String token = jwtTokenProvider.generateToken(authentication);
            System.out.println("authentication" + authentication);
            return token;
        } catch (AuthenticationException e) {
            System.out.println("Exception: " + e.getMessage());
            return "Error occur....";

        }
    }
}
