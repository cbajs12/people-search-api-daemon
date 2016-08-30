package crawler.vo;

public class FamilyVO {
    private int family_seq;
    private String fm_os;
    private String fm_name;

    public FamilyVO(FamilyVO vo) {
        this.family_seq = vo.getFamily_seq();
        this.fm_os = vo.getFm_os();
        this.fm_name = vo.getFm_name();
    }

    public FamilyVO(int id, String fm_os, String fm_name) {
        this.family_seq = id;
        this.fm_os = fm_os;
        this.fm_name = fm_name;
    }

    public int getFamily_seq() {
        return family_seq;
    }

    public void setFamily_seq(int family_seq) {
        this.family_seq = family_seq;
    }

    public String getFm_os() {
        return fm_os;
    }

    public void setFm_os(String fm_os) {
        this.fm_os = fm_os;
    }

    public String getFm_name() {
        return fm_name;
    }

    public void setFm_name(String fm_name) {
        this.fm_name = fm_name;
    }

    //    public String toString() {
//        return "BaseVO [" + getUrl() + ", " + getTitle() + "]";
//    }
}
