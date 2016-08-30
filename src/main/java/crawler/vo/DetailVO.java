package crawler.vo;

public class DetailVO {
    private int detail_seq;
    private int career_seq;
    private int family_seq;
    private String detail_os;
    private String age;
    private String detail_date;
    private String detail_job;
    private String physical;
    private String site;
    private String religion;

    public DetailVO(DetailVO vo) {
        this.detail_seq = vo.getDetail_seq();
        this.career_seq = vo.getCareer_seq();
        this.family_seq = vo.getFamily_seq();
        this.detail_os = vo.getDetail_os();
        this.age = vo.getAge();
        this.detail_date = vo.getDetail_date();
        this.detail_job = vo.getDetail_job();
        this.physical = vo.getPhysical();
        this.site = vo.getSite();
        this.religion = vo.getReligion();
    }

    public DetailVO(int id, int career, int family, String os, String age, String date, String job, String physical, String site, String religion) {
        this.detail_seq = id;
        this.career_seq = career;
        this.family_seq = family;
        this.detail_os = os;
        this.age = age;
        this.detail_date = date;
        this.detail_job = job;
        this.physical = physical;
        this.site = site;
        this.religion = religion;
    }

    public int getDetail_seq() {
        return detail_seq;
    }

    public void setDetail_seq(int detail_seq) {
        this.detail_seq = detail_seq;
    }

    public int getCareer_seq() {
        return career_seq;
    }

    public void setCareer_seq(int career_seq) {
        this.career_seq = career_seq;
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

    public String getDetail_date() {
        return detail_date;
    }

    public void setDetail_date(String detail_date) {
        this.detail_date = detail_date;
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

    public String getPhysical() {
        return physical;
    }

    public void setPhysical(String physical) {
        this.physical = physical;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    //    public String toString() {
//        return "BaseVO [" + getUrl() + ", " + getTitle() + "]";
//    }
}
