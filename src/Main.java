/**
 * Created by Kevin on 3/18/2017.
 * The main class containing the main loop that creates an exchange and awaits for a Response.
 *
 *
 */
import FrontEnd.*;
import Library.BackEnd;
import Library.Books;
import Library.Transactions;
import Library.Visitors;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application{

    Tab tab = new Tab();

    @Override
    public void start(Stage primaryStage) {

        GUIClients clients = new GUIClients();

        primaryStage.setTitle("Library Books Management System(LBMS)");

        Group root = new Group();
        Scene scene = new Scene(root,1200,800);

        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        MenuItem newClient = new MenuItem("New Client");
        MenuItem appExit = new MenuItem("Exit");

        menuFile.getItems().add(newClient);
        menuFile.getItems().add(appExit);

        menuBar.getMenus().addAll(menuFile);

        TabPane tabPane = new TabPane();
        tab.setText("     ");
        //tab.setContent(new Rectangle(200,200, Color.LIGHTSTEELBLUE));
        tabPane.getTabs().add(tab);

        BorderPane pane = new BorderPane();

        //pane.setTop(menuBar);
        pane.setTop(menuBar);
        pane.setCenter(tabPane);

        ClientGUI cl1 = new ClientGUI(tabPane.getTabs().size());
        clients.connectClient(cl1);


        BorderPane bodyPane = cl1.returnClientGUI();
        tab.setContent(bodyPane);
        tab.setClosable(false);


        //pane.setBottom(bodyPane);

        pane.prefHeightProperty().bind(scene.heightProperty());
        pane.prefWidthProperty().bind(scene.widthProperty());

        root.getChildren().add(pane);

        //scene.getStylesheets().add(getClass().getResource("base.css").toExternalForm());


        newClient.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final Tab tab = new Tab("Client #" + (tabPane.getTabs().size() + 1));
                tabPane.getTabs().add(tab);
                tabPane.getSelectionModel().select(tab);

                ClientGUI client = new ClientGUI(tabPane.getTabs().size());
                clients.connectClient(client);

                BorderPane newBody = client.returnClientGUI();
                //hbox.setAlignment(Pos.CENTER);
                tab.setContent(newBody);
                tab.setOnCloseRequest(ev -> {clients.disconnectClient(client);});

            }
        });

        appExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void setTabText(String txt){
        tab.setText(txt);
    }


    static public void main(String[] args){
        View.initMenuOptions();
        new Books();
        new Visitors();
        new Transactions();

        BackEnd.setDebugMode(true);
//        MainLoop();
        launch(args);
    }
}
