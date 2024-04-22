package co.edu.unicauca.asae.taller_jpa_asst.taller_jpa_asst;
import co.edu.unicauca.asae.taller_jpa_asst.taller_jpa_asst.repositories.*;
import co.edu.unicauca.asae.taller_jpa_asst.taller_jpa_asst.models.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import java.util.Optional;
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
		crearCuestionario();
		registrarRespuestasCuestionarioDocente();
		listarCuestionarios();
		listarDatosCuestionario();
	}

		private void registrarDocente() {
		System.out.println("--------Creando Docente 1--------\n");
		Docente objDocente = new Docente();
		objDocente.setNombres("Isabella");
		objDocente.setApellidos("Solarte");
		objDocente.setTipoidentificacion("CC");
		objDocente.setNumeroidentificacion("1002966984");
		objDocente.setCorreo("isabsolarte@unicauca");
		objDocente.setVinculacion("Trabajador");

		Telefono objTelefono = new Telefono();
		objTelefono.setNumero("31254656");
		objTelefono.setTipotelefono("cel");
		objDocente.setObjTelefono(objTelefono);
		objTelefono.setObjDocente(objDocente);

		listarDepartamentos();

		Departamento objDepartamento= new Departamento();
		objDepartamento = srvDepartamentosBD.findById(1).get();
		objDocente.getListaDepartamentos().add(objDepartamento);
		srvDocentesBD.save(objDocente);
		System.out.println("---------Se agregó el docente 1--------\n");

		System.out.println(" ");

		System.out.println("--------Creando Docente 2--------\n");
		Docente objDocente2 = new Docente();
		objDocente2.setNombres("Daniela");
		objDocente2.setApellidos("Riascos");
		objDocente2.setTipoidentificacion("CC");
		objDocente2.setNumeroidentificacion("1002777704");
		objDocente2.setCorreo("driascos@unicauca");
		objDocente2.setVinculacion("Trabajador");

		Telefono objTelefono2 = new Telefono();
		objTelefono2.setNumero("3165566");
		objTelefono2.setTipotelefono("fijo");
		objDocente2.setObjTelefono(objTelefono2);
		objTelefono2.setObjDocente(objDocente2);

		listarDepartamentos();
		Departamento objDepartamento2= new Departamento();
		objDepartamento2 = srvDepartamentosBD.findById(2).get();
		objDocente2.getListaDepartamentos().add(objDepartamento2);
		srvDocentesBD.save(objDocente2);
		System.out.println("---------Se agregó el docente 2--------\n");

		System.out.println("---------------------------------\n");
		listarDocentes();
		System.out.println("---------------------------------\n");

	}
	private void crearCuestionario() {
		
		System.out.println("---------Creando cuestionario--------\n");
		String titulo = "Preguntas de selección multiple";
		String desc = "Preguntas de selección multiple de unica respuesta";
		String enunciado1 = "Cuando murio simon Bolivar";
		String enunciado2 = "Caunto tiempo tiene que trasncurrir para comenzar a quemar calorias";

		System.out.println("Datos a utilizar de Cuestionario: \n");

		System.out.println("Titulo: " + titulo + "\nDescripcion: "+ desc);

		Cuestionario objCuestionario = new Cuestionario();
		objCuestionario.setTitulo(titulo);
		objCuestionario.setDescripcion(desc);
		System.out.println("-----El cuestionario cuenta con 2 preguntas------\n");
		System.out.println("Pregunta 1 \n");
		System.out.println("Enunciado: " + enunciado1 );
		Pregunta objPregunta1 = new Pregunta();
		objPregunta1.setEnunciado(enunciado1);
		System.out.println("Pregunta 2 \n");
		System.out.println("Enunciado: " + enunciado2 );
		Pregunta objPregunta2 = new Pregunta();
		objPregunta2.setEnunciado(enunciado2);
		//creo el tipo de pregunta 
		TipoPregunta objTipPregunta1 = new TipoPregunta();
		TipoPregunta objTipPregunta2 = new TipoPregunta();
		objTipPregunta1 = srvTipoPreguntasBD.findById(1).get();
		objTipPregunta2 = srvTipoPreguntasBD.findById(2).get();
		//le agrego el tipo de pregunta al objeto pregunta
		objPregunta1.setObjTipoPregunta(objTipPregunta2);
		objPregunta2.setObjTipoPregunta(objTipPregunta1);
		//agrego el cuestionario a las preguntas
		objPregunta1.setObjCuestionario(objCuestionario);
		objPregunta2.setObjCuestionario(objCuestionario);
		//agrego las preguntas al cuestionario
		objCuestionario.getPreguntas().add(objPregunta1);
		objCuestionario.getPreguntas().add(objPregunta2);

		srvCuestionariosBD.save(objCuestionario);
	}
	private void registrarRespuestasCuestionarioDocente() {
		System.out.println("------Registrando respuestas cuestionario- 1------\n");
		System.out.printf("Docentes 1: \n");
		listarDocentes();
		Docente objDocente = new Docente();
		objDocente = srvDocentesBD.findById(1).get();
		
		System.out.printf("Cuestionarios disponibles: \n");
		listarCuestionariosSinPreguntas();
		//a partir del cuestionario listo las preguntas que tiene y obtengo el id de la pregunta para asignarle respuestas
		Cuestionario objCuestionario = new Cuestionario();
		objCuestionario = srvCuestionariosBD.findById(1).get();
		
		//listar preguntas del cuestionario hacer procedimiento
		listarPreguntasCuestionario(objCuestionario);
		Pregunta objPregunta = new Pregunta();
		objPregunta = srvPreguntasBD.findById(1).get();
		
		Respuesta objRespuesta = new Respuesta(); //preguntar por Id
		objRespuesta.setDescripcion("Desc respuesta");
		Respuesta objRespuesta2 = new Respuesta(); //preguntar por Id
		objRespuesta2.setDescripcion("Desc respuesta");

			objRespuesta.setObjDocente(objDocente);
			objRespuesta.setObjPregunta(objPregunta);
			objDocente.getRespuestas().add(objRespuesta);
			objPregunta.getRespuestas().add(objRespuesta);

			objRespuesta2.setObjDocente(objDocente);
			objRespuesta2.setObjPregunta(objPregunta);
			objDocente.getRespuestas().add(objRespuesta2);
			objPregunta.getRespuestas().add(objRespuesta2);
		
		//......
		System.out.println("------Registrando respuestas cuestionario 2-------\n");
		System.out.printf("Docentes 2: \n");
		listarDocentes();
		Docente objDocente2 = new Docente();
		objDocente2 = srvDocentesBD.findById(2).get();
		
		System.out.printf("Cuestionarios disponibles: \n");
		listarCuestionariosSinPreguntas();
		//a partir del cuestionario listo las preguntas que tiene y obtengo el id de la pregunta para asignarle respuestas
		Cuestionario objCuestionario2 = new Cuestionario();
		objCuestionario2 = srvCuestionariosBD.findById(2).get();
		
		//listar preguntas del cuestionario hacer procedimiento
		listarPreguntasCuestionario(objCuestionario2);
		Pregunta objPregunta2 = new Pregunta();
		objPregunta2 = srvPreguntasBD.findById(2).get();

		Respuesta objRespuesta3 = new Respuesta(); //preguntar por Id
		objRespuesta3.setDescripcion("Desc respuesta");
		Respuesta objRespuesta4 = new Respuesta(); //preguntar por Id
		objRespuesta4.setDescripcion("Desc respuesta");

			objRespuesta3.setObjDocente(objDocente2);
			objRespuesta3.setObjPregunta(objPregunta2);
			objDocente2.getRespuestas().add(objRespuesta3);
			objPregunta2.getRespuestas().add(objRespuesta3);

			objRespuesta4.setObjDocente(objDocente2);
			objRespuesta4.setObjPregunta(objPregunta2);
			objDocente2.getRespuestas().add(objRespuesta4);
			objPregunta2.getRespuestas().add(objRespuesta4);

	}
	private void listarCuestionarios(){
		System.out.println("---------Listando cuestionarios con preguntas--------------\n");
		srvCuestionariosBD.findAll().forEach(cuestionario -> {
			System.out.println("----Cuestionario---\n");
			System.out.printf("Id: %d\n Titulo: %s\n Descripcion: %s\n", cuestionario.getIdcuestionario(), cuestionario.getTitulo(), cuestionario.getDescripcion());
			System.out.printf("Preguntas: \n");
			cuestionario.getPreguntas().forEach(pregunta -> {
				System.out.printf("Id: %d\n, Enunciado: %s\n", pregunta.getIdpregunta(), pregunta.getEnunciado());
				System.out.printf("Tipo de Pregunta: \n");
				System.out.printf("Id: %d\n Nombre: %s\n Descripcion: %s\n", pregunta.getObjTipoPregunta().getIdtippregunta(), pregunta.getObjTipoPregunta().getNombre(), pregunta.getObjTipoPregunta().getDescripcion());
			});
		});
	}
	private void listarDatosCuestionario() {
        System.out.println("---------Listando datos de cuestionario--------------\n");
        int idCuestionario = 1;
        System.out.println("Id del cuestionario a buscar: \n" + idCuestionario);

        Cuestionario objCuestionario = new Cuestionario();
        objCuestionario = srvCuestionariosBD.findById(idCuestionario).get();

        System.out.printf("------Listando cuestionario con id: %d-------\n", objCuestionario.getIdcuestionario());
        System.out.printf("Titulo cuestionario: %s", objCuestionario.getTitulo());
        System.out.printf("Descripcion cuestionario: %s", objCuestionario.getDescripcion());
        listarPreguntasCuestionario(objCuestionario); //se listan las preguntas con las respuestas y el docente


    }
	private void listarDepartamentos(){
		System.out.println("Departamentos disponibles: ");
		srvDepartamentosBD.findAll().forEach(departamento -> {System.out.printf("Id: %d, Nombre: %s, descripcion: %s \n",
        departamento.getIddepartamento(),departamento.getNombre(),departamento.getDescripcion());});
	}
	private void listarDocentes(){
		System.out.println("------Listando docentes-------\n");
		srvDocentesBD.findAll().forEach(docente -> {
			System.out.printf(" Nombres: %s\n Apellidos: %s\n-----------------\n", docente.getIdpersona(), docente.getNombres(), docente.getApellidos());
			docente.getListaDepartamentos().forEach(departamento -> {
				System.out.printf("Nombre: %s\n", departamento.getNombre());
				
			});
		});
	}
	private void listarTipoPreguntas(){
		System.out.println("---------Listando tipos de preguntas--------------\n");
		srvTipoPreguntasBD.findAll().forEach(TipoPregunta -> {
			System.out.printf("Id: %d\n Nombre: %s\n Descripcion: %s\n",TipoPregunta.getIdtippregunta() ,TipoPregunta.getNombre(),TipoPregunta.getDescripcion());
		});
	}
	private void listarCuestionariosSinPreguntas() {
		System.out.println("------Listando cuestionarios-------\n");
		srvCuestionariosBD.findAll().forEach(cuestionario -> {
			System.out.printf("Id: %d\n Titulo: %s\n Descripcion: %s\n-----------------\n", cuestionario.getIdcuestionario(), cuestionario.getTitulo(), cuestionario.getDescripcion());
		});
	}
	private void listarPreguntasCuestionario(Cuestionario objCuestionario){
		System.out.printf("Las preguntas del cuestionario: %s\n", objCuestionario.getTitulo());
		objCuestionario.getPreguntas().forEach(pregunta -> {
            System.out.printf("Id: %d\n, Enunciado: %s\n, Nombre tipo de pregunta: %s, Descipcion tipo pregunta: %s", pregunta.getIdpregunta(), pregunta.getEnunciado(),pregunta.getObjTipoPregunta().getNombre(),pregunta.getObjTipoPregunta().getDescripcion());
			listarRespuestasPregunta(pregunta);
        });
	}
	private void listarRespuestasPregunta(Pregunta objPregunta){
		System.out.printf("Respuestas de la pregunta: %s\n", objPregunta.getEnunciado());
		objPregunta.getRespuestas().forEach(respuesta -> {
			System.out.printf("Id: %d\n, Descripcion: %s\n", respuesta.getIdrespuesta(), respuesta.getDescripcion());
			System.out.printf("Docente:");
			System.out.printf("Id: %d\n, Nombres: %s\n, Apellidos: %s\n", respuesta.getObjDocente().getIdpersona(), respuesta.getObjDocente().getNombres(), respuesta.getObjDocente().getApellidos());
			System.out.printf("-------------------------");
		});
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
