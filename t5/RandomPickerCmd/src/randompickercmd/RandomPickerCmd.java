/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randompickercmd;
import static java.awt.SystemColor.desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
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
import javafx.stage.Stage;


// Exemplo em JavaFX com tratamento de evento associado a um objeto da classe Button
// Ver mais sobre classes anonimas em:
// https://docs.oracle.com/javase/tutorial/java/javaOO/anonymousclasses.html


public class RandomPickerCmd {
   public static void main(String[] args) throws IOException {
       int r=3;
      RandomList random = new RandomList();
      Scanner s = new Scanner(System.in);
       

      FileReader arq = new FileReader("texto.txt");
      random.readArq(arq);
      while(r > 1 || r < 0){
          System.out.println("Para embaralhar: \nOnline=0\nOffline=1");
          r = s.nextInt();
      }
      if(r==0){
          random.shuffleOnline();
      }else{
          random.shuffleOffline();
      }
            
        while (random.listaSize() > 0) {
            System.out.println(random.getStrings());
            System.in.read();
        }

        System.out.println("Empty List");
    }
}
