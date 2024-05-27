package chatbot;

import java.awt.BorderLayout;
import java.awt.Color;
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

  private class MessageBox extends JPanel {

    private String author, text;
    private JLabel authLabel;
    private JTextArea textLabel;

    private void render() {
      this.authLabel = new JLabel(author);
      this.textLabel = new JTextArea(text);
      this.textLabel.setLineWrap(true);
      this.textLabel.setEditable(false);
      this.textLabel.setBackground(new Color(255, 255, 255, 150));

      this.authLabel.setBorder(new EmptyBorder(0, 5, 5, 0));
      this.textLabel.setBorder(new EmptyBorder(2, 5, 5, 0));
      this.setBorder(new EmptyBorder(10, 10, 0, 0));

      // this.set
      this.setLayout(new BorderLayout());
      this.add(this.authLabel, BorderLayout.NORTH);
      this.add(this.textLabel, BorderLayout.CENTER);
    }

    MessageBox(String author, String text) {
      this.author = author;
      this.text = text;
      this.render();
    }

    MessageBox() {
      this.author = "unknown";
      this.text = "...";
      this.render();
    }

    public void setColor(int color) {
      this.setBackground(new Color(color));
    }

    public String getAuthor() {
      return author;
    }

    public void setAuthor(String author) {
      this.author = author;
      this.authLabel.setText(author);
    }

    public String getText() {
      return text;
    }

    public void setText(String text) {
      this.text = text;
      this.textLabel.setText(text);
    }
  }

  private class UserMessageBox extends MessageBox {

    private void paint() {
      this.setColor(0xE7F2F8);
    }

    UserMessageBox(String author, String text) {
      super(author, text);
      this.paint();
    }

    UserMessageBox() {
      super();
      this.paint();
    }
  }

  private class BotMessageBox extends MessageBox {

    private void paint() {
      this.setColor(0xF0F0F0);
    }

    BotMessageBox(String author, String text) {
      super(author, text);
      this.paint();
    }

    BotMessageBox() {
      super();
      this.paint();
    }
  }

  private class InputBox extends JPanel {

    public JTextArea textArea;
    public JButton button;

    public void handlePrompt() {
      String text = this.textArea.getText();
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
