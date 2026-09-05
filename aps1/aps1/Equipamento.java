package aps1;

public class Equipamento {
	private String id;
    private String nome;
    private String modelo;
    private boolean ativo;
    

    public Equipamento(String id, String nome, String modelo) {
        this.id = id;
        this.nome = nome;
        this.modelo = modelo;
        this.ativo = true; // Todo equipamento novo começa ativo no sistema
    }
    
 // Regra de negócio: inativa o equipamento se ele já não estiver inativo
    public void inativar() {
        if (!this.ativo) {
            throw new IllegalStateException("O equipamento já está inativo.");
        }
        this.ativo = false;
    }
    
 // Regra de negócio: altera o nome e/ou modelo apenas se valores válidos forem passados
    public void atualizarDados(String novoNome, String novoModelo) {
        if (novoNome != null && !novoNome.isBlank()) {
            this.nome = novoNome;
        }
        if (novoModelo != null && !novoModelo.isBlank()) {
            this.modelo = novoModelo;
        }
    }

	public String getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public String getModelo() {
		return modelo;
	}
    
}
