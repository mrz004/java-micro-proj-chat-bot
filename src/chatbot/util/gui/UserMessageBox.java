package chatbot.util.gui;

public class UserMessageBox extends MessageBox {

  private void paint() {
    this.setColor(0xE7F2F8);
  }

  public UserMessageBox(String author, String text) {
    super(author, text);
    this.paint();
  }

  UserMessageBox() {
    super();
    this.paint();
  }
}
