import javafx.scene.Parent;


public class Ship extends Parent {

    public int type;
    public boolean vertical = true;
    public boolean isEnemy;
    private int health;


    /*Constructor*/
    public Ship(int type, boolean vertical, boolean isEnemy) {
        this.type = type;
        this.vertical = vertical;
        this.isEnemy= isEnemy;
        health = type;

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
        if(health == 0)
        {

        }
    }

    /*isAlive*/
    public boolean isAlive() { return health > 0; }

}

