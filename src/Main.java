/**
 * Created by Kevin on 3/18/2017.
 * The main class containing the main loop that creates an exchange and awaits for a Response.
 *
 *
 */
import FrontEnd.*;
import Library.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import FrontEnd.Exchange;
import FrontEnd.Response;
import FrontEnd.View;
import Library.*;
import javafx.stage.WindowEvent;

import java.util.ArrayList;

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

        menuFile.getItems().add(newClient);

        menuBar.getMenus().addAll(menuFile);

        TabPane tabPane = new TabPane();
        tab.setText("Client #"+(tabPane.getTabs().size()+1));
        tabPane.getTabs().add(tab);

        BorderPane pane = new BorderPane();

        pane.setTop(menuBar);
        pane.setCenter(tabPane);

        ClientGUI cl1 = new ClientGUI(tabPane.getTabs().size(), clients);

        cl1.changeHeader("==================================================\nWelcome to the LBMS Application\nPlease connect to the library network with the following command:\n\"connect;\"\n==================================================");

        BorderPane bodyPane = cl1.returnClientGUI();
        tab.setContent(bodyPane);
        tab.setClosable(false);

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

                ClientGUI client = new ClientGUI(tabPane.getTabs().size(), clients);

                BorderPane newBody = client.returnClientGUI();

                client.changeHeader("========================================\nWelcome to the LBMS Application\nPlease connect to the library network with the following command:\n\"connect;\"\n========================================");
                tab.setContent(newBody);

                tab.setOnCloseRequest(ev -> {clients.disconnectClient(client);});
            }
        });

        primaryStage.setOnHiding(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        BackEnd.exit(new ArrayList<>());
                        System.exit(0);
                    }
                });
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
        new Visits();
        new Visitors();
        new Users();
        new Transactions();
        new Time();
        new Purchases();

        BackEnd.setDebugMode(true);
//        MainLoop();
        launch(args);
    }
}
