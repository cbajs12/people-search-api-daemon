package crawler.vo;

public class CareerVO {
    private int career_seq;
    private String career_dt;
    private String description;

    public CareerVO(CareerVO vo) {
        this.career_seq = vo.getCareer_seq();
        this.career_dt = vo.getCareer_dt();
        this.description = vo.getDescription();
    }

    public CareerVO(int id, String career_dt, String description) {
        this.career_seq = id;
        this.career_dt = career_dt;
        this.description = description;
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

    //    public String toString() {
//        return "BaseVO [" + getUrl() + ", " + getTitle() + "]";
//    }
}
