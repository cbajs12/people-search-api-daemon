package crawler.vo;

public class NameVO {
    private int name_seq;
    private String name_os;
    private String name_names;
    private String name_code;

    public NameVO(NameVO vo) {
        this.name_seq = vo.getName_seq();
        this.name_os = vo.getName_os();
        this.name_names = vo.getName_names();
        this.name_code = vo.getName_code();
    }

    public NameVO(int id, String os, String name, String flag) {
        this.name_seq = id;
        this.name_os = os;
        this.name_names = name;
        this.name_code = flag;
    }

    public NameVO(){}

    public NameVO(String name, String flag){
        this.name_names = name;
        this.name_code = flag;
    }

    public int getName_seq() {
        return name_seq;
    }

    public void setName_seq(int name_seq) {
        this.name_seq = name_seq;
    }

    public String getName_os() {
        return name_os;
    }

    public void setName_os(String name_os) {
        this.name_os = name_os;
    }

    public String getName_names() {
        return name_names;
    }

    public void setName_names(String name_names) {
        this.name_names = name_names;
    }

    public String getName_code() {
        return name_code;
    }

    public void setName_code(String name_code) {
        this.name_code = name_code;
    }

    //    public String toString() {
//        return "BaseVO [" + getUrl() + ", " + getTitle() + "]";
//    }
}
