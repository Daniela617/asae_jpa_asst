package co.edu.unicauca.asae.taller_jpa_asst.taller_jpa_asst;
import co.edu.unicauca.asae.taller_jpa_asst.taller_jpa_asst.repositories.*;
import co.edu.unicauca.asae.taller_jpa_asst.taller_jpa_asst.models.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
@SpringBootApplication
public class TallerJpaAsstApplication implements CommandLineRunner{

	Scanner sc = new Scanner(System.in);

	//repositories
	@Autowired
	private CuestionariosRepository srvCuestionariosBD;

	@Autowired
	private DepartamentosRepository srvDepartamentosBD;

	@Autowired
	private DocentesRepository srvDocentesBD;

	@Autowired
	private PersonasRepository srvPersonasBD;

	@Autowired
	private PreguntasRepository srvPreguntasBD;

	@Autowired
	private RespuestasRepository srvRespuestasBD;

	@Autowired
	private TelefonosRepository srvTelefonosBD;

	@Autowired
	private TipoPreguntasRepository srvTipoPreguntasBD;
	public static void main(String[] args) {
		SpringApplication.run(TallerJpaAsstApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		cargarDatos();	
		registrarDocente();
	}

	private void registrarDocente() {
		System.out.println("---------Registrar Docente--------------");
			Docente objDocente = new Docente();
			System.out.printf("Ingrese el nombre: ");
			String nombre = sc.nextLine();
			objDocente.setNombres(nombre);
			System.out.printf("Ingrese el apellido:");
			String apellido = sc.nextLine();
			objDocente.setApellidos(apellido);
			System.out.printf("Ingrese el tipo de identificacion: ");
			String tipoIdentificacion = sc.nextLine();
			objDocente.setTipoidentificacion(tipoIdentificacion);
			System.out.printf("Ingrese el numero de identificacion: ");
			String numeroIdentificacion = sc.nextLine();
			objDocente.setNumeroidentificacion(numeroIdentificacion);
			System.out.printf("Ingrese el correo: ");
			String correo = sc.nextLine();
			objDocente.setCorreo(correo);
			System.out.printf("Ingrese la vinculacion: ");
			String vinculacion = sc.nextLine();
			objDocente.setVinculacion(vinculacion);

			Telefono objTelefono = new Telefono();
			System.out.printf("Ingrese el numero de telefono: ");
			String numeroTelefono = sc.nextLine();
			objTelefono.setNumero(numeroTelefono);
			System.out.printf("Ingrese el tipo de telefono: ");
			String tipoTelefono = sc.nextLine();
			objTelefono.setTipotelefono(tipoTelefono);
			objDocente.setObjTelefono(objTelefono);
			objTelefono.setObjDocente(objDocente);
			System.out.println("Departamentos disponibles: ");
			srvDepartamentosBD.findAll().forEach(departamento -> {System.out.printf("Id: %d, Nombre: %s, descripcion: %s \n",
					departamento.getIddepartamento(),departamento.getNombre(),departamento.getDescripcion());});

			System.out.println("Ingrese el id del departamento: ");
			int idDep=sc.nextInt();
			sc.nextLine();
			Departamento departamento = srvDepartamentosBD.findById(idDep).get();
			objDocente.getListaDepartamentos().add(departamento);

			System.out.printf("-Datos docente:--");
			System.out.printf("Nombres: %s, Apellidos: %s, Tipo de identificacion: %s, Numero de identificacion: %s, Correo: %s, Vinculacion: %s",
					objDocente.getNombres(),objDocente.getApellidos(),objDocente.getTipoidentificacion(),objDocente.getNumeroidentificacion(),objDocente.getCorreo(),objDocente.getVinculacion());

			srvDocentesBD.save(objDocente);
	
	}

	private void cargarDatos() {
		//Tipo preguntas
		TipoPregunta objTPregunta1 = new TipoPregunta(1,"cultura","Pregutas culturales",null);
		TipoPregunta objTPregunta2 = new TipoPregunta(2,"rutina","Pregutas cotidianas",null);
		this.srvTipoPreguntasBD.save(objTPregunta1);
		this.srvTipoPreguntasBD.save(objTPregunta2);
		//Departamentos
		Departamento objDpto1 = new Departamento(1,"Cauca","Departamento del cauca");
		Departamento objDpto2 = new Departamento(2,"Valle","Departamento del valle");
		this.srvDepartamentosBD.save(objDpto1);
		this.srvDepartamentosBD.save(objDpto2);
	
	}
}
