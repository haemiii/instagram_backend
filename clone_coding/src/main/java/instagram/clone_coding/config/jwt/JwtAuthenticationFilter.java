package instagram.clone_coding.config.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //헤더에서 authorization 정보를 추출
        String token = resolveToken((HttpServletRequest) request);

        // 토큰 유효성 검사
        if (token!=null && jwtTokenProvider.validateToken(token)) {

            //추출한 토큰을 이용해 인증정보(Authentication)생성
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            // 인증 정보 SecurityContextHolder 에 저장
            //-> spring security 에서 인증된 사용자에 대한 정보 제공를 제공할때 사용!
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response); //다음 필터로 req, res 객체 전달
    }

    // 헤더에서 토큰 추출
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}