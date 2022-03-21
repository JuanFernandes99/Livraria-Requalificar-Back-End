package livrariaRq.dto;

import java.util.ArrayList;
import java.util.List;

import livrariaRq.model.livro.Editora;

public class SimpleResponseEditora extends SimpleResponse {
	List<Editora> editoras;

	public SimpleResponseEditora() {
		editoras = new ArrayList<>();
	}

	public List<Editora> getEditoras() {
		return editoras;
	}

	public void setEditoras(List<Editora> aEditoras) {
		editoras = aEditoras;
	}

}
