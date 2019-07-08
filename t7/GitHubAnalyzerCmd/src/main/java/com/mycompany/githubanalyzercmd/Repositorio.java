/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.githubanalyzercmd;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luizf
 */
public class Repositorio extends Thread {

    private String nome;
    private int numeroCommit;
    private String mediaTamanho;
    private Commit cVelho;
    private Commit cNovo;
    private ArrayList<Commit> listaCommit;
    

    public Repositorio(String nome) {
        this.nome = nome;
        this.listaCommit = new ArrayList<>();
        this.mediaTamanho = "/";
        this.numeroCommit = 0;
        this.cNovo = null;
        this.cVelho = null;
    }

    public Commit getcVelho() {
        return cVelho;
    }

    public Commit getcNovo() {
        return cNovo;
    }
    
    

    public int getNumeroCommit() {
        return numeroCommit;
    }

    public void setNumeroCommit(int numeroCommit) {
        this.numeroCommit = numeroCommit;
    }

    public String getMediaTamanho() {
        return mediaTamanho;
    }

    public void setMediaTamanho(String mediaTamanho) {
        this.mediaTamanho = mediaTamanho;
    }
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void CalculaAtributos() {
        int media = listaCommit.get(0).getMensagem().length();
        Date velho = listaCommit.get(0).getData();
        Date novo = listaCommit.get(0).getData();
        Commit cn = listaCommit.get(0) ;
        Commit cv = listaCommit.get(0);
        Date d;
        for (int i = 1; i < listaCommit.size(); i++) {
            d = listaCommit.get(i).getData();
            media += listaCommit.get(i).getMensagem().length();
            if(d.compareTo(novo) > 0){
                novo = d;
                cn = listaCommit.get(i);
            }else if(d.compareTo(velho) < 0){
                velho = d;
                cv = listaCommit.get(i);
            }
        }
        this.cVelho = cv;
        this.cNovo = cn;
        media = media / listaCommit.size();
        this.mediaTamanho = Integer.toString(media);
        this.numeroCommit = listaCommit.size();
        System.out.println("    MEDIA DE TAMANHO DAS MENSAGENS: "+this.mediaTamanho+"\n"
                + "    NUMERO DE COMMITS: "+this.numeroCommit);
        
    }

    public int getCommits() {
        return listaCommit.size();
    }

    public Commit getCommit(int index) {
        return listaCommit.get(index);
    }

    public void addCommit(Commit commit) {
        listaCommit.add(commit);
    }

    
    @Override
    public void run() {
        try { 
            URL url = new URL(this.nome);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            //System.out.println("Response code: " + con.getResponseCode());
            
            
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            
            JsonParser parser = new JsonParser();
            JsonArray results = parser.parse(in.readLine()).getAsJsonArray();
            //System.out.println("Size: " + results.size());
            
            for (JsonElement e : results) {
                String m = (e.getAsJsonObject().get("commit").getAsJsonObject().get("message").toString());
                Date d = null;
                String data;
                try {
                    d = new SimpleDateFormat("\"yyyy-MM-dd'T'HH:mm:ss'Z'\"").parse(e.getAsJsonObject().get("commit").getAsJsonObject().get("author").getAsJsonObject().get("date").toString());
                } catch (ParseException ex) {
                    Logger.getLogger(Repositorio.class.getName()).log(Level.SEVERE, null, ex);
                }
                //System.out.print(d);
                Commit c = new Commit(nome, d);
                this.listaCommit.add(c);
            }
            CalculaAtributos();
            
            
            in.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(Repositorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Repositorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
