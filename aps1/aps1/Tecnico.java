package aps1;

public class Tecnico extends Usuario{
	private StatusTecnico statusTec;

	public Tecnico(String id, String nome, String email, TipoUsuario tipo, StatusTecnico statusTec) {
		super(id, nome, email, TipoUsuario.TECNICO);
		this.statusTec = statusTec;
	}
	
	// Regra de negócio: altera o status para EM_ATENDIMENTO se o técnico não estiver ausente
    public void ocupar() {
        if (this.statusTec == StatusTecnico.AUSENTE) {
            throw new IllegalStateException("Técnico ausente não pode ser alocado.");
        }
        this.statusTec = StatusTecnico.EM_ATENDIMENTO;
    }

 // Regra de negócio: libera o técnico tornando-o disponível para novos chamados
    public void liberar() {
        this.statusTec = StatusTecnico.DISPONIVEL;
    }

 // Regra de negócio: marca o técnico como ausente se não tiver atendimentos em andamento
    public void marcarComoAusente() {
        if (this.statusTec == StatusTecnico.EM_ATENDIMENTO) {
            throw new IllegalStateException("Finalize os atendimentos antes de marcar ausência.");
        }
        this.statusTec = StatusTecnico.AUSENTE;
    }

	public StatusTecnico getStatusTec() {
		return statusTec;
	}
}
