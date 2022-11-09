import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        try{
        IOHandler ioHandler = new IOHandler("messages.txt", "users.txt");
        Cryptographer cryptographer = new Cryptographer();
        String messages = new String(ioHandler.readMessageList());
        String users = new String(ioHandler.readUserList());
        AppManager appManager = new AppManager(messages, users);
        //AuthorizationManager authorizationManager = new AuthorizationManager(appManager);


        HomePage h= new HomePage(appManager.getUsernames());

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
