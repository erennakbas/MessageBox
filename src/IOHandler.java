import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;

public class IOHandler {
    String messageFileName;
    String userFileName;
    private Path messagesPath;
    private Path usersPath;
    private FileWriter messOut;
    private FileWriter userOut;
    Cryptographer crypto;

    public IOHandler(String messageFileName, String userFileName){
        try {
            this.crypto = new Cryptographer();
            this.messageFileName = messageFileName;
            this.userFileName=userFileName;
            this.messagesPath = Paths.get(messageFileName);
            this.usersPath = Paths.get(userFileName);


        }catch(Exception e){System.out.println(e);}
    }
    //Method for reading plainText to get an utilizable arraylist for encrypting
    public byte[] readMessageList() throws Exception{
        String line = String.join( "",Files.readAllLines(this.messagesPath));
        byte[] bytes = Base64.getDecoder().decode(line);
        return crypto.decrypt(bytes);
//
    }
    //Method for reading cipherText to get an utilizable arraylist for decrypting
    public byte[] readUserList() throws Exception{
        String line = String.join( "",Files.readAllLines(this.usersPath));
        byte[] bytes = Base64.getDecoder().decode(line);
        return crypto.decrypt(bytes);
//        String lines = String.join( "\n",Files.readAllLines(this.usersPath));
//        return lines.getBytes();
//

    }
    public void writeMessages(ArrayList<Message> messageList, ArrayList<User> userList) throws Exception{
        StringBuilder builder = new StringBuilder();
        StringBuilder userBuilder = new StringBuilder();
        this.messOut = new FileWriter(messageFileName);
        this.userOut = new FileWriter(userFileName);
        for (Message m : messageList){
            builder.append(m.toString()+"\n");
        }
        for (User u : userList){
            userBuilder.append(u.toString()+"\n");
        }

        byte[] encryptedMessages=crypto.encrypt(builder.toString().getBytes());
        messOut.write(Base64.getEncoder().encodeToString(encryptedMessages));
//        System.out.println(userBuilder.toString());
        byte[] encryptedUsers = crypto.encrypt(userBuilder.toString().getBytes());
        userOut.write(Base64.getEncoder().encodeToString(encryptedUsers));
//        userOut.write(Base64.getEncoder().encodeToString(encryptedUsers));
//        userOut.close();
        messOut.close();
        userOut.close();
    }

}

