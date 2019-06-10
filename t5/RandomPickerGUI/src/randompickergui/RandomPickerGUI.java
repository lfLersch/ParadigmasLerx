/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randompickergui;

import java.awt.Desktop;
import static java.awt.SystemColor.desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JButton;

// Exemplo em JavaFX com tratamento de evento associado a um objeto da classe Button
// Ver mais sobre classes anonimas em:
// https://docs.oracle.com/javase/tutorial/java/javaOO/anonymousclasses.html
public class RandomPickerGUI extends Application {

    private Desktop desktop = Desktop.getDesktop();
    Boolean fNext;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        fNext = false;
        Random r = new Random();

        Menu mFile = new Menu("File");
        Menu mHelp = new Menu("Help");

        MenuItem miOpen = new MenuItem("Open");
        MenuItem miExit = new MenuItem("Exit");
        MenuItem miAbout = new MenuItem("About");

        TextArea textArea = new TextArea();

        FileChooser fileChooser = new FileChooser();
        List<String> lista = new ArrayList<>();

        mFile.getItems().addAll(miOpen, miExit);
        mHelp.getItems().add(miAbout);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(mFile, mHelp);

        VBox vb = new VBox();
        vb.setSpacing(10);
        vb.setAlignment(Pos.TOP_CENTER);
        Label lDraft = new Label("");
        Button bNext = new Button("Next");
        Button bShuffle = new Button("Shuffle");

        miExit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        miOpen.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                File file = fileChooser.showOpenDialog(stage);
                if (file != null) {
                    try {
                        FileReader arq = new FileReader(file.getAbsolutePath());
                        BufferedReader lerArq = new BufferedReader(arq);
                        String linha = lerArq.readLine();
                        while (linha != null) {
                            textArea.appendText(linha + "\n");
                            linha = lerArq.readLine();
                        }
                        textArea.deletePreviousChar();
                        arq.close();
                    } catch (IOException e) {
                        System.err.printf("Erro na abertura do arquivo: %s.\n",
                                e.getMessage());
                    }
                }

            }
        });

        bNext.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if(fNext){
                    if(lista.size()>0){
                        int i = r.nextInt(lista.size());
                        lDraft.setText(lista.get(i));
                        lista.remove(i); 
                    }else{
                        fNext = false;
                        lDraft.setText("");
                    }
                }
            }
        });
        
        bShuffle.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                String linha = textArea.getText();
                String vLista[] = linha.split("\n");
                for(int i = 0; i < vLista.length; i++){
                    lista.add(vLista[i]);
                }
                if(lista.size() > 0){
                    Collections.shuffle(lista);
                    fNext = true;
                }
            }
        });

        vb.getChildren().addAll(menuBar, textArea, lDraft, bNext,bShuffle);
        stage.setScene(new Scene(vb, 500, 500));
        stage.show();
    }

    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(FileChooser.class.getName()).log(
                    Level.SEVERE, null, ex
            );
        }
    }
}
