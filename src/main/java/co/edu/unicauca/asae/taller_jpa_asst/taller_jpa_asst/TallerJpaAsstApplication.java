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
		//cargarDatos();	
		//registrarDocente();
		crearCuestionario();
		registrarRespuestasCuestionarioDocente();
		listarCuestionarios();
		listarDatosCuestionario();
	}

		private void registrarDocente() {
		System.out.println("--------Creando Docente--------\n");
		Docente objDocente = new Docente();
		System.out.printf("Ingrese el nombre del docente: \n");
		String nombre = sc.nextLine();
		objDocente.setNombres(nombre);
		System.out.printf("Ingrese el apellido del docente: \n");
		String apellido = sc.nextLine();
		objDocente.setApellidos(apellido);
		System.out.printf("Ingrese el tipo de identificacion: \n");
		String tipoIdentificacion = sc.nextLine();
		objDocente.setTipoidentificacion(tipoIdentificacion);
		System.out.printf("Ingrese el numero de identificacion del docente: \n");
		String numeroIdentificacion = sc.nextLine();
		objDocente.setNumeroidentificacion(numeroIdentificacion);
		System.out.printf("Ingrese el correo del docente: \n");
		String correo = sc.nextLine();
		objDocente.setCorreo(correo);
		System.out.printf("Ingrese la vinculacion: \n");
		String vinculacion = sc.nextLine();
		objDocente.setVinculacion(vinculacion);

		Telefono objTelefono = new Telefono();
		System.out.printf("Ingrese el numero de telefono perteneciente al docente: \n");
		String numeroTelefono = sc.nextLine();
		objTelefono.setNumero(numeroTelefono);
		System.out.printf("Ingrese el tipo de telefono: \n");
		String tipoTelefono = sc.nextLine();
		objTelefono.setTipotelefono(tipoTelefono);
		objDocente.setObjTelefono(objTelefono);
		objTelefono.setObjDocente(objDocente);
		int cantDepartamentos = 0;
		do {
			System.out.printf("Ingrese el numero de departamentos a registrar: \n");
			cantDepartamentos = sc.nextInt();
			sc.nextLine();
			if (cantDepartamentos<=0) {
				System.out.printf("La cantidad de departamentos debe ser mayor a cero\n");
			}
		} while (cantDepartamentos<=0);
		listarDepartamentos();
		
		//Agrego departamentos - cambiar
		for (int i = 0; i < cantDepartamentos; i++) {
			Optional<Departamento> departamentoOpcional;
			Departamento objDepartamento= new Departamento();
			do {
				System.out.printf("Ingrese el id del Departamento: \n");
				int idDep = sc.nextInt();
				departamentoOpcional = srvDepartamentosBD.findById(idDep);
				if (!departamentoOpcional.isPresent()) {
					System.out.printf("El departamento con este id no existe\n");
				}
			} while (!departamentoOpcional.isPresent());
			objDepartamento = departamentoOpcional.get();
			objDocente.getListaDepartamentos().add(objDepartamento);
		}
		srvDocentesBD.save(objDocente);
		System.out.println("---------Se agregó el docente--------\n");
		listarDocentes();
	}
	private void crearCuestionario() {
		System.out.println("---------Creando cuestionario--------\n");
		Cuestionario objCuestionario = new Cuestionario();
		System.out.println("Ingrese el titulo: \n");
		String titulo = sc.nextLine();
		objCuestionario.setTitulo(titulo);
		System.out.println("Ingrese la descripcion: \n");
		String desc = sc.nextLine();
		objCuestionario.setDescripcion(desc);
		int cantPreguntas = 0;
		do {
			System.out.println("Ingrese la cantidad de preguntas pertenecientes al cuestionario: \n");
			cantPreguntas = sc.nextInt();
			sc.nextLine();
			if (cantPreguntas<=0) {
				System.out.printf("La cantidad de preguntas debe ser mayor a cero\n");
			}
		} while (cantPreguntas<=0);
		//Agrego preguntas
		for(int i=0; i<cantPreguntas;i++)
		{
			Pregunta objPregunta = new Pregunta();
			System.out.println("\nIngrese el enunciado de la pregunta"+ (i+1));
			String enunciado = sc.nextLine();
			objPregunta.setEnunciado(enunciado);
			//listo los tipos de preguntas
			listarTipoPreguntas();
			TipoPregunta objTipPregunta = new TipoPregunta();
			Optional<TipoPregunta> tipoPreguntaOpcional;
			//Obtengo el tipo de pregunta a partir de su id
			do {
				System.out.printf("Digite el id del tipo de pregunta: \n");
				int idTipoPregunta = sc.nextInt();
				tipoPreguntaOpcional = srvTipoPreguntasBD.findById(idTipoPregunta);
				if (!tipoPreguntaOpcional.isPresent()) {
					System.out.printf("El tipo de pregunta con este id no existe\n");
				}
			} while (!tipoPreguntaOpcional.isPresent());
			System.out.printf("%d",objCuestionario.getIdcuestionario());
			System.out.printf("%d",objPregunta.getIdpregunta());
			System.out.printf("%d",objTipPregunta.getIdtippregunta());
			objTipPregunta = tipoPreguntaOpcional.get();
			objPregunta.setObjTipoPregunta(objTipPregunta);
            objPregunta.setObjCuestionario(objCuestionario);
			objCuestionario.getPreguntas().add(objPregunta);
		}	


		srvCuestionariosBD.save(objCuestionario);
	}
	private void registrarRespuestasCuestionarioDocente() {
		System.out.println("------Registrando respuestas cuestionario-------\n");
		System.out.printf("Docentes disponibles: \n");
		listarDocentes();
		Docente objDocente = new Docente();
		Optional<Docente> docenteOpcional;
		//Busco docente por id
		do {
			System.out.printf("Digite el id del docente: \n");
			int idDocente = sc.nextInt();
			docenteOpcional = srvDocentesBD.findById(idDocente);
			if (!docenteOpcional.isPresent()) { //verifico si existe ese docente
				System.out.printf("El docente con este id no existe\n");
			}
		} while (!docenteOpcional.isPresent());
		objDocente = docenteOpcional.get();
		
		System.out.printf("Cuestionarios disponibles: \n");
		listarCuestionariosSinPreguntas();
		//a partir del cuestionario listo las preguntas que tiene y obtengo el id de la pregunta para asignarle respuestas
		Cuestionario objCuestionario = new Cuestionario();
		Optional<Cuestionario> cuestionarioOpcional;
		//Obtengo el cuestionario a partir de su id
		do {
			System.out.printf("Digite el id del cuestionario: \n");
			int idCuestionario = sc.nextInt();
			cuestionarioOpcional = srvCuestionariosBD.findById(idCuestionario);
			if (!cuestionarioOpcional.isPresent()) {
				System.out.printf("El cuestionario con este id no existe\n");
			}
		} while (!cuestionarioOpcional.isPresent());
		objCuestionario = cuestionarioOpcional.get();
		//listar preguntas del cuestionario hacer procedimiento
		listarPreguntasCuestionario(objCuestionario);
		Pregunta objPregunta = new Pregunta();
		Optional<Pregunta> preguntaOpcional;
		do {
			System.out.printf("Digite el id de la pregunta: \n");
			int idPregunta = sc.nextInt();
			preguntaOpcional = srvPreguntasBD.findById(idPregunta);
			if (!preguntaOpcional.isPresent()) {
				System.out.printf("La pregunta con este id no existe\n");
			}
		} while (!preguntaOpcional.isPresent());
		objPregunta = preguntaOpcional.get();
		int cantRespuestas =0;
		//pregunto por el numero de respuestas a la pregunta
		do {
			System.out.printf("Ingrese el numero de respuestas a registrar: \n");
			cantRespuestas = sc.nextInt();
			sc.nextLine();
			if (cantRespuestas<=0) {
				System.out.printf("La cantidad de respuestas debe ser mayor a cero\n");
			}
		} while (cantRespuestas<=0);

		for (int i = 0; i < cantRespuestas; i++) {
			Respuesta objRespuesta = new Respuesta(); //preguntar por Id
			System.out.printf("\nIngrese la descripcion de la respuesta :"  + (i+1));
			String descripcion = sc.nextLine();
			objRespuesta.setDescripcion(descripcion);
			objRespuesta.setObjDocente(objDocente);
			objRespuesta.setObjPregunta(objPregunta);
			objDocente.getRespuestas().add(objRespuesta);
			objPregunta.getRespuestas().add(objRespuesta);
		}

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
		System.out.println("Cuestionarios disponibles: \n");
		listarCuestionariosSinPreguntas();
		Cuestionario objCuestionario = new Cuestionario();
		do {
			System.out.printf("Digite el id del cuestionario: \n");
			int idCuestionario = sc.nextInt();
			objCuestionario = srvCuestionariosBD.findById(idCuestionario).get();
			if (objCuestionario.getIdcuestionario()==0) {
				System.out.printf("El cuestionario con este id no existe\n");
			}
		} while (objCuestionario.getIdcuestionario()==0);

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
