package aps1;

import java.util.List;

public class UsuarioService {
    private final UsuarioRepositorio usuarioRepo;

    public UsuarioService(UsuarioRepositorio usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

 // Regra: valida se o objeto informado é válido e solicita o salvamento
    public void cadastrarUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo.");
        }
        usuarioRepo.salvar(usuario);
    }

    // Busca um usuário pelo ID e lança exceção caso o registro não exista
    public Usuario buscarPorId(String id) {
        return usuarioRepo.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado para o ID: " + id));
    }

 // Filtra e retorna apenas os técnicos que possuem o status DISPONIVEL
    public List<Tecnico> listarTecnicosDisponiveis() {
        return usuarioRepo.listarTecnicos().stream()
                .filter(tec -> tec.getStatusTec() == StatusTecnico.DISPONIVEL)
                .toList();
    }

 // Retorna a lista completa com todos os técnicos cadastrados
    public List<Tecnico> listarTodosTecnicos() {
        return usuarioRepo.listarTecnicos();
    }
}