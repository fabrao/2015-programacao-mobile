import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("hello")
public class HelloResource {
	
	@GET // http://localhost:3000/hello
	@Produces(MediaType.TEXT_PLAIN)
	public String getHello() {
		return "hello world!";
	}
	
	@GET // http://localhost:3000/hello/pt-br
	@Path("pt-br")
	@Produces(MediaType.TEXT_PLAIN)
	public String getOla() {
		return "ola mundo!";
	}
	
	@GET // http://localhost:3000/hello/de
	@Path("de")
	@Produces(MediaType.TEXT_PLAIN)
	public String getHallo(){
	return "Hallo Wilt!";
	}
	
	@GET // http://localhost:3000/hello/jp
	@Path("jp")
	@Produces(MediaType.TEXT_PLAIN)
	public String getKonitiwa(){
		return "Konitiwa!";
	}
}