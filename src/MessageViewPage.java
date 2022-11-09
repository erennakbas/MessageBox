import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MessageViewPage {
    public MessageViewPage(ArrayList<String> userList, String content){
        JFrame message= new JFrame("Message");
        message.setSize(500,500);
        message.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container pane=message.getContentPane();
        pane.setLayout(new FlowLayout());

        JTextArea messageArea = new JTextArea(20, 40);
        messageArea.setText(content);
        JScrollPane scroll = new JScrollPane(messageArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        JButton retrn = new JButton("Return");
        retrn.setSize(200,80);
        retrn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AccessPage a=new AccessPage(userList);
                message.setVisible(false);
            }
        });

        pane.add(scroll);
        pane.add(retrn);
        message.setVisible(true);

    }
}
