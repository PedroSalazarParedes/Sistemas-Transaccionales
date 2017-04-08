package vos;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonProperty;



public class LocalidadesDetail {

	@JsonProperty(value = "nombresLocalidades")
	private ArrayList<String> nombres;
	@JsonProperty(value = "valoresLocalidades")
	private ArrayList<Double> valores;

	public LocalidadesDetail(
			@JsonProperty(value = "nombresLocalidades") ArrayList<String> nombres,
			@JsonProperty(value = "valoresLocalidades") ArrayList<Double> valores) {
		super();
		this.nombres = nombres;
		this.valores = valores;
	}

	public ArrayList<String> getNombres() {
		return nombres;
	}

	public void setNombres(ArrayList<String> nombres) {
		this.nombres = nombres;
	}

	public ArrayList<Double> getValores() {
		return valores;
	}

	public void setValores(ArrayList<Double> valores) {
		this.valores = valores;
	}

}
