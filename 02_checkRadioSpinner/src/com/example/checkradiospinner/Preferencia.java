package com.example.checkradiospinner;

public class Preferencia {
	int id, faixa;
	String nome;

	public Preferencia(int id, String nome, int faixa) {
		this.id = id;
		this.nome = nome;
		this.faixa = faixa;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getFaixa() {
		return faixa;
	}
	public void setFaixa(int faixa) {
		this.faixa = faixa;
	}
}
