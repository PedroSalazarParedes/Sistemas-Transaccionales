package vos;

import org.codehaus.jackson.annotate.*;

/**
 * Clase que representa un Espectaculo
 * @author Mariana
 */
public class Espectaculo 
{
////Atributos

	/**
	 * Id del espectaculo
	 */
	@JsonProperty(value="idEspactaculo")
	private Long id;

	/**
	 * Nombre del espectaculo
	 */
	@JsonProperty(value="nameEspactaculo")
	private String name;

	/**
	 * Duración en minutos del espectaculo
	 */
	@JsonProperty(value="durationEspactaculo")
	private int duration;
	
	/**
	 * Costo de realizaci�n del espectaculo
	 */
	@JsonProperty(value="costEspactaculo")
	private double cost;

	/**
	 * Participaci�n del p�blico en el espect�culo
	 */
	@JsonProperty(value="participationEspectaculo")
	private int participation;

	
	/**
	 * Descripci�n del espectaculo
	 */
	@JsonProperty(value="descriptionEspectaculo")
	private String description;

	/**
	 * Publico objetivo del espectaculo
	 */
	@JsonProperty(value="objetivoEspactaculo")
	private String objetivo;

	/**
	 * Idioma del espectaculo
	 */
	@JsonProperty(value="idiomaEspectaculo")
	private String idioma;

	/**
	 * Tipo de traducci�n del espectaculo
	 */
	@JsonProperty(value="traduccionEspectaculo")
	private String traduccion;

	/**
	 * Requerimientos t�cnicos del espectaculo
	 */
	@JsonProperty(value="reqtecnicosEspectaculo")
	private String reqtecnicos;

	/**
	 * Id de la categoria del espectaculo
	 */
	@JsonProperty(value="idcategoriaEspactaculo")
	private Long idcategoria;


	/**
	 * Método constructor de la clase espectaculo
	 * <b>post: </b> Crea el espectaculo con los valores que entran como parámetro
	 * @param id - Id del video.
	 * @param name - Nombre del video. name != null
	 * @param duration - Duración en minutos del video.
	 */
	public Espectaculo(@JsonProperty(value="idEspactaculo")Long id, @JsonProperty(value="nameEspactaculo")String name,@JsonProperty(value="durationEspactaculo") int duration,@JsonProperty(value="costEspactaculo") double cost, @JsonProperty(value="participationEspactaculo") int participation,
			@JsonProperty(value="descriptionEspactaculo") String description, @JsonProperty(value="objetivoEspactaculo") String objetivo, @JsonProperty(value="idiomaEspactaculo") String idioma, @JsonProperty(value="traduccionEspactaculo") String traduccion, 
			@JsonProperty(value="reqtecnicosEspactaculo") String reqtecnicos, @JsonProperty(value="idcategoriaEspactaculo") Long idcategoria) {
		super();
		this.id = id;
		this.name = name;
		this.duration = duration;
		this.cost = cost;
		this.participation = participation;
		this.description = description;
		this.objetivo = objetivo;
		this.idioma = idioma;
		this.traduccion = traduccion;
		this.reqtecnicos = reqtecnicos;
		this.idcategoria = idcategoria;
	}
	
	/**
	 * Método getter del atributo duration
	 * @return duración del espectaculo en minutos
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * Método setter del atributo duration <b>post: </b> La duración del video
	 * ha sido cambiado con el valor que entra como parámetro
	 * @param duration - Duración en minutos del espectaculo.
	 */
	public void setDuration(int duration) {
		this.duration = duration;
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
	public Double getCost() {
		return cost;
	}

	/**
	 * Método setter del atributo name <b>post: </b> El nombre del espectaculo ha sido
	 * cambiado con el valor que entra como parámetro
	 * @param name - Nombre del espectaculo
	 */
	public void setCost(Double cost) {
		this.cost = cost;
	}
	
	/**
	 * Método getter del atributo name
	 * @return nombre del espectaculo
	 */
	public int getParticipation() {
		return participation;
	}

	/**
	 * Método setter del atributo name <b>post: </b> El nombre del espectaculo ha sido
	 * cambiado con el valor que entra como parámetro
	 * @param name - Nombre del espectaculo
	 */
	public void setParticipation(int participation) {
		this.participation = participation;
	}
	
	/**
	 * Método getter del atributo name
	 * @return nombre del espectaculo
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Método setter del atributo name <b>post: </b> El nombre del espectaculo ha sido
	 * cambiado con el valor que entra como parámetro
	 * @param name - Nombre del espectaculo
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Método getter del atributo name
	 * @return nombre del espectaculo
	 */
	public String getObjetivo() {
		return objetivo;
	}

	/**
	 * Método setter del atributo name <b>post: </b> El nombre del espectaculo ha sido
	 * cambiado con el valor que entra como parámetro
	 * @param name - Nombre del espectaculo
	 */
	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}
	
	/**
	 * Método getter del atributo name
	 * @return nombre del espectaculo
	 */
	public String getIdioma() {
		return idioma;
	}

	/**
	 * Método setter del atributo name <b>post: </b> El nombre del espectaculo ha sido
	 * cambiado con el valor que entra como parámetro
	 * @param name - Nombre del espectaculo
	 */
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	
	
	 /** Método getter del atributo name
	 * @return nombre del espectaculo
	 */
	public String getTraduccion() {
		return traduccion;
	}

	/**
	 * Método setter del atributo name <b>post: </b> El nombre del espectaculo ha sido
	 * cambiado con el valor que entra como parámetro
	 * @param name - Nombre del espectaculo
	 */
	public void setTraduccion(String traduccion) {
		this.traduccion = traduccion;
	}
	
	/**
	 * Método getter del atributo name
	 * @return nombre del espectaculo
	 */
	public String getReqtecnicos() {
		return reqtecnicos;
	}

	/**
	 * Método setter del atributo name <b>post: </b> El nombre del espectaculo ha sido
	 * cambiado con el valor que entra como parámetro
	 * @param name - Nombre del espectaculo
	 */
	public void setReqtecnicos(String reqtecnicos) {
		this.reqtecnicos = reqtecnicos;
	}
	/**
	
	/**
	 * Método getter del atributo name
	 * @return nombre del espectaculo
	 */
	public Long getIdcategoria() {
		return idcategoria;
	}

	/**
	 * Método setter del atributo name <b>post: </b> El nombre del espectaculo ha sido
	 * cambiado con el valor que entra como parámetro
	 * @param name - Nombre del espectaculo
	 */
	public void setIdcategoria(Long idcategoria) {
		this.idcategoria = idcategoria;
	}
	

}
