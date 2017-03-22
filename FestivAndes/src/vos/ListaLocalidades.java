package vos;

import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Clase que representa una arreglo de Video
 * @author Mariana
 */
public class ListaLocalidades {
	
	/**
	 * List con los videos
	 */
	@JsonProperty(value="localidades")
	private List<Localidad> localidades;
	
	/**
	 * Constructor de la clase ListaVideos
	 * @param videos - videos para agregar al arreglo de la clase
	 */
	public ListaLocalidades( @JsonProperty(value="localidades")List<Localidad> localidades){
		this.localidades = localidades;
	}

	/**
	 * Método que retorna la lista de videos
	 * @return  List - List con los videos
	 */
	public List<Localidad> getLocalidades() {
		return localidades;
	}

	/**
	 * Método que asigna la lista de videos que entra como parametro
	 * @param  videos - List con los videos ha agregar
	 */
	public void setLocalidades(List<Localidad> comps) {
		this.localidades = comps;
	}
	
}
