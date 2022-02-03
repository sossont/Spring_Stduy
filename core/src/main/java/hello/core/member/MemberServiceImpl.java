package hello.core.member;

public class MemberServiceImpl implements MemberService{

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    private final MemberRepository memberRepository;

    @Override
    public void signUp(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
