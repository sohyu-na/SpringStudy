package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
//@Service //이 어노테이션 없으면 순수한 자바 클래스에 불과함, 이걸 추가해야 스프링이 스프링 컨테이너에 등록함
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    //@Autowired //생성자 호출시 MemberRepository를 컨테이너에 넣어줌, 현재 구현체인 MemoryMemberRepository를 서비스에 주입해줌
    public MemberService(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }

    //회원가입
    public Long join(Member member){
        //중복체크 *같은 이름 등록 불가
        // if(memberRepository.findByName(member.getName())!=null) findByName으로 Optional로 나오니까 null로 비교불가
        /*클린코드로 바꾸면
        Optional<Member> result = memberRepository.findByName(member.getName()); //Optional로 받으면 null도 감싸짐
        result.ifPresent( m -> { //m이 member, 존재하는지만 확인하고 실행문에 사용되지않으므로 걍 m이라 써서 존재확인만 함
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });*/
        validateDuplicateMember(member);//함수 추출
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m->{
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    //전체 회원 조회
    public List<Member> findMembers(){
       return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
