package instagram.clone_coding.service;

import instagram.clone_coding.domain.CustomUserDetails;
import instagram.clone_coding.domain.Member;
import instagram.clone_coding.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member findMember = memberRepository.findByEmail(email);

        if (findMember == null) {
            throw new RuntimeException();
        }
        return new CustomUserDetails(findMember);
    }
}
