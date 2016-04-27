import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("cotacao")
public class CotacaoResource {
	// http://localhost:3000/cotacao/real/dolar
	// ou
	// http://localhost:3000/cotacao/real/euro
	@Path("{moeda1}/{moeda2}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String cotacao(@PathParam("moeda1") String m1,@PathParam("moeda2") String m2) {
		String ret = "Nao temos esta cotacao";
		if(m1.equalsIgnoreCase("real")&& m2.equalsIgnoreCase("dolar"))
			ret="3.20";
		if(m1.equalsIgnoreCase("real")&& m2.equalsIgnoreCase("euro"))
			ret="4.15";
		return ret;
	}
}