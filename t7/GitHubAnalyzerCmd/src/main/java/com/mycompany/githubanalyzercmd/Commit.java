/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.githubanalyzercmd;

import java.util.Date;

/**
 *
 * @author luizf
 */
public class Commit {
    private String mensagem;
    private Date data;

    public Commit(String mensagem, Date data) {
        this.mensagem = mensagem;
        this.data = data;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
            
}
