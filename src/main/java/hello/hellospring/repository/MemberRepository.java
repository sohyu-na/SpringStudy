package hello.hellospring.repository;
import hello.hellospring.domain.Member;

import java.util.*;

public interface MemberRepository { //기능들
    Member save(Member member); //저장
    Optional<Member> findById(Long id); //검색
    Optional<Member> findByName(String name);
    List<Member> findAll(); //모든 정보를 리스트로 반환

}
