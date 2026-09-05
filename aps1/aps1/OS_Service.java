package aps1;

import java.util.List;

public class OS_Service {
    private final OrdemServicoRepositorio osRepo;
    private final EquipamentoRepositorio equipRepo;

    public OS_Service(OrdemServicoRepositorio osRepo, EquipamentoRepositorio equipRepo) {
        this.osRepo = osRepo;
        this.equipRepo = equipRepo;
    }

 // Fluxo de abertura de chamado: busca o equipamento e cria uma nova OS no sistema
    public OrdemDeServico abrirChamado(String id, String idEquipamento, String falha, String criticidade) {
    	// Busca o equipamento ou lança exceção caso não exista
        Equipamento equipamento = equipRepo.buscarPorId(idEquipamento)
                .orElseThrow(() -> new IllegalArgumentException("Equipamento não encontrado ID: " + idEquipamento));
        
     // Instancia a nova OS (a própria entidade valida se o equipamento está ativo)
        OrdemDeServico novaOS = new OrdemDeServico(id, equipamento, falha, criticidade);
        
     // Persiste a OS criada
        osRepo.salvar(novaOS);
        return novaOS;
    }

 // Aloca um técnico à OS e atualiza seu estado no repositório
    public void atribuirTecnico(String idOS, Tecnico tecnico) {
        OrdemDeServico os = buscarOS(idOS);
        os.atribuirTecnico(tecnico); // Delega a regra de transição de estado para o objeto de domínio
        osRepo.salvar(os);
    }

 // Registra a análise do problema
    public void registrarDiagnostico(String idOS, String diagnostico) {
        OrdemDeServico os = buscarOS(idOS);
        os.registrarDiagnostico(diagnostico);
        osRepo.salvar(os);
    }

 // Registra o trabalho executado, tempo gasto e peças usadas
    public void RegistrarReparo(String idOS, String descricao, double horas, String pecas) {
        OrdemDeServico os = buscarOS(idOS);
        os.adicionarRegistro(descricao, horas, pecas);
        osRepo.salvar(os);
    }

 // Finaliza o atendimento do técnico e libera o profissional para novos chamados
    public void encerrarReparo(String idOS) {
        OrdemDeServico os = buscarOS(idOS);
        os.concluirTecnicamente();
        osRepo.salvar(os);
    }

 // Validação e encerramento da OS efetuado pelo Gestor
    public void fecharPeloGestor(String idOS) {
        OrdemDeServico os = buscarOS(idOS);
        os.fecharPeloGestor();
        osRepo.salvar(os);
    }
    
 // Consulta a lista completa de Ordens de Serviço
    public List<OrdemDeServico> listarTodas() {
        return osRepo.listarTodas();
    }

 // Método auxiliar para validação de existência da OS
    private OrdemDeServico buscarOS(String idOS) {
        return osRepo.buscarPorId(idOS)
                .orElseThrow(() -> new IllegalArgumentException("OS não encontrada: " + idOS));
    }
}