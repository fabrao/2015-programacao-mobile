package mural;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Mural {
	int id;
	String nome;
	List<Mensagem> mensagens;
	
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
	
	public List<Mensagem> getMensagens() {
		return mensagens;
	}
	
	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}
	
	
}
