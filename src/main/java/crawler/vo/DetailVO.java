package crawler.vo;

public class DetailVO {
    private int detail_seq;
    private int family_seq;
    private String detail_os;
    private String age;
    private String detail_job;
    private String profile;
    private String detail_name;

    public DetailVO(){}

    public DetailVO(DetailVO vo) {
        this.detail_seq = vo.getDetail_seq();
        this.family_seq = vo.getFamily_seq();
        this.detail_os = vo.getDetail_os();
        this.age = vo.getAge();
        this.detail_job = vo.getDetail_job();
        this.profile = vo.getProfile();
        this.detail_name = vo.getDetail_name();
    }

    public DetailVO(int id, int family, String os, String age, String job, String profile, String detail_name) {
        this.detail_seq = id;
        this.family_seq = family;
        this.detail_os = os;
        this.age = age;
        this.detail_job = job;
        this.detail_name = detail_name;
        this.profile = profile;
    }

    public int getDetail_seq() {
        return detail_seq;
    }

    public void setDetail_seq(int detail_seq) {
        this.detail_seq = detail_seq;
    }

    public int getFamily_seq() {
        return family_seq;
    }

    public void setFamily_seq(int family_seq) {
        this.family_seq = family_seq;
    }

    public String getDetail_os() {
        return detail_os;
    }

    public void setDetail_os(String detail_os) {
        this.detail_os = detail_os;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDetail_job() {
        return detail_job;
    }

    public void setDetail_job(String detail_job) {
        this.detail_job = detail_job;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getDetail_name() {
        return detail_name;
    }

    public void setDetail_name(String detail_name) {
        this.detail_name = detail_name;
    }

    //    public String toString() {
//        return "BaseVO [" + getUrl() + ", " + getTitle() + "]";
//    }
}
