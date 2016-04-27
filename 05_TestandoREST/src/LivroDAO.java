import java.util.ArrayList;
import java.util.List;

public class LivroDAO {
	List<Livro> livros;

	public LivroDAO() {
		livros = new ArrayList<Livro>();

		Livro l1 = new Livro();
		l1.setCodigo(1234);
		l1.setTitulo("Codigo da Vinci");
		l1.setAno(2002);
		l1.setAutor("Dan Brown");
		l1.setPreco(35.55);
		livros.add(l1);
		
		l1 = new Livro();
		l1.setCodigo(6789);
		l1.setTitulo("Cem Anos de Solidao");
		l1.setAno(1998);
		l1.setAutor("Gabriel Garcia Marquez");
		l1.setPreco(40.05);
		livros.add(l1);
		
		l1 = new Livro();
		l1.setCodigo(2000);
		l1.setTitulo("Dom Casmurro");
		l1.setAno(1899);
		l1.setAutor("Machado de Assis");
		l1.setPreco(22.49);
		livros.add(l1);
	}

	public List<Livro> getLivros() {
		// TODO Auto-generated method stub
		return livros;
	}

	public Livro getLivro(long id) {
		// TODO Auto-generated method stub
		for(Livro l:livros){
			if(l.getCodigo() == id){
				return l;
			}
		}
		return null;
	}

	public void salvar(Livro livro) {
		// TODO Auto-generated method stub
		livros.add(livro);
		System.out.println("salvou o livro de id: " + livro.getCodigo());
	}

	public Livro atualizar(long id, Livro livro) {
		// TODO Auto-generated method stub
		Livro l = getLivro(id);
		l = livro;
		System.out.println("atualizou");
		return l;
	}

	public String apagar(long id) {
		// TODO Auto-generated method stub
		Livro l = getLivro(id);
		if(l != null){
			livros.remove(l);
			return "remocao com sucesso";
		}
		return "erro na remocao";
	}
}