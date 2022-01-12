package sample;

import javafx.scene.image.ImageView;

import java.text.DecimalFormat;

public class User {
    private int id;
    private String username;
    private String password;
    private String avatar_path;
    private int wins;
    private int battles;
    private int shots_amount;
    private int hits_amount;
    private ImageView photo;
    private double aimRatio;

    public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return username;
    }
    public User(int id, String username, String password, String avatar_path, int wins, int battles, int shots_amount, int hits_amount) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.avatar_path = avatar_path;
        this.wins = wins;
        this.battles = battles;
        this.shots_amount = shots_amount;
        this.hits_amount = hits_amount;
        if(shots_amount == 0){
            this.aimRatio = 0;
        }else {
            this.aimRatio = (int)(Math.round((double) hits_amount/(double) shots_amount * 100))/100.0;
        }



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

    public int getShots_amount() {
        return shots_amount;
    }

    public int getHits_amount() {
        return hits_amount;
    }

    public double getAimRatio() {
        return aimRatio;
    }
}
