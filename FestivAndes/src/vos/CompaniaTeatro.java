package vos;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Clase que representa un Espectaculo
 * @author Mariana
 */
public class CompaniaTeatro
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
	@JsonProperty(value="representante")
	private String representante;

	/**
	 * Publico paginaWeb del espectaculo
	 */
	@JsonProperty(value="paginaWeb")
	private String paginaWeb;

	/**
	 * pais del espectaculo
	 */
	@JsonProperty(value="pais")
	private String pais;



	/**
	 * Método constructor de la clase espectaculo
	 * <b>post: </b> Crea el espectaculo con los valores que entran como parámetro
	 * @param id - Id del video.
	 * @param name - Nombre del video. name != null
	 * @param duration - Duración en minutos del video.
	 */
	public CompaniaTeatro(@JsonProperty(value="id")Long id, @JsonProperty(value="name")String name,
			@JsonProperty(value="representante") String representante, @JsonProperty(value="paginaWeb") String paginaWeb, @JsonProperty(value="pais") String pais) {
		super();
		this.id = id;
		this.name = name;
		this.representante = representante;
		this.paginaWeb = paginaWeb;
		this.pais = pais;
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
	public String getRepresentante() {
		return representante;
	}

	/**
	 * Método setter del atributo name <b>post: </b> El nombre del espectaculo ha sido
	 * cambiado con el valor que entra como parámetro
	 * @param name - Nombre del espectaculo
	 */
	public void setRepresentante(String representante) {
		this.representante = representante;
	}
	
	/**
	 * Método getter del atributo name
	 * @return nombre del espectaculo
	 */
	public String getPaginaWeb() {
		return paginaWeb;
	}

	/**
	 * Método setter del atributo name <b>post: </b> El nombre del espectaculo ha sido
	 * cambiado con el valor que entra como parámetro
	 * @param name - Nombre del espectaculo
	 */
	public void setPaginaWeb(String paginaWeb) {
		this.paginaWeb = paginaWeb;
	}
	
	/**
	 * Método getter del atributo name
	 * @return nombre del espectaculo
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * Método setter del atributo name <b>post: </b> El nombre del espectaculo ha sido
	 * cambiado con el valor que entra como parámetro
	 * @param name - Nombre del espectaculo
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}
	
	
	 
	

}
