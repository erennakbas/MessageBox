import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MessageRegisterPage {
    public MessageRegisterPage(ArrayList<String> userList){    //user objesi alacak
        Object[] users = userList.toArray();

        JFrame registerPage = new JFrame("Register Form");
        registerPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel authUserName=new JLabel("Auth. username*");
        authUserName.setSize(120,20);

        JComboBox authUsers = new JComboBox(users);
        authUsers.setSize(150,30);

        JLabel password=new JLabel("Password*");
        password.setSize(120,20);

        JPasswordField passwordText= new JPasswordField();
        passwordText.setSize(150,30);

        JLabel confirmPassword=new JLabel("Confirm Password*");
        confirmPassword.setSize(120,20);

        JPasswordField confirmPasswordText= new JPasswordField();
        confirmPasswordText.setSize(150,30);

        JLabel messageCodeName=new JLabel("Message codename*");
        messageCodeName.setSize(120,20);

        JTextField messageCodeText= new JTextField();
        messageCodeText.setSize(150,30);

        JLabel messageContent=new JLabel("ENTER YOUR MESSAGE*");
        messageContent.setSize(200,20);

        JTextArea messageContentText= new JTextArea(5,10);
        messageContentText.setSize(400,120);
        JScrollPane srcPane = new JScrollPane(messageContentText);
        srcPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JButton createMessage = new JButton("Create Message");
        createMessage.setSize(150,40);

        createMessage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(AppManager.getManager().addMessage(messageCodeName.getText(), passwordText.getText(),
                        confirmPasswordText.getText(), messageContentText.getText(),  authUserName.getText())){
                    HomePage h=new HomePage(userList);
                    registerPage.setVisible(false);
                    }
                }

        });


        JButton home = new JButton("HOME");
        home.setSize(150,40);
        home.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HomePage h=new HomePage(userList);
                registerPage.setVisible(false);
            }
        });

        Container pane=registerPage.getContentPane();
        pane.setLayout(null);

        pane.add(authUserName);
        pane.add(authUsers);
        pane.add(password);
        pane.add(passwordText);
        pane.add(confirmPassword);
        pane.add(confirmPasswordText);
        pane.add(messageCodeName);
        pane.add(messageCodeText);
        pane.add(messageContent);
        pane.add(messageContentText);
        pane.add(srcPane);
        pane.add(createMessage);
        pane.add(home);


        Insets in = pane.getInsets();

        Dimension size =authUserName.getSize();
        authUserName.setBounds(30 + in.left, 60 + in.top,
                size.width, size.height);

        size = authUsers.getSize();
        authUsers.setBounds(160 + in.left, 55 + in.top,
                size.width, size.height);

        size = password.getSize();
        password.setBounds(30 + in.left, 100 + in.top,
                size.width + 50, size.height + 20);

        size = passwordText.getSize();
        passwordText.setBounds(160 + in.left, 105 + in.top,
                size.width , size.height );

        size =confirmPassword.getSize();
        confirmPassword.setBounds(330 + in.left, 100 + in.top,
                size.width+ 50, size.height+ 20);

        size = confirmPasswordText.getSize();
        confirmPasswordText.setBounds(460 + in.left, 105 + in.top,
                size.width, size.height);

        size = messageCodeName.getSize();
        messageCodeName.setBounds(30 + in.left, 140 + in.top,
                size.width + 50, size.height + 20);

        size = messageCodeText.getSize();
        messageCodeText.setBounds(160 + in.left, 150 + in.top,
                size.width , size.height);

        size = messageContent.getSize();
        messageContent.setBounds(30 + in.left, 240 + in.top,
                size.width , size.height + 20);

        size = messageContentText.getSize();
        messageContentText.setBounds(220 + in.left, 220 + in.top,
                size.width , size.height );

        size = srcPane.getSize();
        srcPane.setBounds(300 + in.left, 250 + in.top,
                size.width , size.height );

        size = createMessage.getSize();
        createMessage.setBounds(100 + in.left, 385 + in.top,
                size.width , size.height );

        size = home.getSize();
        home.setBounds(400 + in.left, 385 + in.top,
                size.width , size.height );


        Insets sets = registerPage.getInsets();
        registerPage.setSize(700 + sets.left + sets.right,
                600 + sets.top + sets.bottom);
        registerPage.setVisible(true);
    }
}
