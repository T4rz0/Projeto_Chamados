package aps1;
import java.util.List;

public interface AcoesMultiUser {
	List<Equipamento> listarEquipamentos();
    List<OrdemDeServico> listarOrdensServico();
    OrdemDeServico abrirChamado(String id, String idEquipamento, String falha, String criticidade);
    List<Tecnico> consultarDisponibilidadeTecnicos();
}
