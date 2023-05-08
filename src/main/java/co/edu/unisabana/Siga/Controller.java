package co.edu.unisabana.Siga;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    List<Estudiante> estudianteList= new ArrayList<>();

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


    @GetMapping(path = "/estudiante")
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

    @DeleteMapping(path = "estudiante/eliminar/{codigo}")
    public String eliminarEstudiante (@PathVariable int codigo){
        List<Estudiante> busqueda =new ArrayList<>();
        for (Estudiante estudiante: estudianteList){
            if (estudiante.getCodigo()== codigo){
                busqueda.remove(estudiante);
                estudianteList=busqueda;
            }
        }
        return "Estudiante eliminado correctamente";
    }

    @GetMapping(path = "/estudiante/busca")
    public List<Estudiante> obtenerEstudiantesPorSemestreYLimite(@RequestParam String facultad, @RequestParam(required=false) Integer limite) {
        List<Estudiante> busqueda = new ArrayList<>();
        for (Estudiante estudiante: estudianteList) {
            if (facultad.equals(estudiante.getFacultad())) {
                busqueda.add(estudiante);
            }
        }
        if (limite != null && limite < busqueda.size()) {
            busqueda = busqueda.subList(0, limite);
        }
        return busqueda;
    }

}
