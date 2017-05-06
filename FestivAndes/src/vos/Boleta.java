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
public class Boleta 
{
////Atributos

	/**
	 * Id del espectaculo
	 */
	@JsonProperty(value="idfuncionBoleta")
	private Long idfuncion;

	/**
	 * Nombre del espectaculo
	 */
	@JsonProperty(value="valorBoleta")
	private double valor;
	
	/**
	 * Nombre del espectaculo
	 */
	@JsonProperty(value="idlocalidadBoleta")
	private Long idlocalidad;
	

	/**
	 * Duración en minutos del espectaculo
	 */
	@JsonProperty(value="numfilaBoleta")
	private Integer numfila;
	

	/**
	 * Requerimientos t�cnicos del espectaculo
	 */
	@JsonProperty(value="numsillaBoleta")
	private Integer numsilla;


	/**
	 * Método constructor de la clase espectaculo
	 * <b>post: </b> Crea el espectaculo con los valores que entran como parámetro
	 * @param idfuncion - Id del vidfuncioneo.
	 * @param valor - Nombre del vidfuncioneo. valor != null
	 * @param realizado - Duración en minutos del vidfuncioneo.
	 */
	public Boleta(@JsonProperty(value="idfuncionBoleta")Long idfuncion, @JsonProperty(value="valorBoleta")double valor,
			 @JsonProperty(value="idlocalidadBoleta") Long idlocalidad, @JsonProperty(value="numfilaBoleta") Integer numfila,
			 @JsonProperty(value="numsillaBoleta") Integer numsilla) {
		super();
		this.idfuncion = idfuncion;
		this.valor = valor;
		this.numsilla = numsilla;
		this.idlocalidad = idlocalidad;
		this.numfila = numfila;
	
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
	

}
