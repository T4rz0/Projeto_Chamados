package aps1;
import java.util.List;
import java.util.Optional;

public interface OrdemServicoRepositorio {
	void salvar(OrdemDeServico os);
    List<OrdemDeServico> listarTodas();
    Optional<OrdemDeServico> buscarPorId(String id);
}
