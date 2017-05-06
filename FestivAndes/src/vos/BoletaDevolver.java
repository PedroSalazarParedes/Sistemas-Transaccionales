package vos;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Clase que representa una funcion
 * @author Mariana
 * idfuncion_localidfuncionad VARCHAR(25),
   num_fila INTEGER
      CHECK (num_fila>0),
   num_silla INTEGER
 */
public class BoletaDevolver 
{
////Atributos

	/**
	 * Id del espectaculo
	 */
	@JsonProperty(value="idfuncionBoletaDevolver")
	private Long idfuncion;

	/**
	 * Nombre del espectaculo
	 */
	@JsonProperty(value="valorBoletaDevolver")
	private double valor;
	
	/**
	 * Nombre del espectaculo
	 */
	@JsonProperty(value="idlocalidadBoletaDevolver")
	private Long idlocalidad;
	

	/**
	 * Duración en minutos del espectaculo
	 */
	@JsonProperty(value="numfilaBoletaDevolver")
	private Integer numfila;
	

	/**
	 * Requerimientos t�cnicos del espectaculo
	 */
	@JsonProperty(value="numsillaBoletaDevolver")
	private Integer numsilla;
	
	@JsonProperty(value="numBoletaDevolver")
	private int numBoletaDevolver;


	/**
	 * Método constructor de la clase espectaculo
	 * <b>post: </b> Crea el espectaculo con los valores que entran como parámetro
	 * @param idfuncion - Id del vidfuncioneo.
	 * @param valor - Nombre del vidfuncioneo. valor != null
	 * @param realizado - Duración en minutos del vidfuncioneo.
	 */
	public BoletaDevolver(@JsonProperty(value="idfuncionBoletaDevolver")Long idfuncion, @JsonProperty(value="valorBoletaDevolver")double valor,
			 @JsonProperty(value="idlocalidadBoletaDevolver") Long idlocalidad, @JsonProperty(value="numfilaBoletaDevolver") Integer numfila,
			 @JsonProperty(value="numsillaBoletaDevolver") Integer numsilla, @JsonProperty(value="numBoletaDevolver") Integer numBoletaDevolver) {
		super();
		this.idfuncion = idfuncion;
		this.valor = valor;
		this.numsilla = numsilla;
		this.idlocalidad = idlocalidad;
		this.numfila = numfila;
		this.numBoletaDevolver = numBoletaDevolver;
	}
	
	
	/**
	 * Método getter del atributo idfuncion
	 * @return idfuncion del espectaculo
	 */
	public Long getIdfuncion() {
		return idfuncion;
	}

	/**
	 * Método setter del atributo idfuncion <b>post: </b> El idfuncion del espectaculo ha sidfunciono
	 * cambiado con el valor que entra como parámetro
	 * @param idfuncion - Id del espectaculo
	 */
	public void setIdfuncion(Long idfuncion) {
		this.idfuncion = idfuncion;
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
	
	/**
	 * Método getter del atributo valor
	 * @return nombre del espectaculo
	 */
	public Integer getNumsilla() {
		return numsilla;
	}

	/**
	 * Método setter del atributo valor <b>post: </b> El nombre del espectaculo ha sidfunciono
	 * cambiado con el valor que entra como parámetro
	 * @param valor - Nombre del espectaculo
	 */
	public void setNumsilla(Integer numsilla) {
		this.numsilla = numsilla;
	}
	
	
	

	

	/**
	 * Método getter del atributo valor
	 * @return nombre del espectaculo
	 */
	public Long getIdlocalidad() {
		return idlocalidad;
	}

	/**
	 * Método setter del atributo valor <b>post: </b> El nombre del espectaculo ha sidfunciono
	 * cambiado con el valor que entra como parámetro
	 * @param valor - Nombre del espectaculo
	 */
	public void setIdlocalidad(Long idlocalidad) {
		this.idlocalidad = idlocalidad;
	}
	/**
	
	/**
	 * Método getter del atributo valor
	 * @return nombre del espectaculo
	 */
	public Integer getNumfila() {
		return numfila;
	}

	/**
	 * Método setter del atributo valor <b>post: </b> El nombre del espectaculo ha sidfunciono
	 * cambiado con el valor que entra como parámetro
	 * @param valor - Nombre del espectaculo
	 */
	public void setNumfila(Integer numfila) {
		this.numfila = numfila;
	}
	
	/**
	 * Método getter del atributo valor
	 * @return nombre del espectaculo
	 */
	public Integer getNumBoletaDevolver() {
		return numBoletaDevolver;
	}

	/**
	 * Método setter del atributo valor <b>post: </b> El nombre del espectaculo ha sidfunciono
	 * cambiado con el valor que entra como parámetro
	 * @param valor - Nombre del espectaculo
	 */
	public void setNumBoletaDevolver(int num) {
		this.numBoletaDevolver = num;
	}
	

}
