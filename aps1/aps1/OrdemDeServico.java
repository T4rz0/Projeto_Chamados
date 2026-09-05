package aps1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrdemDeServico {
    private String id;
    private Equipamento equipamento;
    private String descricaoFalha;
    private String criticidade;
    private StatusOS status;
    private Tecnico tecnicoAlocado;
    private String diagnostico;
    private List<RegistroHistorico> historicoDeRegistros;

    public OrdemDeServico(String id, Equipamento equipamento, String descricaoFalha, String criticidade) {
    	// Regra de negócio: impede abertura de OS para equipamento inativo
    	if (!equipamento.isAtivo()) {
            throw new IllegalStateException("Não é possível abrir OS para um equipamento inativo.");
        }
        this.id = id;
        this.equipamento = equipamento;
        this.descricaoFalha = descricaoFalha;
        this.criticidade = criticidade;
        this.status = StatusOS.ABERTA;
        this.historicoDeRegistros = new ArrayList<>();
    }

 /// Aloca um técnico à OS e altera seu status para EM_EXECUCAO
    public void atribuirTecnico(Tecnico tecnico) {
        if (this.status != StatusOS.ABERTA) {
            throw new IllegalStateException("Apenas OSs no status ABERTA podem ser atribuídas.");
        }
        tecnico.ocupar(); // Altera o status do técnico para EM_ATENDIMENTO
        this.tecnicoAlocado = tecnico;
        this.status = StatusOS.EM_EXECUCAO;
    }

 // Registra o parecer técnico inicial após a análise do equipamento
    public void registrarDiagnostico(String diagnostico) {
        if (this.status != StatusOS.EM_EXECUCAO) {
            throw new IllegalStateException("A OS precisa estar EM_EXECUCAO para registrar diagnóstico.");
        }
        this.diagnostico = diagnostico;
    }

    // Adiciona um novo registro ao histórico de manutenções
    public void adicionarRegistro(String descricao, double horas, String pecas) {
        if (this.status != StatusOS.EM_EXECUCAO && this.status != StatusOS.AGUARDANDO_PECA) {
            throw new IllegalStateException("Não é possível apontar intervenção neste status.");
        }
        this.historicoDeRegistros.add(new RegistroHistorico(descricao, horas, pecas));
    }

    // Permite ao técnico alterar o status operacional (ex: AGUARDANDO_PECA)
    public void atualizarStatusOperacional(StatusOS novoStatus) {
        if (novoStatus == StatusOS.FECHADA || novoStatus == StatusOS.CANCELADA) {
            throw new IllegalArgumentException("Alterações definitivas de fechamento/cancelamento cabem ao Gestor.");
        }
        this.status = novoStatus;
    }
    
 // Finaliza a etapa técnica e libera o técnico para novos atendimentos
    public void concluirTecnicamente() {
        if (this.status != StatusOS.EM_EXECUCAO && this.status != StatusOS.AGUARDANDO_PECA) {
            throw new IllegalStateException("Apenas OSs em execução podem ser concluídas tecnicamente.");
        }
        this.status = StatusOS.REPARO_FINALIZADO;
        if (this.tecnicoAlocado != null) {
            this.tecnicoAlocado.liberar();
        }
    }

 // Encerramento formal e administrativo da OS pelo Gestor
    public void fecharPeloGestor() {
        if (this.status != StatusOS.REPARO_FINALIZADO) {
            throw new IllegalStateException("A OS precisa estar com REPARO_FINALIZADO para fechar.");
        }
        this.status = StatusOS.FECHADA;
    }

 // Cancelamento da OS pelo Gestor, liberando o técnico se estivesse alocado
    public void cancelarPeloGestor() {
        if (this.status == StatusOS.FECHADA) {
            throw new IllegalStateException("Uma OS já fechada não pode ser cancelada.");
        }
        if (this.tecnicoAlocado != null && this.status != StatusOS.REPARO_FINALIZADO) {
            this.tecnicoAlocado.liberar();
        }
        this.status = StatusOS.CANCELADA;
    }

    
    public String getId() {
		return id;
	}

	public Equipamento getEquipamento() {
		return equipamento;
	}

	public String getDescricaoFalha() {
		return descricaoFalha;
	}

	public String getCriticidade() {
		return criticidade;
	}

	public StatusOS getStatus() {
		return status;
	}

	public Tecnico getTecnicoAlocado() {
		return tecnicoAlocado;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public List<RegistroHistorico> getHistoricoDosRegistros() { 
        return Collections.unmodifiableList(historicoDeRegistros); 
    }
   }