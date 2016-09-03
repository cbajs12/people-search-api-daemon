package crawler.vo;

public class BaseVO {
    private int base_seq;
    private String base_name;
    private String base_code;

    public BaseVO(){}

    public BaseVO(BaseVO vo) {
        this.base_seq = vo.getBase_seq();
        this.base_name = vo.getBase_name();
        this.base_code = vo.getBase_code();
    }

    public BaseVO(String name, String flag) {
        this.base_name = name;
        this.base_code = flag;
    }

    public BaseVO(String name){
        this.base_name = name;
        this.base_code = "CR001";
    }

    public void setBase_seq(int base_seq) {
        this.base_seq = base_seq;
    }

    public int getBase_seq() {
        return base_seq;
    }

    public String getBase_name() {
        return base_name;
    }

    public void setBase_name(String base_name) {
        this.base_name = base_name;
    }

    public String getBase_code() {
        return base_code;
    }

    public void setBase_code(String base_code) {
        this.base_code = base_code;
    }

    //    public String toString() {
//        return "BaseVO [" + getUrl() + ", " + getTitle() + "]";
//    }
}
