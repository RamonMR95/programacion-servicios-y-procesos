import java.io.Serializable;

public class Persona implements Serializable {

	private static final long serialVersionUID = 1655652555471572090L;
	private String nombre;
	private String asignatura;
	private int nota;

	public Persona(String nombre, String asignatura, int nota) {
		this.nombre = nombre;
		this.asignatura = asignatura;
		this.nota = nota;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", asignatura=" + asignatura + ", nota=" + nota + "]";
	}

}
