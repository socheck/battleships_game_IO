package sample;

public class ClassToComboBox {
    private String path;
    private String dispayText;

    public ClassToComboBox(String dispayText) {
        this.path = "/img/avatars/" + dispayText + ".jpg";
        this.dispayText = dispayText;
    }

    @Override
    public String toString() {
        return dispayText;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDispayText() {
        return dispayText;
    }

    public void setDispayText(String dispayText) {
        this.dispayText = dispayText;
    }
}
