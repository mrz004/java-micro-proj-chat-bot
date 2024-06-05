package chatbot.util.gui;

public class BotMessageBox extends MessageBox {

  private void paint() {
    this.setColor(0xF0F0F0);
  }

  public BotMessageBox(String author, String text) {
    super(author, text);
    this.paint();
  }

  BotMessageBox() {
    super();
    this.paint();
  }
}
