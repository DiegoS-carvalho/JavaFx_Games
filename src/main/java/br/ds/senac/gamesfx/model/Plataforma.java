package br.ds.senac.gamesfx.model;

import java.time.LocalDate;

public class Plataforma {

    private  int id;
    private String  nome;
    private  String fabricante;
    private LocalDate dataPlataforma;
    private double preco;

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    //===========================================================\\

    public String getFabricante() {return fabricante;}
    public void setFabricante(String fabricante) {this.fabricante = fabricante;}
    //===========================================================\\

    public LocalDate getDataPlataforma() {return dataPlataforma;}
    public void setDataPlataforma(LocalDate dataPlataforma) {this.dataPlataforma = dataPlataforma;}
    //===========================================================\\

    public double getPreco() {return preco;}
    public void setPreco(double preco) {this.preco = preco;}
    //===========================================================\\


    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    //===========================================================\\

    public Plataforma(){}
    //===========================================================\\

    public Plataforma(int id, String nome, String fabricante, LocalDate dataplataforma, double preco){
        this.id = id;
        this.nome = nome;
        this.fabricante = fabricante;
        this.dataPlataforma = dataplataforma;
        this.preco = preco;
    }
}
