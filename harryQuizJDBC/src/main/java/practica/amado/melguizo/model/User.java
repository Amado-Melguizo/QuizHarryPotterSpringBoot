package practica.amado.melguizo.model;

public class User {
	private Long id;
	private String nombre;
	private String casa;
	private Integer puntos;

	public User() {
	}

	public User(Long id, String nombre, String casa, Integer puntos) {
		this.id = id;
		this.nombre = nombre;
		this.casa = casa;
		this.puntos = puntos;
	}

	public User(String nombre, String casa, Integer puntos) {
		this.nombre = nombre;
		this.casa = casa;
		this.puntos = puntos;
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
	

	public String getCasa() {
		return casa;
	}

	public void setCasa(String casa) {
		this.casa = casa;
	}

	public Integer getPuntos() {
		return puntos;
	}

	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}

	@Override
	public String toString() {
		return "User{" + "id =" + id + ", nombre ='" + nombre + '\'' + ", casa ='" + casa + '\'' + ", puntos =" + puntos + '}';
	}
}
