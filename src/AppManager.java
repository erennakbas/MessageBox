import java.security.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;

public class AppManager {
    // Static self-referential attribute for other classes to use.
    // When AppManager is created, this attribute is reachable from other classes.
    private static AppManager manager;
    private ArrayList<User> userList;
    private ArrayList<Message> messageList;
    private HashMap<String, Integer> messageIndexes = new HashMap<String,Integer>();
    private HashMap<String, Integer> userIndexes = new HashMap<String,Integer>();
    public AppManager(String messages, String users){
        userList = new ArrayList<User>();
        messageList = new ArrayList<Message>();
        this.parseMessages(messages);
        this.parseUsers(users);
        manager = this;
    }
    //Parse user strings object to user arraylist
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
    //Parse message strings object to message arraylist
    private void parseMessages(String messages){
        if (messages.length()==0) return;
        String[] messageLines= messages.split("\n");
        int i=0;
        for (String messageLine: messageLines){
            String[] messageParameters =messageLine.split("-");
            Message message = new Message(messageParameters[0],messageParameters[1],messageParameters[2],messageParameters[3]);
            messageList.add(message);
            messageIndexes.put(message.getCodeName(),i);
            i++;
        }
    }
    //Helper method to get usernames of the program.
    public ArrayList<String> getUsernames(){
        ArrayList<String> usernames = new ArrayList<String>();
        for (User u: userList){
            usernames.add(u.getUsername());
        }
        return usernames;
    }
    //Method for adding new messages with constraints.
    public boolean addMessage(String codeName, String password, String confirmPassword, String content, String receiverName){
        if (codeName.equals("")) {System.out.println("Codename cannot be empty."); return false;}
        if(password.equals("")) {System.out.println("Password cannot be empty."); return false;}

        if (!messageIndexes.containsKey(codeName)){
            if (!password.equals(confirmPassword)){
                System.out.println("Password's are not the same.");
            }
            messageList.add(new Message(codeName, this.hashPassword(password), content, receiverName));
            messageIndexes.put(codeName, messageList.size()-1);
            System.out.println("Message is added.");
            return true;
        }
        else{
            System.out.println("There is a message with the same codename, please use another one.");
            return false;
        }

    }
    //Helper method to query an user with username string
    public User getUserByUsername(String username){
        if (!userIndexes.containsKey(username)) return null;
        return userList.get(userIndexes.get(username));
    }
    //Helper method to query a message with codename string
    public Message getMessageByCodename(String codename){
        if (!messageIndexes.containsKey(codename)) return null;
        return messageList.get(messageIndexes.get(codename));
    }
    //Method for validating / authorizing the user for access.
    public String validateUser(String codename, String messagePassword, String username, String userPassword){
        Message message = this.getMessageByCodename(codename);
        User user = this.getUserByUsername(username);
        if (user!=null && message!=null){
            //If username is authorized to see the message.
            if (user.getUsername().equals(message.getReceiverName())){
                //If user's password and message's passwords are true, then get the message.
                if (this.comparePasswords(userPassword, user.getHashedPassword()) && this.comparePasswords(messagePassword, message.getHashedPassword())){
                    return message.getContent();
                }
            }
        }
        System.out.println("You are not authorized.");
        return null;
    }
    //Method for comparing the password input with the password registered in our system
    public boolean comparePasswords(String passwordAttempt, String hashedPassword){
        return hashPassword(passwordAttempt).equals(hashedPassword);
    }
    //Method for hashing the password.
    public String hashPassword(String password){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            String hashedString = Base64.getEncoder().encodeToString(hash);
            return hashedString;
        }
        catch (Exception e){e.printStackTrace(); return new String("");}
    }
    public static AppManager getManager(){
        return manager;
    }

    public ArrayList<Message> getMessageList() {
        return messageList;
    }
}
