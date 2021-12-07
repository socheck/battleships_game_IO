package IA;

public class IAController {
    private int gameMode;
    public void moveAI(){
        switch(getGameMode()) {
            case 0:
                easyMode();
                break;
            case 1:
                mediumMode();
                break;
            case 2:
                hardMode();
                break;
            default:
                System.out.println("Nie wybrano żadnego trybu!");
        }
    }

    private void easyMode(){
        System.out.println("EasyMode");
    }

    private void mediumMode(){
        System.out.println("MediumMode");
    }

    private void hardMode(){
        System.out.println("HardMode");
    }

    public int getGameMode() {
        return gameMode;
    }

    public void setGameMode(int gameMode) {
        this.gameMode = gameMode;
    }
}
