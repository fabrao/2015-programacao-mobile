package mural;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MuralStore {
	String name;
	List<Mural> murais;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Mural> getMurais() {
		return murais;
	}
	public void setMurais(List<Mural> murais) {
		this.murais = murais;
	}
}
