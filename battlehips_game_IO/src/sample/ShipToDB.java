package sample;



public class ShipToDB {


        private int type;
        private boolean vertical = true;
        private int health;

        public ShipToDB(int type, boolean vertical){
            this.type = type;
            this.vertical = vertical;
            this.health = type;
        }

        public void hit(){
            health--;
        }

        public int getType() {
            return type;
        }

        public boolean isVertical() {
            return vertical;
        }

        public int getHealth() {
            return health;
        }

        public boolean isAlive(){
            if (health > 0) {
                return true;
            }else {
                return false;
            }
        }





}
