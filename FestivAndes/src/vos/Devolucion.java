package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Devolucion {
	
////Atributos

	/**
	 * Id del espectaculo
	 */
	@JsonProperty(value="boletaADevolver")
	private BoletaDevolver boleta;

	/**
	 * Nombre del espectaculo
	 */
	@JsonProperty(value="valorADevolver")
	private double valor;
	
	public Devolucion(@JsonProperty(value="boletaADevolver")BoletaDevolver boleta, @JsonProperty(value="valorADevolver")double valor) {
		super();
		this.boleta=boleta;
		this.valor = valor;
	}
	
	/**
	 * Método getter del atributo valor
	 * @return nombre del espectaculo
	 */
	public double getValor() {
		return valor;
	}

	/**
	 * Método setter del atributo valor <b>post: </b> El nombre del espectaculo ha sidfunciono
	 * cambiado con el valor que entra como parámetro
	 * @param valor - Nombre del espectaculo
	 */
	public void setValor(double valor) {
		this.valor = valor;
	}
	

}
