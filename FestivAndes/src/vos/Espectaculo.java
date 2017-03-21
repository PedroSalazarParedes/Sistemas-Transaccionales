package vos;


import org.codehaus.jackson.annotate.*;
public class Espectaculo {
	

	@JsonProperty(value = "id")
	private Long id;
	
	@JsonProperty(value = "nombre")
	private String nombre;
	
	@JsonProperty(value = "duracion")
	private Integer duracion;
	
	@JsonProperty(value = "costo")
	private Double costo;
	
	@JsonProperty(value = "participacion")
	private int participacion;
	
	@JsonProperty(value = "descripcion")
	private String descripcion;
	
	@JsonProperty(value = "publicoObjetivo")
	private String publicoObjetivo;
	
	@JsonProperty(value = "idioma")
	private String idioma;
	
	@JsonProperty(value = "tipoTraduccion")
	private String tipoTraduccion;
	
	@JsonProperty(value = "requerimientosTecnicos")
	private String requerimientosTecnicos;
	
	@JsonProperty(value = "idCategoria")
	private Long idCategoria;

	public Espectaculo(Long id, String nombre, Integer duracion, Double costo,
			int participacion, String descripcion, String publicoObjetivo,
			String idioma, String tipoTraduccion,
			String requerimientosTecnicos, Long idCategoria) {
		this.id = id;
		this.nombre = nombre;
		this.duracion = duracion;
		this.costo = costo;
		this.participacion = participacion;
		this.descripcion = descripcion;
		this.publicoObjetivo = publicoObjetivo;
		this.idioma = idioma;
		this.tipoTraduccion = tipoTraduccion;
		this.requerimientosTecnicos = requerimientosTecnicos;
		this.idCategoria = idCategoria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public int getParticipacion() {
		return participacion;
	}

	public void setParticipacion(int participacion) {
		this.participacion = participacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPublicoObjetivo() {
		return publicoObjetivo;
	}

	public void setPublicoObjetivo(String publicoObjetivo) {
		this.publicoObjetivo = publicoObjetivo;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getTipoTraduccion() {
		return tipoTraduccion;
	}

	public void setTipoTraduccion(String tipoTraduccion) {
		this.tipoTraduccion = tipoTraduccion;
	}

	public String getRequerimientosTecnicos() {
		return requerimientosTecnicos;
	}

	public void setRequerimientosTecnicos(String requerimientosTecnicos) {
		this.requerimientosTecnicos = requerimientosTecnicos;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}
	

	

}
