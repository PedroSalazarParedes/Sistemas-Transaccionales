package vos;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Clase que representa un Localidad
 * @author Mariana
 */
public class Localidad 
{
////Atributos

	/**
	 * Id del espectaculo
	 */
	@JsonProperty(value="id")
	private Long id;

	/**
	 * Nombre del espectaculo
	 */
	@JsonProperty(value="name")
	private String name;

	/**
	 * Descripci�n del espectaculo
	 */
	@JsonProperty(value="idlugar")
	private Long idlugar;

	
	/**
	 * Duración en minutos del espectaculo
	 */
	@JsonProperty(value="capacity")
	private Integer capacity;
	

	/**
	 * Idioma del espectaculo
	 */
	@JsonProperty(value="numfilas")
	private Integer numfilas;

	/**
	 * Idioma del espectaculo
	 */
	@JsonProperty(value="numsillas")
	private Integer numsillas;

	/**
	 * Método constructor de la clase espectaculo
	 * <b>post: </b> Crea el espectaculo con los valores que entran como parámetro
	 * @param id - Id del video.
	 * @param name - Nombre del video. name != null
	 * @param capacity - Duración en minutos del video.
	 */
	public Localidad(@JsonProperty(value="id")Long id, @JsonProperty(value="name")String name,@JsonProperty(value="idlugar")Long idlugar,@JsonProperty(value="capacity") Integer capacity, 
			 @JsonProperty(value="numfilas") Integer numfilas, @JsonProperty(value="numsillas") Integer numsillas) {
		super();
		this.id = id;
		this.name = name;
		this.capacity = capacity;
		this.idlugar = idlugar;
		this.numsillas = numsillas;
		this.numfilas = numfilas;
	}
	
	/**
	 * Método getter del atributo capacity
	 * @return duración del espectaculo en minutos
	 */
	public Integer getCapacity() {
		return capacity;
	}

	/**
	 * Método setter del atributo capacity <b>post: </b> La duración del video
	 * ha sido cambiado con el valor que entra como parámetro
	 * @param capacity - Duración en minutos del espectaculo.
	 */
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	/**
	 * Método getter del atributo id
	 * @return id del espectaculo
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Método setter del atributo id <b>post: </b> El id del espectaculo ha sido
	 * cambiado con el valor que entra como parámetro
	 * @param id - Id del espectaculo
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Método getter del atributo name
	 * @return nombre del espectaculo
	 */
	public String getName() {
		return name;
	}

	/**
	 * Método setter del atributo name <b>post: </b> El nombre del espectaculo ha sido
	 * cambiado con el valor que entra como parámetro
	 * @param name - Nombre del espectaculo
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Método getter del atributo name
	 * @return nombre del espectaculo
	 */
	public Long getIdlugar() {
		return idlugar;
	}

	/**
	 * Método setter del atributo name <b>post: </b> El nombre del espectaculo ha sido
	 * cambiado con el valor que entra como parámetro
	 * @param name - Nombre del espectaculo
	 */
	public void setIdlugar(Long idlugar) {
		this.idlugar = idlugar;
	}
	
	/**
	 * Método getter del atributo name
	 * @return nombre del espectaculo
	 */
	public Integer getNumfilas() {
		return numfilas;
	}

	/**
	 * Método setter del atributo name <b>post: </b> El nombre del espectaculo ha sido
	 * cambiado con el valor que entra como parámetro
	 * @param name - Nombre del espectaculo
	 */
	public void setNumfilas(Integer numfilas) {
		this.numfilas = numfilas;
	}
	
	
	/**
	 * Método getter del atributo name
	 * @return nombre del espectaculo
	 */
	public Integer getNumsillas() {
		return numsillas;
	}

	/**
	 * Método setter del atributo name <b>post: </b> El nombre del espectaculo ha sido
	 * cambiado con el valor que entra como parámetro
	 * @param name - Nombre del espectaculo
	 */
	public void setNumsillas(Integer numsillas) {
		this.numsillas = numsillas;
	}
	
}
