import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;

public class IOHandler {
    String messageFileName;
    private Path messagesPath;
    private Path usersPath;
    private FileWriter messOut;
    Cryptographer crypto;

    public IOHandler(String messageFileName, String userFileName){
        try {
            this.crypto = new Cryptographer();
            this.messageFileName = messageFileName;
            this.messagesPath = Paths.get(messageFileName);
            this.usersPath = Paths.get(userFileName);


        }catch(Exception e){System.out.println(e);}
    }
    //Method for reading plainText to get an utilizable arraylist for encrypting
    public byte[] readMessageFile() throws Exception{
        String line = String.join( "",Files.readAllLines(this.messagesPath));
        byte[] bytes = Base64.getDecoder().decode(line);
        return crypto.decrypt(bytes);
//
    }
    //Method for reading cipherText to get an utilizable arraylist for decrypting
    public byte[] readUserFile() throws Exception{
        String line = String.join( "",Files.readAllLines(this.usersPath));
        byte[] bytes = Base64.getDecoder().decode(line);
        return crypto.decrypt(bytes);
    }
    //Method for saving new messages to our txt file.
    public void writeMessages(ArrayList<Message> messageList) throws Exception{
        StringBuilder builder = new StringBuilder();
        this.messOut = new FileWriter(messageFileName);
        for (Message m : messageList){
            builder.append(m.toString()+"\n");
        }
        byte[] encryptedMessages=crypto.encrypt(builder.toString().getBytes());
        messOut.write(Base64.getEncoder().encodeToString(encryptedMessages));
        messOut.close();
    }

}

