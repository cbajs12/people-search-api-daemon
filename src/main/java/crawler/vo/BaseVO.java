package crawler.vo;

public class BaseVO {
    private int id;
    private String name;
    private String flag;

    public BaseVO(BaseVO vo) {
        this.id = vo.getId();
        this.name = vo.getName();
        this.flag = vo.getFlag();
    }

    public BaseVO(int id, String name, String flag) {
        this.id = id;
        this.name = name;
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
