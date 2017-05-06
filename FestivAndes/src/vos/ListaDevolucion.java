package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ListaDevolucion {


		@JsonProperty(value="devoluciones")
		private List<Devolucion> devoluciones;
		

		public ListaDevolucion( @JsonProperty(value="devoluciones")List<Devolucion> devoluciones){
			super();
			this.devoluciones = devoluciones;
		}


		public List<Devolucion> getDevoluciones() {
			return devoluciones;
		}


		public void setDevoluciones(List<Devolucion> comps) {
			this.devoluciones = comps;
		}
		
	

}
