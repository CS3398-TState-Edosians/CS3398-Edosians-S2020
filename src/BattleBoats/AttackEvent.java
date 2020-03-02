package BattleBoats;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class AttackEvent {
    private final ObjectProperty<Integer> targetColumn = new SimpleObjectProperty<>(null);
    private final ObjectProperty<Integer> targetRow = new SimpleObjectProperty<>(null);
    private final StringProperty attackPlayer = new SimpleStringProperty();
    private final StringProperty attackText = new SimpleStringProperty();

    public AttackEvent(){}

    public AttackEvent(Integer targetColumn, Integer targetRow, String attackPlayer, String attackText){
        this.targetColumn.set(targetColumn);
        this.targetRow.set(targetRow);
        this.attackPlayer.set(attackPlayer);
        this.attackText.set(attackText);
    }

    public void setTargetColumn(Integer targetColumn){
        this.targetColumn.set(targetColumn);
    }

    public Integer getTargetColumn(){
        return this.targetColumn.get();
    }

    public ObjectProperty<Integer> targetColumnProperty(){ return targetColumn; }

    public void setTargetRow(Integer targetRow){
        this.targetRow.set(targetRow);
    }

    public Integer getTargetRow(){
        return this.targetRow.get();
    }

    public ObjectProperty<Integer> targetRowProperty(){
        return targetRow;
    }

    public void setAttackPlayer(String entryAuthor){
        this.attackPlayer.set(entryAuthor);
    }

    public String getAttackPlayer(){
        return this.attackPlayer.get();
    }

    public StringProperty attackPlayerProperty(){
        return attackPlayer;
    }

    public void setAttackText(String entryText){
        this.attackText.set(entryText);
    }

    public String getAttackText(){
        return this.attackText.get();
    }

    public StringProperty attackTextProperty(){
        return attackText;
    }

}
