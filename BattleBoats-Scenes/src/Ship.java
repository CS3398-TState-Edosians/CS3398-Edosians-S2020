import javafx.scene.Parent;


public class Ship extends Parent {
    public String name;
    public int type;
    public boolean vertical = true;
    private int health;

    /*Constructor*/
    public Ship(int type, boolean vertical) {
        this.type = type;
        this.vertical = vertical;
        health = type;

        switch (type){
            case 5:
                this.name = "aircraft_carrier.png";
                break;
            case 4:
                this.name = "battleship.png";
                break;
            case 3:
                this.name = "destroyer.png";
                break;
            case 2:
                this.name = "submarine.png";
                break;
            default:
                this.name = "";
        }

        /*VBox vbox = new VBox();
        for (int i = 0; i < type; i++) {
            Rectangle square = new Rectangle(30, 30);
            square.setFill(null);
            square.setStroke(Color.BLACK);
            vbox.getChildren().add(square);
        }

        getChildren().add(vbox);*/
    }

    /*isAlive*/
    public void hit() {
        health--;
    }

    /*isAlive*/
    public boolean isAlive() { return health > 0; }


}