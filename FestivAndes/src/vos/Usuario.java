package vos;
	import org.codehaus.jackson.annotate.JsonProperty;

	/**
	 * Clase que representa un Espectaculo
	 * @author Mariana
	 */
	public class Usuario
	{
		////Atributos

		/**
		 * Id del espectaculo
		 */
		@JsonProperty(value="idUsuario")
		private Long id;

		/**
		 * Nombre del espectaculo
		 */
		@JsonProperty(value="nameUsuario")
		private String name;

		
		/**
		 * Descripci�n del espectaculo
		 */
		@JsonProperty(value="emailUsuario")
		private String email;

		/**
		 * Publico objetivo del espectaculo
		 */
		@JsonProperty(value="rolUsuario")
		private String rol;

	
		/**
		 * Método constructor de la clase espectaculo
		 * <b>post: </b> Crea el espectaculo con los valores que entran como parámetro
		 * @param id - Id del video.
		 * @param name - Nombre del video. name != null
		 * @param duration - Duración en minutos del video.
		 */
		public Usuario(@JsonProperty(value="idUsuario")Long id, @JsonProperty(value="nameUsuario")String name,@JsonProperty(value="emailUsuario") String email,@JsonProperty(value="rolUsuario") String rol) {
			super();
			this.id = id;
			this.name = name;
			this.email=email;
			this.rol=rol;
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
		public String getEmail() {
			return email;
		}

		/**
		 * Método setter del atributo name <b>post: </b> El nombre del espectaculo ha sido
		 * cambiado con el valor que entra como parámetro
		 * @param name - Nombre del espectaculo
		 */
		public void setEmail(String email) {
			this.email= email;
		}

		/**
		 * Método getter del atributo name
		 * @return nombre del espectaculo
		 */
		public String getRol() {
			return rol;
		}

		/**
		 * Método setter del atributo name <b>post: </b> El nombre del espectaculo ha sido
		 * cambiado con el valor que entra como parámetro
		 * @param name - Nombre del espectaculo
		 */
		public void setRol(String rol) {
			this.rol = rol;
		}

		

	}
