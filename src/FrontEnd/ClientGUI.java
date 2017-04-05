package FrontEnd;

import Library.BackEnd;
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

    //The current view that will display the available options at the left
    private View currentView;

    public ClientGUI(Integer _clientGuiID){

        this.clientGuiID = _clientGuiID;
        this.currentView = View.findView(0);

        Button btn = new Button();
        Button btn1 = new Button();
        Button btn2 = new Button();

        btn.setText("Submit");
        btn1.setText("Undo");
        btn2.setText("Redo");

        txtResponse.setPrefWidth(550);
        //txtResponse.setDisable(true);

        txtHeader.setText(this.currentView.printUI());

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
                Response response = Exchange.interpret(text,this.currentView);
                if(!response.isEndResponse()){
                    // Only if there is a view attached to the response we assign a new view, otherwise just loop with
                    // the same view as before, so that if there is a problem with the response just loop again
                    if(response.getResponseView() != null ){
                        this.currentView = response.getResponseView();
                        txtHeader.setText(this.currentView.printUI());
                    }else if(BackEnd.isDebugMode()){
                        txtResponse.appendText("\nThe view specified for the back end method was not found");
                    }
                }else{
                    System.exit(0);
                }
                txtResponse.appendText("\n"+response.getResponseMessage());
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

    public void changeResponse(String text){
        txtResponse.setText(text);
    }

    public Integer returnID() {
        return clientGuiID;
    }

    public void login(){

    }
}
