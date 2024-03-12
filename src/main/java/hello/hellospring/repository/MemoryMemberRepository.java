package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>(); //아이디:멤버 저장
    private static long sequence =0L; //key 값 생성해줌
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //optional - null값이여도 감싸서 반환해줌
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() //루프를 다 돌면서 찾아서 반환
                 .filter(member -> member.getName().equals(name))
                 .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());//리스트로 반환
    }
    public void clearStore(){
        store.clear();
    }
}
