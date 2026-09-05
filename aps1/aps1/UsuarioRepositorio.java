package aps1;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepositorio {
	void salvar(Usuario usuario);
	List<Tecnico> listarTecnicos();
	Optional<Usuario> buscarPorId(String id);
}
