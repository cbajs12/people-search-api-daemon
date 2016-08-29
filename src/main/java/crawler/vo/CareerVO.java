package crawler.vo;

public class CareerVO {
    private int id;
    private String st_dt;
    private String end_dt;
    private String description;
    private String career_code;

    public CareerVO(CareerVO vo) {
        this.id = vo.getId();
        this.st_dt = vo.getSt_dt();
        this.end_dt = vo.getEnd_dt();
        this.description = vo.getDescription();
        this.career_code = vo.getCareer_code();
    }

    public CareerVO(int id, String st_dt, String end_dt, String description, String career_code) {
        this.id = id;
        this.st_dt = st_dt;
        this.end_dt = end_dt;
        this.description = description;
        this.career_code = career_code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSt_dt() {
        return st_dt;
    }

    public void setSt_dt(String st_dt) {
        this.st_dt = st_dt;
    }

    public String getEnd_dt() {
        return end_dt;
    }

    public void setEnd_dt(String end_dt) {
        this.end_dt = end_dt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCareer_code() {
        return career_code;
    }

    public void setCareer_code(String career_code) {
        this.career_code = career_code;
    }

    //    public String toString() {
//        return "BaseVO [" + getUrl() + ", " + getTitle() + "]";
//    }
}
