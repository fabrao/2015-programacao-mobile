import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("mensagem")
public class MensagemResource {
	// http://localhost:3000/mensagem/xml?de=andre&para=alunos&conteudo=estamos
	@Path("xml")
	@GET
	@Produces(MediaType.TEXT_XML)
	public Mensagem getMensagem(@QueryParam("de") String de,
			@QueryParam("para") String para,@QueryParam("conteudo") String conteudo) {
		Mensagem m = new Mensagem();
		m.setId(System.currentTimeMillis());
		m.setDe(de);
		m.setPara(para);
		m.setConteudo(conteudo);
		return m;
	}

	// http://localhost:3000/mensagem/json?de=andre&para=alunos&conteudo=estamos
	@Path("json")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Mensagem getMensagemJson(@QueryParam("de") String de,
			@QueryParam("para") String para,@QueryParam("conteudo") String conteudo) {
		return getMensagem(de,para,conteudo);
	}
}