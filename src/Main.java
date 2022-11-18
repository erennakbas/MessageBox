public class Main {

    public static void main(String[] args) {
        try{

        IOHandler ioHandler = new IOHandler("messages.data", "users.data");
        String messages = new String(ioHandler.readMessageFile());
        String users = new String(ioHandler.readUserFile());
        AppManager appManager = new AppManager(messages, users);

        HomePage homePage= new HomePage(appManager.getUsernames());

            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                public void run() {
                    try {
                        //Write new messages before terminating the program.
                        ioHandler.writeMessages(AppManager.getManager().messageList);
                    }
                    catch (Exception e){
                        System.out.println("Error while writing the message list.");
                        e.printStackTrace();
                    }
                }
            }));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
