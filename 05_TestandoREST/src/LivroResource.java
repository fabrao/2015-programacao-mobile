import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("livros")
public class LivroResource {

	// implementar operacoes GET, DELETE, POST e PUT

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Livro> getTodosLivros() {
		LivroDAO dao = new LivroDAO();
		return dao.getLivros();
	}

	@Path("{idLivro}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Livro getLivroById(@PathParam("idLivro") long id) {
		LivroDAO dao = new LivroDAO();
		return dao.getLivro(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Livro addLivro(Livro livro) {
		LivroDAO dao = new LivroDAO();
		dao.salvar(livro);
		return livro;
	}

	@Path("{idLivro}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Livro atualizar(@PathParam("idLivro") long id, Livro livro) {
		LivroDAO dao = new LivroDAO();
		return dao.atualizar(id, livro);
	}

	@Path("{idLivro}")
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public String apagar(@PathParam("idLivro") long id) {
		LivroDAO dao = new LivroDAO();
		return dao.apagar(id);
	}
}