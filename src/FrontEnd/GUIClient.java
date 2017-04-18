package FrontEnd;

import Library.BackEnd;
import Library.User;
import Library.Database.Users;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;

/**
 * Created by Anthony Perez on 4/3/17.
 */
public class GUIClient {

    BorderPane layoutPane = new BorderPane();
    TextArea txtResponse = new TextArea();
    TextArea txtHeader = new TextArea();
    BorderPane bottomPane = new BorderPane();
    TextArea txtSubmit = new TextArea();

    Number clientGuiID = 0;
    Boolean clientLogin = false;
    Boolean clientConnection = false;
    static Boolean isLocal;

    GUIClients clients;
    View currentView;
    //User class object for login purposes

    public GUIClient(Number _clientGuiID, GUIClients _clients){
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
        txtResponse.setStyle("-fx-font-family: monospace");
        txtResponse.setEditable(false);
//        txtResponse.setFont(Font.font(10.75));

        txtHeader.setText(this.currentView.printUI());

        txtHeader.setPrefWidth(250);
        txtHeader.setEditable(false);
        txtHeader.setWrapText(true);
        txtHeader.setStyle("-fx-font-weight:  bold");

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

        btn.prefWidthProperty().bind(stack.heightProperty());
        btn1.prefWidthProperty().bind(stack.heightProperty());
        btn2.prefWidthProperty().bind(stack.widthProperty());

        stack.setPrefHeight(100);

        txtSubmit.setPrefHeight(100);

        bottomPane.prefHeight(100);

        txtSubmit.requestFocus();

        bottomPane.setCenter(txtSubmit);
        bottomPane.setRight(stack);

        layoutPane.setCenter(txtResponse);
        layoutPane.setTop(txtHeader);
        layoutPane.setBottom(bottomPane);

        txtSubmit.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER){
                this.handleSubmission(getTextFromInput());
                event.consume();
            }
        });




        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleSubmission(getTextFromInput());
            }
        });
    }

    public BorderPane returnClientGUI(){
        return layoutPane;
    }

    /**
     * Obtains the String typed in the input and checks that it is valid
     * @return The string given bythe user
     */
    private String getTextFromInput(){
        String text = txtSubmit.getText().trim();
        return (text.length() <= 0 || !text.contains(";")) ? "\0" : text;
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

    public Number returnID() {
        return clientGuiID;
    }

    public Boolean login(String _user, String _pass){
        for(User user: Users.getMap().values()){
            System.out.println(user.getVisitorID()+"-"+user.getUsername()+"-"+user.getPassword());
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

    public void handleSubmission(String text){

        if(!clientConnection && !clientLogin){
            switch (text){
                case "connect;":
                    this.addResponse(text+"\n");
                    this.addResponse("Sucessful connection to LBMS network!\n");
                    setConnectionStatus(clients.connectClient(this));
                    this.changeHeader("========================================\nWelcome to the LBMS Application\nPlease log in to the library network with the following command:\n\"login,'username','password';\"\n========================================");
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
//                    if(text.substring(0,5).equals("login")){
//                        this.addResponse(text+"\n");
//                        String txt = text.replace(";","");
//                        String[] loginData = txt.split(",");
//                        this.setLoginStatus(login(loginData[1],loginData[2]));
//                        this.addResponse(clientLogin ? "Successful Login!" : "Login Failed. Please Try again.");
//                    }
                    if(text.contains("login,true;")){
                        //HAKZ
                        this.setLoginStatus(true);
                        this.addResponse("Successful Login!\n");
                        this.changeHeader("===========================\nWelcome to the LBMS Application\nPlease select your service location (local or google) using the following command:\n\"service,'info-service';\"\n===========================");
                    }
                    else{
                        this.addResponse(text+"\n");
                        this.addResponse("Invalid command.\n");
                    }
                    break;
            }
        }

        else if(clientConnection && clientLogin && isLocal == null){
            if(text.equals("service,local;")){
                isLocal = true;
                this.addResponse("Service location has been set to: Local");
                this.changeHeader(this.currentView.printUI());
            }
            else if(text.equals("service,google;")){
                isLocal = false;
                this.addResponse("Service location has been set to: Google");
                this.changeHeader(this.currentView.printUI());
            }
        }

        else if(clientConnection && clientLogin && isLocal != null){
            Response response = Exchange.interpret(text,this.currentView);
            if(text.equals("logout;")){
                this.addResponse(text+"\n");
                this.setLoginStatus(false);
                this.addResponse("Successful Logout!");
                this.changeHeader("===========================\nWelcome to the LBMS Application\nPlease log in to the library network with the following command:\n\"login,'username','password';\"\n===========================");
            }
            else if(!response.isEndResponse()){
                // Only if there is a view attached to the response we assign a new view, otherwise just loop with
                // the same view as before, so that if there is a problem with the response just loop again
                if(response.getResponseView() != null ){
                    this.currentView = response.getResponseView();

                    this.changeHeader(this.currentView.printUI());
                }else if(BackEnd.isDebugMode()){
                    txtResponse.appendText("\nThe view specified for the back end method was not found");
                }
            }
            else{
                System.exit(0);
            }
            txtResponse.appendText("\n"+response.getResponseMessage());
        }
        txtSubmit.clear();
    }
    public static boolean getIsLocal(){
        return isLocal;
    }

    public Number getClientGuiID(){
        return clientGuiID;
    }
}