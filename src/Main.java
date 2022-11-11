public class Main {

    public static void main(String[] args) {
        try{
        IOHandler ioHandler = new IOHandler("messages.txt", "users.txt");
        String messages = new String(ioHandler.readMessageList());
        String users = new String(ioHandler.readUserList());
        AppManager appManager = new AppManager(messages, users);
        //AuthorizationManager authorizationManager = new AuthorizationManager(appManager);
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                public void run() {
                    try {
                        ioHandler.writeMessages(AppManager.getManager().messageList, AppManager.getManager().userList);
                    }
                    catch (Exception e){
                        System.out.println("Error while writing the message list.");
                        e.printStackTrace();
                    }
                }
            }));
        HomePage h= new HomePage(appManager.getUsernames());

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
