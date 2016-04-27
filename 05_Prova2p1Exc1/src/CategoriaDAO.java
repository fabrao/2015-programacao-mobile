import java.util.ArrayList;
import java.util.List;


public class CategoriaDAO {
	List<Categoria> categorias;
	
	public CategoriaDAO(){
		categorias = new ArrayList<Categoria>();
		
		Categoria c1 = new Categoria();
		c1.setId(0);
		c1.setNome("");
		c1.setDescricao("");
		categorias.add(c1);
		
		c1 = new Categoria();
		c1.setId(1);
		c1.setNome("games");
		c1.setDescricao("sao jogos");
		categorias.add(c1);
		
		c1 = new Categoria();
		c1.setId(2);
		c1.setNome("utilitarios");
		c1.setDescricao("auxiliam o usuario em tarefas");
		categorias.add(c1);
		
		c1 = new Categoria();
		c1.setId(3);
		c1.setNome("saude");
		c1.setDescricao("melhoram a saude");
		categorias.add(c1);
		
		c1 = new Categoria();
		c1.setId(4);
		c1.setNome("social");
		c1.setDescricao("possuem redes sociais");
		categorias.add(c1);
	}

	public List<Categoria> getCategorias() {
		List<Categoria> categorias2;
		categorias2 = categorias;
		categorias2.remove(0);
		return categorias2;
	}

	public void salvar(Categoria categoria) {
		// TODO Auto-generated method stub
		categorias.add(categoria);
		System.out.println("salvou o categoria de id: " + categoria.getId());
	}
	
	public Categoria getCategoria(int id) {
		// TODO Auto-generated method stub
		for(Categoria c:categorias){
			if(c.getId() == id){
				return c;
			}
		}
		return null;
	}
	
	public String apagar(int id) {
		// TODO Auto-generated method stub
		Categoria c = getCategoria(id);
		if(c != null){
			categorias.remove(c);
			return "remocao com sucesso";
		}
		return "erro na remocao";
	}
	
	public void remover(int id){
		// TODO Auto-generated method stub
		Categoria c = getCategoria(id);
		if(c != null){
			categorias.remove(c);
		}
	}
	
}
