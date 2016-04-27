package mural;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("mural")
public class MuralResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Mural> getAll() {
		MuralXMLDAO dao = new MuralXMLDAO();
		return dao.getAll();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Mural add(Mural mural) {
		MuralXMLDAO dao = new MuralXMLDAO();
		dao.save(mural);
		return mural;
	}
	
	@GET
	@Path("{nomemural}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Mensagem> getMensagens(@PathParam("nomemural") String mural) {
		MuralXMLDAO dao = new MuralXMLDAO();
		
		return dao.getMensagensBy(mural);
	}
	
	@POST
	@Path("{nomemural}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Mensagem addMensagem(@PathParam("nomemural") String nomemural, 
			Mensagem mensagem) {
		MuralXMLDAO dao = new MuralXMLDAO();
		dao.save(nomemural, mensagem);
		return null;
	}
	
	
	
}
