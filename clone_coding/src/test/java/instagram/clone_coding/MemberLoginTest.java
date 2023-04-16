package instagram.clone_coding;

import instagram.clone_coding.domain.Member;
import instagram.clone_coding.repository.MemberRepository;
import instagram.clone_coding.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class MemberLoginTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;

    @Test
    public void 로그인() throws Exception {

        Member m1 = new Member();

        m1.setName("kang");
        m1.setId("hyemi");
        m1.setPassword("1234");

        String saveId = memberService.join(m1);

        Member loginMember = memberService.Login("hyemi", "1234");
//        Member failedMember = memberService.Login("hyemi", "3333");
//        Member failedMember2 = memberService.Login("haemi", "1234");

    }
}
