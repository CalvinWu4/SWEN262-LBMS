package FrontEnd;

import Library.BackEnd;
import Library.User;
import Library.Users;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;

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
    Boolean clientConnection = false;

    GUIClients clients;
    View currentView;
    //User class object for login purposes

    public ClientGUI(Integer _clientGuiID, GUIClients _clients){
    //The current view that will display the available options at the left

        this.clientGuiID = _clientGuiID;
        this.currentView = View.findView(0);
        this.clients = _clients;

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
        txtHeader.setWrapText(true);

        ScrollPane scroll = new ScrollPane();

        ScrollPane headerScroll = new ScrollPane();

        headerScroll.setContent(txtHeader);
        headerScroll.setFitToHeight(true);
        headerScroll.setFitToWidth(true);

        headerScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        headerScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        ScrollPane responseScroll = new ScrollPane();

        responseScroll.setContent(txtResponse);
        responseScroll.setFitToHeight(true);
        responseScroll.setFitToWidth(true);

        responseScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        responseScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);


        BorderPane stack = new BorderPane();

        stack.setTop(btn);
        stack.setCenter(btn1);
        stack.setBottom(btn2);

        bottomPane.setCenter(txtSubmit);
        bottomPane.setRight(stack);

        layoutPane.setCenter(txtResponse);
        layoutPane.setLeft(txtHeader);
        layoutPane.setBottom(bottomPane);

        txtSubmit.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER){
                //type here what you want

                String text;

                if(clientConnection){
                    text = (txtSubmit.getText().equals("")) ? "" : txtSubmit.getText().substring(4,txtSubmit.getText().length());
                }
                else{
                    text = txtSubmit.getText();
                }

                if(!clientConnection && !clientLogin){
                    switch (text){
                        case "connect;":
                            this.addResponse(text+"\n");
                            this.addResponse("Sucessful connection to LBMS network!\n");
                            setConnectionStatus(clients.connectClient(this));
                            this.changeHeader("===========================\nWelcome to the LBMS Application\nPlease log in to the library network with the following command:\n\"login,'username','password';\"\n===========================");
                            break;

                        case "disconnect;":
                            this.addResponse(text+"\n");
                            this.addResponse("You do not have a connection to the LBMS network!\n");
                            break;

                        default:
                            this.addResponse(text+"\n");
                            this.addResponse("Invalid command, please connect to Library Network.\n");
                            break;
                    }
                }

                else if(clientConnection && !clientLogin){
                    switch (text){
                        case "connect;":
                            this.addResponse(text+"\n");
                            this.addResponse("You already have a connection to LBMS network!\n");
                            break;

                        case "disconnect;":
                            this.addResponse(text+"\n");
                            this.addResponse("Sucessful disconnection to LBMS network!\n");
                            this.setConnectionStatus(clients.disconnectClient(this));
                            this.changeHeader("===========================\nWelcome to the LBMS Application\nPlease connect to the library network with the following command:\n\"connect;\"\n===========================");
                            break;

                        default:
//                            if(text.substring(0,5).equals("login")){
//                                this.addResponse(text+"\n");
//                                String[] loginData = text.split(",");
//                                this.setLoginStatus(login(loginData[1],loginData[2]));
//                                this.addResponse(clientLogin ? "Successful Login!" : "Login Failed. Please Try again.");
//                            }
                            if(text.substring(0,11).equals("login,true;")){
                                this.setLoginStatus(true);
                                this.addResponse(clientLogin ? "Successful Login!" : "Login Failed. Please Try again.");
                            }
                            else{
                                this.addResponse(text+"\n");
                                this.addResponse("Invalid command.\n");
                            }
                            break;
                    }
                }
                if(clientConnection && clientLogin){
                    Response response = Exchange.interpret(text,this.currentView);
                    if(!response.isEndResponse()){
                        // Only if there is a view attached to the response we assign a new view, otherwise just loop with
                        // the same view as before, so that if there is a problem with the response just loop again
                        if(response.getResponseView() != null ){
                            this.currentView = response.getResponseView();
                            this.changeHeader(this.currentView.printUI());
                        }else if(BackEnd.isDebugMode()){
                            txtResponse.appendText("\nThe view specified for the back end method was not found");
                        }
                    }else{
                        System.exit(0);
                    }
                    txtResponse.appendText("\n"+response.getResponseMessage());
                }


//                if(text.equals("connect;") && !clientLogin && !clientConnection){
//                    txtResponse.appendText(text+"\n");
//                    txtResponse.appendText("Sucessful connection to LBMS network!\n");
//                    setConnectionStatus(clients.connectClient(this));
//                    this.changeHeader("===========================\nWelcome to the LBMS Application\nPlease log in to the library network with the following command:\n\"login,'username','password';\"\n===========================");
//                }
//                else if(text.equals("connect;") && !clientLogin && clientConnection){
//                    txtResponse.appendText(text+"\n");
//                    txtResponse.appendText("You already have a connection to LBMS network!\n");
//                }
//
//                else if(text.equals("disconnect;") && !clientLogin && clientConnection){
//                    txtResponse.appendText(text+"\n");
//                    txtResponse.appendText("Sucessful disconnection to LBMS network!\n");
//                    setConnectionStatus(clients.disconnectClient(this));
//                    this.changeHeader("===========================\nWelcome to the LBMS Application\nPlease connect to the library network with the following command:\n\"connect;\"\n===========================");
//                }
//                else if(text.equals("disconnect;") && !clientLogin && !clientConnection){
//                    txtResponse.appendText(text+"\n");
//                    txtResponse.appendText("You do not have a connection to the LBMS network!\n");
//                }
//
//                if(clientConnection || clientLogin){
//                    Response response = Exchange.interpret(text,this.currentView);
//                    if(!response.isEndResponse()){
//                        // Only if there is a view attached to the response we assign a new view, otherwise just loop with
//                        // the same view as before, so that if there is a problem with the response just loop again
//                        if(response.getResponseView() != null ){
//                            this.currentView = response.getResponseView();
//                            txtHeader.setText(this.currentView.printUI());
//                        }else if(BackEnd.isDebugMode()){
//                            txtResponse.appendText("\nThe view specified for the back end method was not found");
//                        }
//                    }else{
//                        System.exit(0);
//                    }
//                    txtResponse.appendText("\n"+response.getResponseMessage());
//                }
//                else{
//                    txtResponse.appendText(text+"\n");
//                    txtResponse.appendText("Invalid command, please connect to Library Network.\n");
//                }
//
                txtSubmit.clear();
                if(clientConnection){
                    txtSubmit.setText(returnID()+" >>");
                    txtSubmit.positionCaret(7);
                }
                else{
                    txtSubmit.clear();
                    txtSubmit.positionCaret(0);
                }
                event.consume();

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

    public void addResponse(String text){
        txtResponse.appendText(text);
    }

    public Integer returnID() {
        return clientGuiID;
    }

    public Boolean login(String _user, String _pass){
        for(User user: Users.getMap().values()){
            if((user.getUsername().equals(_user)) && (user.getPassword().equals(_pass))){
                return true;
            }
        }
        return false;
    }

    public void setConnectionStatus(Boolean status){
        this.clientConnection = status;
    }
    public void setLoginStatus(Boolean status){
        this.clientLogin = status;
    }
}
