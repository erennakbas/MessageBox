public class Message {
    private String codeName;
    private String hashedPassword;
    private String content;

    private String receiverName;

    public Message(String codeName, String hashedPassword, String content, String receiverName) {
        this.codeName = codeName;
        this.hashedPassword = hashedPassword;
        this.content = content;
        this.receiverName = receiverName;
    }

    public String getCodeName() {
        return codeName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getContent() {
        return content;
    }
}
