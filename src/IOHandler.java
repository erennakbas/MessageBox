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


    public IOHandler(String messageFileName, String userFileName){
        try {
            this.messageFileName = messageFileName;
            this.messagesPath = Paths.get(messageFileName);
            this.usersPath = Paths.get(userFileName);

//            this.userOut = new FileWriter(userFile, true);

        }catch(Exception e){System.out.println(e);}
    }
    //Method for reading plainText to get an utilizable arraylist for encrypting
    public byte[] readMessageList() throws IOException{
        String lines = String.join("\n",Files.readAllLines(this.messagesPath));
        return lines.getBytes();
//        return Base64.getDecoder().decode(lines);
    }
    //Method for reading cipherText to get an utilizable arraylist for decrypting
    public byte[] readUserList() throws IOException{
        String lines = String.join("\n",Files.readAllLines(this.usersPath));
        return lines.getBytes();
//        return Base64.getDecoder().decode(lines);

    }
    public void writeMessages(ArrayList<Message> messageList) throws IOException{
        this.messOut = new FileWriter(messageFileName);
        for (Message m : messageList){
            messOut.write(m.toString()+"\n");
        }
//        userOut.write(Base64.getEncoder().encodeToString(encryptedUsers));
//        userOut.close();
        messOut.close();
    }

}

