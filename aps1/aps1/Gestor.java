package aps1;

public class Gestor extends Usuario{

	public Gestor(String id, String nome, String email, TipoUsuario tipo) {
		super(id, nome, email, TipoUsuario.GESTOR);
	}
	
}
