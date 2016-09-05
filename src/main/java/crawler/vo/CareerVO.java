package crawler.vo;

public class CareerVO {
    private int career_seq;
    private String career_dt;
    private String description;
    private String career_os;

    public CareerVO(){}

    public CareerVO(CareerVO vo) {
        this.career_seq = vo.getCareer_seq();
        this.career_dt = vo.getCareer_dt();
        this.description = vo.getDescription();
        this.career_os = vo.getCareer_os();
    }

    public CareerVO(int id, String career_dt, String description, String career_os) {
        this.career_seq = id;
        this.career_dt = career_dt;
        this.description = description;
        this.career_os = career_os;
    }

    public CareerVO(String career_os){
        this.career_os = career_os;
    }

    public int getCareer_seq() {
        return career_seq;
    }

    public void setCareer_seq(int career_seq) {
        this.career_seq = career_seq;
    }

    public String getCareer_dt() {
        return career_dt;
    }

    public void setCareer_dt(String career_dt) {
        this.career_dt = career_dt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCareer_os() {
        return career_os;
    }

    public void setCareer_os(String career_os) {
        this.career_os = career_os;
    }

    //    public String toString() {
//        return "BaseVO [" + getUrl() + ", " + getTitle() + "]";
//    }
}
