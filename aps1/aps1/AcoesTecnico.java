package aps1;

public interface AcoesTecnico {
	void registrarDiagnostico(String idOS, String diagnostico);
    void apontarReparo(String idOS, String descricao, double horas, String pecas);
    void atualizarStatusOperacional(String idOS, StatusOS novoStatus);
    void concluirTecnicamente(String idOS);
}
