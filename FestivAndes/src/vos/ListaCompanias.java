package vos;

import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;


public class ListaCompanias {
	

	@JsonProperty(value="companias")
	private List<CompaniaTeatro> companias;
	

	public ListaCompanias( @JsonProperty(value="companias")List<CompaniaTeatro> companias){
		this.companias = companias;
	}


	public List<CompaniaTeatro> getCompanias() {
		return companias;
	}


	public void setCompanias(List<CompaniaTeatro> comps) {
		this.companias = comps;
	}
	
}
