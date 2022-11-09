import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HomePage {
    public HomePage(ArrayList<String> userList){
        JFrame homePage = new JFrame("Main Page");
        homePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane=homePage.getContentPane();

        pane.setLayout(null);

        JLabel welcome=new JLabel("Welcome to MessageBox");

        JButton accessButton = new JButton("Access");
        accessButton.setSize(120,40);
        accessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AccessPage access=new AccessPage(userList);
                homePage.setVisible(false);
            }
        });



        JButton leaveAMessageButton = new JButton("Leave a message");
        leaveAMessageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegisterPage register=new RegisterPage(userList);
                homePage.setVisible(false);
            }
        });
        welcome.setFont(new Font("Calibri", Font.BOLD, 50));

        pane.add(welcome);
        pane.add(accessButton);
        pane.add(leaveAMessageButton);

        Insets insets = pane.getInsets();

        Dimension size =welcome.getPreferredSize();
        welcome.setBounds(30 + insets.left, 5 + insets.top,
                size.width, size.height);

        size = accessButton.getSize();
        accessButton.setBounds(230 + insets.left, 150 + insets.top,
                size.width, size.height);

        size = leaveAMessageButton.getPreferredSize();
        leaveAMessageButton.setBounds(200 + insets.left, 200 + insets.top,
                size.width + 50, size.height + 20);

        //Size and display the window.
        Insets in = homePage.getInsets();
        homePage.setSize(600 + in.left + in.right,
                400 + in.top + in.bottom);
        homePage.setVisible(true);
    }

}
