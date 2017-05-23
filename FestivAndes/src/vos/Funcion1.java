package vos;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Clase que representa una funcion
 * @author Mariana
 */
public class Funcion1 
{
////Atributos

	/**
	 * Id del espectaculo
	 */
	@JsonProperty(value="idFuncion")
	private Long id;

	/**
	 * Nombre del espectaculo
	 */
	@JsonProperty(value="fechaFuncion")
	private String fecha;

	/**
	 * Duración en minutos del espectaculo
	 */
	@JsonProperty(value="horaFuncion")
	private String hora;
	

	

	/**
	 * Requerimientos t�cnicos del espectaculo
	 */
	@JsonProperty(value="idlugarFuncion")
	private Long idlugar;

	/**
	 * Id de la categoria del espectaculo
	 */
	@JsonProperty(value="idespectaculoFuncion")
	private Long idespectaculo;


	/**
	 * Método constructor de la clase espectaculo
	 * <b>post: </b> Crea el espectaculo con los valores que entran como parámetro
	 * @param id - Id del video.
	 * @param fecha - Nombre del video. fecha != null
	 * @param realizado - Duración en minutos del video.
	 */
	public Funcion1(@JsonProperty(value="id")Long id, @JsonProperty(value="fecha")String fecha, @JsonProperty(value="hora")String hora,
			 @JsonProperty(value="idlugar") Long idlugar, @JsonProperty(value="idespectaculo") Long idespectaculo) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.hora = hora;
		this.idlugar = idlugar;
		this.idespectaculo = idespectaculo;
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
	 * Método getter del atributo fecha
	 * @return nombre del espectaculo
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Método setter del atributo fecha <b>post: </b> El nombre del espectaculo ha sido
	 * cambiado con el valor que entra como parámetro
	 * @param fecha - Nombre del espectaculo
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	/**
	 * Método getter del atributo fecha
	 * @return nombre del espectaculo
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * Método setter del atributo fecha <b>post: </b> El nombre del espectaculo ha sido
	 * cambiado con el valor que entra como parámetro
	 * @param fecha - Nombre del espectaculo
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}
	
	
	

	

	/**
	 * Método getter del atributo fecha
	 * @return nombre del espectaculo
	 */
	public Long getIdlugar() {
		return idlugar;
	}

	/**
	 * Método setter del atributo fecha <b>post: </b> El nombre del espectaculo ha sido
	 * cambiado con el valor que entra como parámetro
	 * @param fecha - Nombre del espectaculo
	 */
	public void setIdlugar(Long idlugar) {
		this.idlugar = idlugar;
	}
	/**
	
	/**
	 * Método getter del atributo fecha
	 * @return nombre del espectaculo
	 */
	public Long getIdespectaculo() {
		return idespectaculo;
	}

	/**
	 * Método setter del atributo fecha <b>post: </b> El nombre del espectaculo ha sido
	 * cambiado con el valor que entra como parámetro
	 * @param fecha - Nombre del espectaculo
	 */
	public void setIdespectaculo(Long idespectaculo) {
		this.idespectaculo = idespectaculo;
	}
	

}
