package aps1;

public abstract class Usuario {
	private String id;
    private String nome;
    private String email;
    private TipoUsuario tipo;
    
	public Usuario(String id, String nome, String email, TipoUsuario tipo) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.tipo = tipo;
	}

	public String getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}
	
	
    
    
}
