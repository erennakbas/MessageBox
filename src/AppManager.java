import java.security.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;

public class AppManager {
    private static AppManager manager;
    public ArrayList<User> userList;
    public ArrayList<Message> messageList;
    private HashMap<String, Integer> messageIndexes = new HashMap<String,Integer>();
    private HashMap<String, Integer> userIndexes = new HashMap<String,Integer>();
    public AppManager(String messages, String users){
        userList = new ArrayList<User>();
        messageList = new ArrayList<Message>();
        parseMessages(messages);
        parseUsers(users);
        manager = this;
    }
    private void parseUsers(String users){
        if (users.length()==0) return;
        String[] userLines= users.split("\n");
        int i=0;
        for (String userLine: userLines){
            String[] userParameters =userLine.split("-");
            User user = new User(userParameters[0], userParameters[1]);
            userList.add(user);
            userIndexes.put(user.getUsername(), i);
            i++;
        }

    }
    private void parseMessages(String messages){
        if (messages.length()==0) return;
        String[] messageLines= messages.split("\n");
        int i=0;
        for (String messageLine: messageLines){
            System.out.println(messageLine);
            String[] messageParameters =messageLine.split("-");

            Message message = new Message(messageParameters[0],messageParameters[1],messageParameters[2],messageParameters[3]);
            messageList.add(message);
            messageIndexes.put(message.getCodeName(),i);
            i++;
        }
    }
    public ArrayList<String> getUsernames(){
        ArrayList<String> usernames = new ArrayList<String>();
        for (User u: userList){
            usernames.add(u.getUsername());
        }
        return usernames;
    }
    //passwordler kıyaslanacak sonra onaylanırsa hashlenecek.
    public boolean addMessage(String codeName, String password, String confirmPassword, String content, String receiverName){
        if (!messageIndexes.containsKey(codeName)){
            if (!password.equals(confirmPassword)){
                System.out.println("Password's are not the same.");
            }
            messageList.add(new Message(codeName, this.hashPassword(password), content, receiverName));
            messageIndexes.put(codeName, messageList.size()-1);
            System.out.println("App Manager'a mesaj eklendi.");
            return true;
        }
        else{
            System.out.println("Aynı codenamede mesaj var.");
            return false;
        }

    }
    public User getUserByUsername(String username){
        if (!userIndexes.containsKey(username)) return null;
        return userList.get(userIndexes.get(username));
    }
    public Message getMessageByCodename(String codename){
        if (!messageIndexes.containsKey(codename)) return null;
        return messageList.get(messageIndexes.get(codename));
    }
    public String validate(String codename, String messagePassword, String username, String userPassword){
        Message message = getMessageByCodename(codename);
        User user = getUserByUsername(username);
        if (user!=null && message!=null){
            if (user.getUsername().equals(message.getReceiverName())){
                if (this.comparePasswords(userPassword, user.getHashedPassword()) && this.comparePasswords(messagePassword, message.getHashedPassword())){
                    System.out.println("validate edildi");

                    return message.getContent();
                }
            }
        }
        System.out.println("validate edilmedi");

        return null;
    }
    public boolean comparePasswords(String passwordAttempt, String hashedPassword){
        return hashPassword(passwordAttempt).equals(hashedPassword);
    }
    public String hashPassword(String password){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            String hashedString = Base64.getEncoder().encodeToString(hash);
            System.out.println(hashedString);
            return hashedString;
        }
        catch (Exception e){e.printStackTrace(); return new String("");}
    }
    public static AppManager getManager(){
        return manager;
    }
}
