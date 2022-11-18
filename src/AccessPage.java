import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AccessPage {
    public AccessPage(ArrayList<String> userList){
        JFrame accessPage = new JFrame("Message View");
        accessPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JLabel codeName=new JLabel("Message Codename");
        codeName.setSize(120,20);

        JTextField codeText= new JTextField();
        codeText.setSize(150,30);

        JLabel messagePassword=new JLabel("Message Password");
        messagePassword.setSize(120,20);

        JPasswordField passwordText= new JPasswordField();
        passwordText.setSize(150,30);

        JLabel userName=new JLabel("User Name");
        userName.setSize(120,20);

        JTextField userNameText= new JTextField();
        userNameText.setSize(150,30);

        JLabel userPassword=new JLabel("User Password");
        userPassword.setSize(120,20);

        JPasswordField userPasswordText= new JPasswordField();
        userPasswordText.setSize(150,30);

        JRadioButton showButton = new JRadioButton();
        showButton.setSize(1,1);
        showButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(showButton.isSelected()){
                    passwordText.setEchoChar((char)0);
                    userPasswordText.setEchoChar((char)0);
                }else{
                    userPasswordText.setEchoChar((char)8226);
                    passwordText.setEchoChar((char)8226);
                }
            }
        });

        JLabel show = new JLabel("Show Password");
        show.setSize(120,20);

        JButton view = new JButton("VIEW");
        view.setSize(120,40);
        view.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Call validateUser function to execute the VIEW button's click event, authorize the user.
                String content = AppManager.getManager().validateUser(codeText.getText(), passwordText.getText(), userNameText.getText(), userPasswordText.getText());
                //If user is authorized (A non null value returned from the function)
                if (content!=null){
                    MessageViewPage m=new MessageViewPage(userList, content);
                    accessPage.setVisible(false);
                }
            }
        });

        JButton reset = new JButton("RESET");
        reset.setSize(120,40);
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                codeText.setText("");
                passwordText.setText("");
                userNameText.setText("");
                userPasswordText.setText("");
            }
        });


        JButton home = new JButton("HOME");
        home.setSize(120,40);
        home.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HomePage h=new HomePage(userList);
                accessPage.setVisible(false);
            }
        });

        Container pane=accessPage.getContentPane();
        pane.setLayout(null);

        pane.add(codeName);
        pane.add(codeText);
        pane.add(messagePassword);
        pane.add(passwordText);
        pane.add(userName);
        pane.add(userNameText);
        pane.add(userPassword);
        pane.add(userPasswordText);
        pane.add(showButton);
        pane.add(show);
        pane.add(view);
        pane.add(reset);
        pane.add(home);
        Insets in = pane.getInsets();

        Dimension size =codeName.getSize();
        codeName.setBounds(30 + in.left, 60 + in.top,
                size.width, size.height);

        size = codeText.getSize();
        codeText.setBounds(160 + in.left, 55 + in.top,
                size.width, size.height);

        size = messagePassword.getSize();
        messagePassword.setBounds(30 + in.left, 100 + in.top,
                size.width + 50, size.height + 20);

        size = passwordText.getSize();
        passwordText.setBounds(160 + in.left, 105 + in.top,
                size.width , size.height );

        size =userName.getSize();
        userName.setBounds(30 + in.left, 160 + in.top,
                size.width, size.height);

        size = userNameText.getSize();
        userNameText.setBounds(160 + in.left, 155 + in.top,
                size.width, size.height);

        size = userPassword.getSize();
        userPassword.setBounds(30 + in.left, 200 + in.top,
                size.width + 50, size.height + 20);

        size = userPasswordText.getSize();
        userPasswordText.setBounds(160 + in.left, 205 + in.top,
                size.width , size.height );

        size = showButton.getSize();
        showButton.setBounds(130 + in.left, 250 + in.top,
                size.width+20 , size.height + 20);

        size = show.getSize();
        show.setBounds(155 + in.left, 249 + in.top,
                size.width , size.height );

        size = view.getSize();
        view.setBounds(40 + in.left, 285 + in.top,
                size.width , size.height );

        size = reset.getSize();
        reset.setBounds(180 + in.left, 285 + in.top,
                size.width , size.height );

        size = home.getSize();
        home.setBounds(120 + in.left, 360 + in.top,
                size.width , size.height );


        Insets sets = accessPage.getInsets();
        accessPage.setSize(350 + sets.left + sets.right,
                500 + sets.top + sets.bottom);
        accessPage.setVisible(true);
    }
}
