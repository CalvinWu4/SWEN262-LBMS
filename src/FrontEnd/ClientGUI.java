package FrontEnd;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;

/**
 * Created by Jp on 4/3/17.
 */
public class ClientGUI {
    BorderPane layoutPane = new BorderPane();
    TextArea txtResponse = new TextArea();
    TextArea txtHeader = new TextArea();
    BorderPane bottomPane = new BorderPane();
    TextArea txtSubmit = new TextArea();

    Integer clientGuiID = 0;
    Boolean clientLogin = false;

    public ClientGUI(Integer _clientGuiID){

        this.clientGuiID = _clientGuiID;

        Button btn = new Button();
        Button btn1 = new Button();
        Button btn2 = new Button();

        btn.setText("Submit");
        btn1.setText("Undo");
        btn2.setText("Redo");

        txtResponse.setPrefWidth(550);
        //txtResponse.setDisable(true);

        txtResponse.setText("Hello");

        txtHeader.setPrefWidth(250);

        ScrollPane scroll = new ScrollPane();

        scroll.setContent(txtResponse);
        scroll.setFitToHeight(true);
        scroll.setFitToWidth(true);

        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        bottomPane.setCenter(txtSubmit);

        BorderPane stack = new BorderPane();

        stack.setTop(btn);
        stack.setCenter(btn1);
        stack.setBottom(btn2);

        bottomPane.setRight(stack);

        layoutPane.setCenter(txtResponse);
        layoutPane.setLeft(txtHeader);
        layoutPane.setBottom(bottomPane);

        txtSubmit.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER){
                //type here what you want
                String text = txtSubmit.getText();

                if(text == "connect"){

                }

                txtResponse.appendText("\n"+text);

                txtSubmit.setText("");
            }
        });
    }

    public BorderPane returnClientGUI(){
        return layoutPane;
    }

    public void changeHeader(String txt){
        txtHeader.setText("");
        txtHeader.setText(txt);
    }

    public Integer returnID() {
        return clientGuiID;
    }

    public void login(){

    }
}
