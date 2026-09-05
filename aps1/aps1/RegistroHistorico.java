package aps1;
import java.time.LocalDateTime;

public class RegistroHistorico {
	private LocalDateTime dataHora;
    private String descricao;
    private double horasTrabalhadas;
    private String materiaisUtilizados;

    public RegistroHistorico(String descricao, double horasTrabalhadas, String materiais) {
        this.dataHora = LocalDateTime.now();
        this.descricao = descricao;
        this.horasTrabalhadas = horasTrabalhadas;
        this.materiaisUtilizados = materiais;
    }

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public String getDescricao() {
		return descricao;
	}

	public double getHorasTrabalhadas() {
		return horasTrabalhadas;
	}

	public String getMateriaisUtilizados() {
		return materiaisUtilizados;
	}
}
