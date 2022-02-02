package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member newMember = new Member(1L, Grade.VIP, "memberA");
        memberService.signUp(newMember);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + newMember.getName());
        System.out.println("find member = " + findMember.getName());
    }
}
