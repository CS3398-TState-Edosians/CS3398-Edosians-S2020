package BattleBoats;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class ChatEntry {
    private final StringProperty entryAuthor = new SimpleStringProperty();
    private final StringProperty entryText = new SimpleStringProperty();
    private final ObjectProperty<Integer> id = new SimpleObjectProperty<>(null);

    public ChatEntry(){}

    public ChatEntry(Integer id, String entryAuthor, String entryText){
        this.id.set(id);
        this.entryAuthor.set(entryAuthor);
        this.entryText.set(entryText);
    }

    public void setEntryAuthor(String entryAuthor){
        this.entryAuthor.set(entryAuthor);
    }

    public String getEntryAuthor(){
        return this.entryAuthor.get();
    }

    public StringProperty entryAuthorProperty(){
        return entryAuthor;
    }

    public void setEntryText(String entryText){
        this.entryText.set(entryText);
    }

    public String getEntryText(){
        return this.entryText.get();
    }

    public StringProperty entryTextProperty(){
        return entryText;
    }

    public void setId(Integer value){
        this.id.set(value);
    }

    public Integer getId(){
        return this.id.get();
    }

    public ObjectProperty<Integer> idProperty(){
        return id;
    }



}
