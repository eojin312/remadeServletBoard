package servlet.model;

public class Member {
    private long memNo;
    private String name;
    private String id;
    private String pwd;
    private String gender;
    private String email;

    public Member(long memNo, String name, String id, String pwd, String gender, String email) {
        this.memNo = memNo;
        this.name = name;
        this.id = id;
        this.pwd = pwd;
        this.gender = gender;
        this.email = email;
    }

    public Member() {
    }

    public long getMemNo() {
        return memNo;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPwd() {
        return pwd;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {return email;}
}
