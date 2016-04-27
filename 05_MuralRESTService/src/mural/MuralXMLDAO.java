package mural;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class MuralXMLDAO {
	private static final String XML_FILE = "./muralstore.xml";
	private MuralStore muralStore;
	
	public MuralXMLDAO() {
		
		try {
			JAXBContext context = JAXBContext.newInstance(MuralStore.class);
			Unmarshaller um = context.createUnmarshaller();
			muralStore = (MuralStore) um.unmarshal(new FileReader(XML_FILE));
			
		} catch (Exception e) {
			//empty store
			muralStore = new MuralStore();
			muralStore.setName("store");
			muralStore.setMurais( new ArrayList<Mural>() );
		}
	}
	
	
	private void saveXML() {
		try {
			JAXBContext context = JAXBContext.newInstance(MuralStore.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(muralStore, new File(XML_FILE));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public List<Mural> getAll() {
		return muralStore.getMurais();
	}


	public void save(Mural mural) {
		muralStore.getMurais().add(mural);
		
		saveXML();
	}


	public List<Mensagem> getMensagensBy(String nome) {
		
		for(Mural m : muralStore.getMurais()) {
			if(m.getNome().equals(nome)) {
				return m.getMensagens();
			}
		}
		
		return new ArrayList<Mensagem>();
	}


	public void save(String nomemural, Mensagem mensagem) {

		for(Mural m : muralStore.getMurais()) {
			if(m.getNome().equals(nomemural)) {
				//achei esse mural
				if(m.getMensagens() == null)
					m.setMensagens(new ArrayList<Mensagem>());
				
				m.getMensagens().add(mensagem);
				saveXML();
			}
		}
		
		
	}	
}
