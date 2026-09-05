package aps1;
import java.util.List;
import java.util.Optional;

public interface EquipamentoRepositorio {
	void salvar(Equipamento equipamento);
    List<Equipamento> listarTodos();
    Optional<Equipamento> buscarPorId(String id);
}
