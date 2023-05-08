package co.edu.unisabana.Siga;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    List<Estudiante> estudianteList= new ArrayList<>();
/*
    public Controller(){
        this.estudianteList = new ArrayList<>();
        estudianteList.add(new Estudiante("Daniel",2525,3,"Masculino"));
        estudianteList.add(new Estudiante("Javier",2355,4,"Masculino"));
        estudianteList.add(new Estudiante("Juan ",2545,7,"Masculino"));
        estudianteList.add(new Estudiante("Mateo",2655,8,"Masculino"));
    }
*/
    @GetMapping(path = "/estudiantes/todos")
    public List<Estudiante> obtenerEstudiantes(){
        return estudianteList;
    }

    @GetMapping(path = "/estudiantes")
    public List<Estudiante> obtenerEstudiantesPorSemestre (@RequestParam int semestre){
        List<Estudiante> busqueda =new ArrayList<>();
        for (Estudiante estudiante: estudianteList){
            if (estudiante.getSemestre()== semestre){
                busqueda.add(estudiante);
            }
        }
        return busqueda;
    }
    @GetMapping(path = "/estudiante/{codigo}")
    public Estudiante obtenerEstudiantesPorCodigo (@PathVariable int codigo){
        for (Estudiante estudiante: estudianteList){
            if (estudiante.getCodigo()== codigo){
                return estudiante;
            }
        }
        return new Estudiante();
    }


    @GetMapping(path = "/estudiantes")
    public List<Estudiante> obtenerEstudiantesPorSemestreYgener0 (@RequestParam int semestre,@RequestParam String genero){
        List<Estudiante> busqueda =new ArrayList<>();
        for (Estudiante estudiante: estudianteList){
            if (estudiante.getSemestre()== semestre && estudiante.getGenero()==genero){
                busqueda.add(estudiante);
            }
        }
        return busqueda;
    }

    @PostMapping (path = "/estudiante/crear")
    public String crearEstudiante(@RequestBody Estudiante estudiante){
        estudiante.setCodigo((int)(Math.random()*1000));
        estudianteList.add(estudiante);
        return "Estudiante ingresado corectamente";
    }

}
