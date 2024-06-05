package chatbot;

import chatbot.util.API_Handler;
import chatbot.util.gui.BotMessageBox;
import chatbot.util.gui.UserMessageBox;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame {

  private class InputBox extends JPanel {

    public JTextArea textArea;
    public JButton button;

    public void handlePrompt() {
      String text = this.textArea.getText().trim();
      this.textArea.setText("");
      if (text == null || text.length() == 0) return;

      // System.out.println(text);
      GUI.this.addUserMessage(text);

      new Thread(
        new Runnable() {
          public void run() {
            String res = GUI.this.apiHandler.sendRequest(text);

            // System.out.println(res);
            GUI.this.addBotMessage(res);
          }
        }
      )
        .start();
    }

    InputBox() {
      this.textArea = new JTextArea(5, 9);
      this.button = new JButton("🚀");
      JLabel jl = new JLabel("Press CTRL + ENTER to send. ll");

      jl.setFont(new Font("Comic Sans MS", Font.ITALIC, 8));

      this.button.setToolTipText("Hit CTRL + ENTER to submit the prompt.");
      this.textArea.setToolTipText("Hit CTRL + ENTER to submit the prompt.");

      this.textArea.addKeyListener(
          new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
              if (e.isControlDown() && e.getKeyCode() == 10) {
                // System.out.println("Select All");
                handlePrompt();
              }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
          }
        );

      this.button.addActionListener(
          new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              handlePrompt();
            }
          }
        );

      this.setLayout(new BorderLayout());
      this.add(jl, BorderLayout.NORTH);
      this.add(new JScrollPane(textArea), BorderLayout.CENTER);
      this.add(button, BorderLayout.EAST);
    }
  }

  public void addUserMessage(String msg) {
    // System.out.println(msg);
    this.chatPanel.add(new UserMessageBox("User", msg));
    this.chatPanel.revalidate();
  }

  public void addBotMessage(String msg) {
    // System.out.println(msg);
    this.chatPanel.add(new BotMessageBox("GPT", msg));
    this.chatPanel.revalidate();
  }

  public GUI() {
    this.chatPanel = new JPanel();
    this.inputBox = new InputBox();
    this.apiHandler = new API_Handler();

    // dev: Vertical Orientation for chats
    this.chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
    // this.chatPanel.add(new UserMessageBox());
    // this.chatPanel.add(new BotMessageBox());
    // this.chatPanel.add(new UserMessageBox());
    // this.chatPanel.add(new BotMessageBox());
    // this.chatPanel.add(new UserMessageBox());
    // this.chatPanel.add(new BotMessageBox());

    this.add(new JScrollPane(chatPanel), BorderLayout.CENTER);
    this.add(inputBox, BorderLayout.SOUTH);

    // Default setup for JFrame
    this.setTitle("Chat Bot 🤖");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(720, 480);
    this.setVisible(true);
  }

  // Variables
  private JPanel chatPanel;
  private InputBox inputBox;
  private API_Handler apiHandler;
}
