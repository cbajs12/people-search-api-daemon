package crawler.vo;

public class FamilyVO {
    private int family_seq;
    private String fm_json;

    public FamilyVO(){}

    public FamilyVO(FamilyVO vo) {
        this.family_seq = vo.getFamily_seq();
        this.fm_json = vo.getFm_json();
    }

    public FamilyVO(String fm_json) {
        this.fm_json = fm_json;
    }

    public int getFamily_seq() {
        return family_seq;
    }

    public void setFamily_seq(int family_seq) {
        this.family_seq = family_seq;
    }

    public String getFm_json() {
        return fm_json;
    }

    public void setFm_json(String fm_json) {
        this.fm_json = fm_json;
    }

    //    public String toString() {
//        return "BaseVO [" + getUrl() + ", " + getTitle() + "]";
//    }
}
