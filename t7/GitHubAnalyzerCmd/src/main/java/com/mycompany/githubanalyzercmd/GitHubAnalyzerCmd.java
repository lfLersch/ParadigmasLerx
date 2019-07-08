/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.githubanalyzercmd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;

/**
 *
 * @author luizf
 */
public class GitHubAnalyzerCmd {

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Repositorio> listaRepositorio = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        System.out.println("Escreva o nome do arquivo com as url's: ");
        String sFile = s.next();

        FileReader file = new FileReader(sFile);

        if (file != null) {
            try {
                BufferedReader lerArq = new BufferedReader(file);
                String linha = lerArq.readLine();
                while (linha != null) {
                    Repositorio r = new Repositorio(linha);
                    listaRepositorio.add(r);
                    linha = lerArq.readLine();
                }
            } catch (IOException ex) {
                Logger.getLogger(GitHubAnalyzerCmd.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for (Repositorio lista : listaRepositorio) {
            System.out.println("NOME: "+lista.getNome());
            lista.run();
        }
        Repositorio rMaior = null;
        Repositorio rMenor = null;
        int tMaior = -1;
        int tMenor = Integer.MAX_VALUE;
        Repositorio maisNovo = listaRepositorio.get(0);
        Repositorio maisVelho = listaRepositorio.get(0);
        for (Repositorio lista : listaRepositorio) {
            if(lista.getNumeroCommit() > tMaior){
                rMaior = lista;
                tMaior = lista.getNumeroCommit();
            }else if(lista.getNumeroCommit() < tMenor){
                rMenor = lista;
                tMenor = lista.getNumeroCommit();
            }
            if(lista.getcNovo().getData().compareTo(maisNovo.getcNovo().getData()) > 0){
                maisNovo = lista;
            }else if(lista.getcVelho().getData().compareTo(maisVelho.getcVelho().getData()) < 0){
                maisVelho = lista;
            }
        }
        System.out.println("MENOR: "+rMenor.getNome()+"\nMAIOR: "+rMaior.getNome());
        System.out.println("COMMIT MAIS VELHO: "+ maisVelho.getNome()+"\n    DATA: "+maisVelho.getcVelho().getData()+"\n    MENSAGEM: "+ maisVelho.getcVelho().getMensagem());
        System.out.println("COMMIT MAIS NOVO: "+ maisNovo.getNome()+"\n    DATA: "+maisNovo.getcNovo().getData()+"\n    MENSAGEM: "+ maisNovo.getcNovo().getMensagem());
    }

}
