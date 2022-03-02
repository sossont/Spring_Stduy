package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction ts = em.getTransaction();
        ts.begin();

        try {
            Member member = new Member();
            member.setId(2L);
            member.setUsername("Test");
            member.setAge(20);
            member.setRoleType(RoleType.USER);
            member.setTestLocalDate(LocalDate.now());
            member.setTestLocalDateTime(LocalDateTime.now());
            System.out.println(LocalDate.now());
            em.persist(member);
            ts.commit();
        } catch (Exception e) {
            ts.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }
}
