package vos;

import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Clase que representa una arreglo de Funcion
 * @author Mariana
 */
public class ListaFunciones {
	
	/**
	 * List con las funciones
	 */
	@JsonProperty(value="funciones")
	private List<Funcion> funciones;
	
	/**
	 * Constructor de la clase ListaFunciones
	 * @param funciones - funciones para agregar al arreglo de la clase
	 */
	public ListaFunciones( @JsonProperty(value="funciones")List<Funcion> funciones){
		this.funciones = funciones;
	}

	/**
	 * Método que retorna la lista de funciones
	 * @return  List - List con las funciones
	 */
	public List<Funcion> getFunciones() {
		return funciones;
	}

	/**
	 * Método que asigna la lista de funciones que entra como parametro
	 * @param  funciones - List con las funciones a agregar
	 */
	public void setFunciones(List<Funcion> comps) {
		this.funciones = comps;
	}
	
}
