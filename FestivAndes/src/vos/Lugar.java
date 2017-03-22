package vos;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Clase que representa un Lugar
 * @author Mariana
 */
public class Lugar 
{
////Atributos

	/**
	 * Id del espectaculo
	 */
	@JsonProperty(value="idLugar")
	private Long id;

	/**
	 * Nombre del espectaculo
	 */
	@JsonProperty(value="nameLugar")
	private String name;

	/**
	 * Descripci�n del espectaculo
	 */
	@JsonProperty(value="tipoLugar")
	private String tipo;

	
	/**
	 * Duración en minutos del espectaculo
	 */
	@JsonProperty(value="capacityLugar")
	private int capacity;
	
	
	
	/**
	 * Descripci�n del espectaculo
	 */
	@JsonProperty(value="silleteriaLugar")
	private String silleteria;

	/**
	 * Publico proteccion del espectaculo
	 */
	@JsonProperty(value="accesibilidadLugar")
	private String accesibilidad;

	/**
	 * Idioma del espectaculo
	 */
	@JsonProperty(value="proteccionLugar")
	private int proteccion;

	/**
	 * Tipo de traducci�n del espectaculo
	 */
	@JsonProperty(value="condtecnicasLugar")
	private String condtecnicas;

	/**
	 * Método constructor de la clase espectaculo
	 * <b>post: </b> Crea el espectaculo con los valores que entran como parámetro
	 * @param id - Id del video.
	 * @param name - Nombre del video. name != null
	 * @param capacity - Duración en minutos del video.
	 */
	public Lugar(@JsonProperty(value="idLugar")Long id, @JsonProperty(value="nameLugar")String name,@JsonProperty(value="tipoLugar")String tipo,@JsonProperty(value="capacityLugar") int capacity, @JsonProperty(value="silleteriaLugar") String silleteria,
			@JsonProperty(value="accesibilidadLugar") String accesibilidad, @JsonProperty(value="proteccionLugar") int proteccion, @JsonProperty(value="condtecnicasLugar") String condtecnicas) {
		super();
		this.id = id;
		this.name = name;
		this.capacity = capacity;
		this.silleteria = silleteria;
		this.accesibilidad = accesibilidad;
		this.proteccion = proteccion;
		this.condtecnicas = condtecnicas;
	}
	
	/**
	 * Método getter del atributo capacity
	 * @return duración del espectaculo en minutos
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Método setter del atributo capacity <b>post: </b> La duración del video
	 * ha sido cambiado con el valor que entra como parámetro
	 * @param capacity - Duración en minutos del espectaculo.
	 */
	public void setCapacity(int capacity) {
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
	public String getTipo() {
		return tipo;
	}

	/**
	 * Método setter del atributo name <b>post: </b> El nombre del espectaculo ha sido
	 * cambiado con el valor que entra como parámetro
	 * @param name - Nombre del espectaculo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * Método getter del atributo name
	 * @return nombre del espectaculo
	 */
	public String getSilleteria() {
		return silleteria;
	}

	/**
	 * Método setter del atributo name <b>post: </b> El nombre del espectaculo ha sido
	 * cambiado con el valor que entra como parámetro
	 * @param name - Nombre del espectaculo
	 */
	public void setSilleteria(String silleteria) {
		this.silleteria = silleteria;
	}
	
	/**
	 * Método getter del atributo name
	 * @return nombre del espectaculo
	 */
	public String getAccesibilidad() {
		return accesibilidad;
	}

	/**
	 * Método setter del atributo name <b>post: </b> El nombre del espectaculo ha sido
	 * cambiado con el valor que entra como parámetro
	 * @param name - Nombre del espectaculo
	 */
	public void setAccesibilidad(String accesibilidad) {
		this.accesibilidad = accesibilidad;
	}
	
	/**
	 * Método getter del atributo name
	 * @return nombre del espectaculo
	 */
	public int getProteccion() {
		return proteccion;
	}

	/**
	 * Método setter del atributo name <b>post: </b> El nombre del espectaculo ha sido
	 * cambiado con el valor que entra como parámetro
	 * @param name - Nombre del espectaculo
	 */
	public void setProteccion(int proteccion) {
		this.proteccion = proteccion;
	}
	
	
	
	 /** Método getter del atributo name
	 * @return nombre del espectaculo
	 */
	public String getCondtecnicas() {
		return condtecnicas;
	}

	/**
	 * Método setter del atributo name <b>post: </b> El nombre del espectaculo ha sido
	 * cambiado con el valor que entra como parámetro
	 * @param name - Nombre del espectaculo
	 */
	public void setCondtecnicas(String condtecnicas) {
		this.condtecnicas = condtecnicas;
	}
	
	
}
