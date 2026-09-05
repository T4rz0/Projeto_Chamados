package aps1;

import java.util.List;

public class EquipamentoService {
    private final EquipamentoRepositorio equipRepo;

    public EquipamentoService(EquipamentoRepositorio equipRepo) {
        this.equipRepo = equipRepo;
    }
    
 // Método para cadastrar um novo equipamento no banco
    public void cadastrar(Equipamento equipamento) {
        equipRepo.salvar(equipamento);
    }

 // Regra de negócio: busca o equipamento, inativa-o e salva seu estado
    public void inativar(String idEquipamento) {
    	// Uso de Optional com orElseThrow para lançar exceção caso o ID não exista
        Equipamento equip = equipRepo.buscarPorId(idEquipamento)
                .orElseThrow(() -> new IllegalArgumentException("Equipamento não encontrado."));
        
     // Altera o estado do objeto
        equip.inativar();
        
     // Persiste a alteração
        equipRepo.salvar(equip);
    }
    
 // Retorna a lista completa de equipamentos cadastrados
    public List<Equipamento> listarTodos() {
        return equipRepo.listarTodos();
    }
}