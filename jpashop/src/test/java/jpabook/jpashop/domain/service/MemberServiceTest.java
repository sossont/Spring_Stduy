package jpabook.jpashop.domain.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Rollback(false)
    public void signUp() throws Exception {
        Member member = new Member();
        member.setName("MemberA");

        Long joinId = memberService.join(member);

        assertEquals(member, memberRepository.findOne(joinId));
    }

    @Test(expected = IllegalStateException.class)
    public void duplicate() throws Exception {
        Member memberA = new Member();
        memberA.setName("MemberA");

        Member memberB = new Member();
        memberB.setName("MemberA");

        Long joinId = memberService.join(memberA);
        Long joinId2 = memberService.join(memberB);

        fail("예외가 발생해야 한다.");
    }
}