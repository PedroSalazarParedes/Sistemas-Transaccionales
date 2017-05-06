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
public class BoletaMultiple 
{
////Atributos

	/**
	 * Id del espectaculo
	 */
	@JsonProperty(value="idfuncionBoletaMultiple")
	private Long idfuncion;

	/**
	 * Nombre del espectaculo
	 */
	@JsonProperty(value="valorBoletaMultiple")
	private double valor;
	
	/**
	 * Nombre del espectaculo
	 */
	@JsonProperty(value="idlocalidadBoletaMultiple")
	private Long idlocalidad;
	
	@JsonProperty(value="cantidadBoletas")
	private int numBoletas;



	/**
	 * Método constructor de la clase espectaculo
	 * <b>post: </b> Crea el espectaculo con los valores que entran como parametro
	 * @param idfuncion - Id del vidfuncioneo.
	 * @param valor - Nombre del vidfuncioneo. valor != null
	 * @param realizado - Duración en minutos del vidfuncioneo.
	 */
	public BoletaMultiple(@JsonProperty(value="idfuncionBoletaMultiple")Long idfuncion, @JsonProperty(value="valorBoletaMultiple")double valor,
			 @JsonProperty(value="idlocalidadBoletaMultiple") Long idlocalidad, @JsonProperty(value="cantidadBoletas") int num) {
		super();
		this.idfuncion = idfuncion;
		this.valor = valor;
		this.idlocalidad = idlocalidad;
		this.numBoletas = num;
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
	 * Método getter del atributo valor
	 * @return nombre del espectaculo
	 */
	public int getNumboletas() {
		return numBoletas;
	}

	/**
	 * Método setter del atributo valor <b>post: </b> El nombre del espectaculo ha sidfunciono
	 * cambiado con el valor que entra como parámetro
	 * @param valor - Nombre del espectaculo
	 */
	public void setNumboletas(int num) {
		this.numBoletas = num;
	}



}
