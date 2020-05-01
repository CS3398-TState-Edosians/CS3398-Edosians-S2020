import java.io.*;

public class User implements Serializable {
    private static final long serialVersionUID = 112358132134L;
    private File info;
    private String userName;
    private static final String filename = "user_info.txt" ;

    public User(String userName){
        setUserName(userName);
    }


    public String getUserName() {
        return this.userName;
    }

    private void setUserName(String userName) {
        this.userName = userName;
    }



    public void createInfoFile() {
        info = new File(filename);
        try {
            if (info.createNewFile()) {
                System.out.println("FIle created");
            } else {
                System.out.println("file already exists");
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void writeUserToInfoFile()  {
        createInfoFile();
        try{
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream userObjOS = new ObjectOutputStream(fileOut);
            userObjOS.writeObject(this);
            userObjOS.close();
            fileOut.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static User readUserFromFile(){
        try{
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream userObjIS = new ObjectInputStream(fileIn);
            Object temp = userObjIS.readObject();
            return  (User)temp;
        } catch (ClassNotFoundException | IOException e){
            e.printStackTrace();
        }
        return new User("file no user");
    }


}
