import javafx.scene.Parent;


public class Ship extends Parent {
    public String name;
    public int type;
    public boolean vertical = true;
    public boolean isEnemy;
    private int health;
    public int x;
    public int y;
    public int len;

    /*Constructor*/
    public Ship(int type, boolean vertical, boolean isEnemy, int x, int y) {
        this.type = type;
        this.vertical = vertical;
        this.isEnemy= isEnemy;
        this.x = x;
        this.y = y;

        switch (type){
            case 5:
                this.name = "aircraft_carrier.png";
                this.health = 5;
                this.len = 5;
                break;
            case 4:
                this.name = "battleship.png";
                this.health = 4;
                this.len = 4;
                break;
            case 3:
                this.name = "destroyer.png";
                this.health = 3;
                this.len = 3;
                break;
            case 2:
                this.name = "submarine.png";
                this.health = 2;
                this.len = 2;
                break;
            default:
                this.name = "cruiser.png";
                this.health = 4;
                this.len = 4;
        }
    }

    /*isAlive*/
    public void hit() {
        health--;
    }

    /*isAlive*/
    public boolean isAlive() { return health > 0; }


}