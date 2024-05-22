package chatbot;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GUI extends JFrame {

  private class MessageBox extends JPanel {

    private String author, text;
    private JLabel authLabel, textLabel;

    private void render() {
      this.authLabel = new JLabel(author);
      this.textLabel = new JLabel(text);

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

  private class InputBox extends JPanel {

    public JTextArea textArea;
    public JButton button;

    InputBox() {
      textArea = new JTextArea(5, 9);
      button = new JButton("🚀");

      this.setLayout(new BorderLayout());
      this.add(new JScrollPane(textArea), BorderLayout.CENTER);
      this.add(button, BorderLayout.EAST);
    }
  }

  public GUI() {
    JPanel chatPanel = new JPanel();
    InputBox inputBox = new InputBox();

    // dev: testing
    chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
    chatPanel.add(new MessageBox());
    chatPanel.add(new MessageBox());
    chatPanel.add(new MessageBox());
    chatPanel.add(new MessageBox());
    chatPanel.add(new MessageBox());
    chatPanel.add(new MessageBox());
    chatPanel.add(new MessageBox());
    chatPanel.add(new MessageBox());
    chatPanel.add(new MessageBox());
    chatPanel.add(new MessageBox());
    chatPanel.add(new MessageBox());
    chatPanel.add(new MessageBox());
    chatPanel.add(new MessageBox());
    chatPanel.add(new MessageBox());
    chatPanel.add(new MessageBox());
    chatPanel.add(new MessageBox());
    chatPanel.add(new MessageBox());
    chatPanel.add(new MessageBox());
    chatPanel.add(new MessageBox());

    this.add(new JScrollPane(chatPanel), BorderLayout.CENTER);
    this.add(inputBox, BorderLayout.SOUTH);

    // Default setup for JFrame
    this.setTitle("Chat Bot 🤖");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(720, 480);
    this.setVisible(true);
  }
}
