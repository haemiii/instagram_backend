package instagram.clone_coding.service;

import instagram.clone_coding.domain.Member;
import instagram.clone_coding.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.lang.model.type.NullType;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    //생성자 주입 -> @RequiredArgsConstructor를 이용하여 생략 가능
    private final MemberRepository memberRepository;

    //회원 가입
    @Transactional
    public String join(Member member) {
        duplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    public Member Login(String id, String password) {
        validateMember(id, password);
        return memberRepository.findOne(id);
    }

    private void validateMember(String id, String password) {
        //비밀번호, 이름 비교
        Member user = memberRepository.findOne(id);
        if(user == null){
            throw new IllegalStateException("존재하지 않는 회원입니다!");
        }
        String pw = user.getPassword();
        if(pw != password){
            throw new IllegalStateException("틀린 비밀번호입니다!");
        }
    }

    private void duplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다!");
        }
    }

    public Member findOne(String memberId) {
        return memberRepository.findOne(memberId);
    }

}
