package crawler.vo;

public class NameVO {
    private int id;
    private String os;
    private String name;
    private String flag;

    public NameVO(NameVO vo) {
        this.id = vo.getId();
        this.os = vo.getOs();
        this.name = vo.getName();
        this.flag = vo.getFlag();
    }

    public NameVO(int id, String os, String name, String flag) {
        this.id = id;
        this.os = os;
        this.name = name;
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    //    public String toString() {
//        return "BaseVO [" + getUrl() + ", " + getTitle() + "]";
//    }
}
