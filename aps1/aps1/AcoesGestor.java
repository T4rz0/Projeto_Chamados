package aps1;

public interface AcoesGestor {
	void cadastrarEquipamento(Equipamento equipamento);
    void inativarEquipamento(String idEquipamento);
    void cadastrarUsuario(Usuario usuario);
    void atribuirChamado(String idOS, String idTecnico);
    void aprovarEFecharOS(String idOS);
}
