/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exercicio_Pedido;

import java.sql.Date;

/**
 *
 * @author RafaelaEmília
 */
public class Pedido {
    
    private Long id;
    private Long pedido;
    private String dono;
    private double valor;
    private String nome;
    private Date atualizacao;

    /**
     * @return the id
     */
    
    
    public Pedido(Long id, Long pedido, String dono, double valor, String nome, Date atualizacao) {
        this.id = id;
        this.pedido = pedido;
        this.dono = dono;
        this.valor = valor;
        this.nome = nome;
        this.atualizacao = atualizacao;
    }

    public Pedido() {
    }

    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the pedido
     */
    public Long getPedido() {
        return pedido;
    }

    /**
     * @param pedido the pedido to set
     */
    public void setPedido(Long pedido) {
        this.pedido = pedido;
    }

    /**
     * @return the dono
     */
    public String getDono() {
        return dono;
    }

    /**
     * @param dono the dono to set
     */
    public void setDono(String dono) {
        this.dono = dono;
    }

    /**
     * @return the valor
     */
    public double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the atualizacao
     */
    public Date getAtualizacao() {
        return atualizacao;
    }

    /**
     * @param atualizacao the atualizacao to set
     */
    public void setAtualizacao(Date atualizacao) {
        this.atualizacao = atualizacao;
    }
    
    
    
    
}
