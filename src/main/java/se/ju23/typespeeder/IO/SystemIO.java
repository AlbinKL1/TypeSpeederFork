package se.ju23.typespeeder.IO;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Component
@ConditionalOnProperty(name = "java.awt.headless", havingValue = "false")
public class SystemIO implements IO {
    private JFrame window;
    private JTextArea text;
    private JTextField inString;
    private JButton go;
    private JPanel sPanel;
    private BlockingQueue<String> mq;

    public SystemIO(){

        window = new JFrame("TypeSpeeder");
        window.getContentPane().setBackground(Color.BLACK);
        window.setLayout(new BorderLayout());

        text = new JTextArea();
        text.setEditable(false);
        text.setBackground(new Color(0, 0, 0));
        text.setForeground(Color.GREEN.darker());
        text.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
        window.add(new JScrollPane(text), BorderLayout.CENTER);

        sPanel = new JPanel();
        sPanel.setLayout(new BorderLayout());
        sPanel.setBackground(new Color(0, 0, 0));

        inString = new JTextField();
        inString.setFont(new Font("Sansserif", Font.BOLD, 18));
        inString.setForeground(Color.GREEN.darker());
        inString.setBackground(Color.BLACK);
        inString.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        inString.requestFocusInWindow();

        go = new JButton("Send");
        go.setForeground(Color.GREEN.darker());
        go.setBackground(Color.BLACK);

        mq = new ArrayBlockingQueue<>(100);
        ActionListener goAction = new GoListener();
        go.addActionListener(goAction);
        inString.registerKeyboardAction(goAction, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        sPanel.add(inString, BorderLayout.CENTER);
        sPanel.add(go, BorderLayout.EAST);

        window.add(sPanel, BorderLayout.SOUTH);
        window.setSize(560, 800);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationByPlatform(true);
        window.setVisible(true);

    }



    private class GoListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            try {
                mq.put(inString.getText());
                inString.setText("");
                inString.requestFocusInWindow();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }


    public boolean yesNo(String prompt) {
        int answer = JOptionPane.showConfirmDialog(null, prompt);
        return answer == JOptionPane.YES_OPTION;
    }

    public String getString(){

        try {
            return mq.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "Should not happen";
        }
    }
    public void addString(String s){
        text.append(s);
    }

    public void clear(){
        text.setText("");
    }

    public void exit() {
        window.dispose();
        System.exit(0);
    }
}
