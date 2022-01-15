package bs_game_backend;

public class ClassToComboBox {
    private String path;
    private String dispayText;

    public ClassToComboBox(String dispayText) {
        this.path = "/img/avatars/user/" + dispayText + ".jpg";
        this.dispayText = dispayText;
    }

    @Override
    public String toString() {
        return dispayText;
    }

    public String getPath() {
        return path;
    }

    public String getDispayText() {
        return dispayText;
    }

}
