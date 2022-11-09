import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class IOHandler {
    private Path messagesPath;
    private Path usersPath;
    private FileWriter messOut;
    private FileWriter userOut;

    public IOHandler(String messageFile, String userFile){
        try {
            this.messagesPath = Paths.get(messageFile);
            this.usersPath = Paths.get(userFile);
            this.messOut = new FileWriter(messageFile, true);
            this.userOut = new FileWriter(userFile, true);

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
    public void writeDataFiles(byte[] encryptedMessages, byte[] encryptedUsers ) throws IOException{
        messOut.write(Base64.getEncoder().encodeToString(encryptedMessages));
        userOut.write(Base64.getEncoder().encodeToString(encryptedUsers));
        userOut.close();
        messOut.close();
    }

}

