package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 실제로는 ConcurrentHashMap 이나 AtomicLong 사용 고려.
public class MemberRepository {

    private static final MemberRepository instance = new MemberRepository();
    private static long sequence = 1L;
    private Map<Long, Member> store = new HashMap<>();

    private MemberRepository() {

    }

    public static MemberRepository getInstance() {
        return instance;
    }

    public Member save(Member member) {
        member.setId(sequence++);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
