package chatbot.util.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class MessageBox extends JPanel {

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
    this.setBorder(new EmptyBorder(10, 4, 0, 0));

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
