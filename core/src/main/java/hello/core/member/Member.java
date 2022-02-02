package hello.core.member;

public class Member {
    private Long id;
    private Grade grade;
    private String name;

    public Member(Long id, Grade grade, String name) {
        this.id = id;
        this.grade = grade;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
