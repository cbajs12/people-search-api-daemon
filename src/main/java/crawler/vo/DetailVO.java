package crawler.vo;

public class DetailVO {
    private int id;
    private int career;
    private int family;
    private String os;
    private String age;
    private String date;
    private String job;
    private String physical;
    private String site;
    private String religion;

    public DetailVO(DetailVO vo) {
        this.id = vo.getId();
        this.career = vo.getCareer();
        this.family = vo.getFamily();
        this.os = vo.getOs();
        this.age = vo.getAge();
        this.date = vo.getDate();
        this.job = vo.getJob();
        this.physical = vo.getPhysical();
        this.site = vo.getSite();
        this.religion = vo.getReligion();
    }

    public DetailVO(int id, int career, int family, String os, String age, String date, String job, String physical, String site, String religion) {
        this.id = id;
        this.career = career;
        this.family = family;
        this.os = os;
        this.age = age;
        this.date = date;
        this.job = job;
        this.physical = physical;
        this.site = site;
        this.religion = religion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCareer() {
        return career;
    }

    public void setCareer(int career) {
        this.career = career;
    }

    public int getFamily() {
        return family;
    }

    public void setFamily(int family) {
        this.family = family;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
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
