package sample;

import javafx.scene.image.ImageView;

public class User {
    private int id;
    private String username;
    private String password;
    private String avatar_path;
    private int wins;
    private int battles;
    private double aim_ratio;
    private ImageView photo;

    public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", avatar_path='" + avatar_path + '\'' +
                ", wins=" + wins +
                ", battles=" + battles +
                ", aim_ratio=" + aim_ratio +
                '}';
    }

    public User(int id, String username, String password, String avatar_path, int wins, int battles, double aim_ratio) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.avatar_path = avatar_path;
        this.wins = wins;
        this.battles = battles;
        this.aim_ratio = aim_ratio;
        ImageView photo = new ImageView(avatar_path);

        this.photo = photo;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar_path() {
        return avatar_path;
    }

    public void setAvatar_path(String avatar_path) {
        this.avatar_path = avatar_path;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getBattles() {
        return battles;
    }

    public void setBattles(int battles) {
        this.battles = battles;
    }

    public double getAim_ratio() {
        return aim_ratio;
    }

    public void setAim_ratio(double aim_ratio) {
        this.aim_ratio = aim_ratio;
    }
}
