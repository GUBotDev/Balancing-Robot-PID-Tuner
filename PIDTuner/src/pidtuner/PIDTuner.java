/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidtuner;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Rollie
 */
public class PIDTuner extends Application {
    static boolean up = false, down = false, left = false, right = false, send = false;
    private static Controller controller;
    String ip = "192.168.0.51";
    int port = 6790;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        GridPane gPane = new GridPane();
        
        TextField pT = new TextField();
        TextField dT = new TextField();
        TextField pTS = new TextField();
        TextField iTS = new TextField();
        TextField angleOffset = new TextField();
        Button send = new Button();
        Button conn = new Button();
        
        send.setOnMouseReleased((MouseEvent me) -> {
            try {
                controller.writePID(pT.getText(), dT.getText(),pTS.getText(), iTS.getText(), angleOffset.getText());
            }
            catch (IOException ex) {}
        });
        
        conn.setOnMouseReleased((MouseEvent me) -> {
            try {
                controller = new Controller(ip, port);
            }
            catch (IOException ex) {}
        });
        
        
        pT.setPromptText("kP");
        dT.setPromptText("kI");
        pTS.setPromptText("kPD");
        iTS.setPromptText("kDD");
        angleOffset.setPromptText("angleOffset");
        send.setText("Send");
        conn.setText("Conn");
        
        pT.setAlignment(Pos.CENTER);
        dT.setAlignment(Pos.CENTER);
        pTS.setAlignment(Pos.CENTER);
        iTS.setAlignment(Pos.CENTER);
        angleOffset.setAlignment(Pos.CENTER);
        send.setAlignment(Pos.CENTER);
        conn.setAlignment(Pos.CENTER);
        
        GridPane.setConstraints(pT, 0, 1);
        GridPane.setConstraints(dT, 0, 2);
        GridPane.setConstraints(pTS, 0, 3);
        GridPane.setConstraints(iTS, 0, 4);
        GridPane.setConstraints(angleOffset, 0, 5);
        GridPane.setConstraints(send, 0, 6);
        GridPane.setConstraints(conn, 0, 7);
        
        GridPane.setMargin(pT, new Insets(10, 10, 10, 10));
        GridPane.setMargin(dT, new Insets(10, 10, 10, 10));
        GridPane.setMargin(pTS, new Insets(10, 10, 10, 10));
        GridPane.setMargin(iTS, new Insets(10, 10, 10, 10));
        GridPane.setMargin(angleOffset, new Insets(10, 10, 10, 10));
        GridPane.setMargin(send, new Insets(10, 10, 10, 10));
        GridPane.setMargin(conn, new Insets(10, 10, 10, 10));
        
        //gPane.setPadding(new Insets(10, 10, 10, 10));
        
        gPane.getChildren().addAll(pT, dT, pTS, iTS, angleOffset, send, conn);
        
        Scene scene = new Scene(gPane, 175, 325);
        
        scene.setOnKeyReleased((KeyEvent ke) -> {
            if (ke.getCode() == KeyCode.ENTER) {
                try {
                    controller.writePID(pT.getText(), dT.getText(), pTS.getText(), iTS.getText(), angleOffset.getText());
                }
                catch (IOException ex) {}
            }
        });
        
        primaryStage.setTitle("PIDTuner");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        launch(args);
    }
}
