import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("categoria")
public class CategoriaResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Categoria> getTodasCategorias() {
		CategoriaDAO dao = new CategoriaDAO();
		return dao.getCategorias();
	}

	
	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Categoria getCategoriaById(@PathParam("id") int id) {
		CategoriaDAO dao = new CategoriaDAO();
		if(dao.getCategoria(id)==null){
			return dao.getCategoria(0);
		}else{
			return dao.getCategoria(id);
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Categoria addCategoria(Categoria categoria) {
		CategoriaDAO dao = new CategoriaDAO();
		dao.salvar(categoria);
		return categoria;
	}
	
}
