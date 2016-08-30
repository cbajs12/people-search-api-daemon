package crawler.vo;

public class BaseVO {
    private int base_seq;
    private String base_name;
    private String base_flag;

    public BaseVO(){}

    public BaseVO(BaseVO vo) {
        this.base_seq = vo.getBase_seq();
        this.base_name = vo.getName();
        this.base_flag = vo.getFlag();
    }

    public BaseVO(String name, String flag) {
        this.base_name = name;
        this.base_flag = flag;
    }

    public void setBase_seq(int base_seq) {
        this.base_seq = base_seq;
    }

    public int getBase_seq() {
        return base_seq;
    }

    public String getName() {
        return base_name;
    }

    public void setName(String name) {
        this.base_name = name;
    }

    public String getFlag() {
        return base_flag;
    }

    public void setFlag(String flag) {
        this.base_flag = flag;
    }

//    public String toString() {
//        return "BaseVO [" + getUrl() + ", " + getTitle() + "]";
//    }
}
