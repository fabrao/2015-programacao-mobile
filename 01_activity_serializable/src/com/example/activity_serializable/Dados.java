package com.example.activity_serializable;

import java.io.Serializable;

public class Dados implements Serializable {
	private static final long serialVersionUID = -6708264038669036552L;

	String nome;
	int idade;
	double peso, altura;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public double getAltura() {
		return altura;
	}
	public void setAltura(double altura) {
		this.altura = altura;
	}
	
	public double getIMC() {
		return peso / (altura * altura);
	}
}
