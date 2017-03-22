package vos;

import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Clase que representa una arreglo de Video
 * @author Juan
 */
public class ListaCompanias {
	
	/**
	 * List con los videos
	 */
	@JsonProperty(value="companias")
	private List<CompaniaTeatro> companias;
	
	/**
	 * Constructor de la clase ListaVideos
	 * @param videos - videos para agregar al arreglo de la clase
	 */
	public ListaCompanias( @JsonProperty(value="companias")List<CompaniaTeatro> companias){
		this.companias = companias;
	}

	/**
	 * Método que retorna la lista de videos
	 * @return  List - List con los videos
	 */
	public List<CompaniaTeatro> getCompanias() {
		return companias;
	}

	/**
	 * Método que asigna la lista de videos que entra como parametro
	 * @param  videos - List con los videos ha agregar
	 */
	public void setCompanias(List<CompaniaTeatro> comps) {
		this.companias = comps;
	}
	
}
