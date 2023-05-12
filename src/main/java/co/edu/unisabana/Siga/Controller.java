package co.edu.unisabana.Siga;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    List<Estudiante> estudianteList= new ArrayList<>();

    @GetMapping(path = "/estudiante/todos")
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
    public Respuesta crearEstudiante(@RequestBody Estudiante estudiante){
        estudiante.setCodigo((int)(Math.random()*1000));
        estudianteList.add(estudiante);
        return new Respuesta("Estudiante ingresado corectamente");
    }

    @DeleteMapping(path = "estudiante/eliminar/{codigo}")
    public Respuesta eliminarEstudiante (@PathVariable int codigo){
        List<Estudiante> busqueda =new ArrayList<>();
        for (Estudiante estudiante: estudianteList){
            if (estudiante.getCodigo()== codigo){
                busqueda.remove(estudiante);
                estudianteList=busqueda;
            }
        }
        return new Respuesta("Estudiante eliminado correctamente");
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

    @PostMapping(path = "/estudiante/actualizar/{codigo}")
    public Estudiante actualizarEstudiante (@RequestBody Estudiante estudianteActulizado ,@PathVariable int codigo){
        estudianteActulizado=new Estudiante();
        String nuevaFacultad=estudianteActulizado.getFacultad();
        String nuevoNombre=estudianteActulizado.getNombre();
        String nuevoGenero =estudianteActulizado.getGenero();
        int nuevoSemestre= estudianteActulizado.getSemestre();
        for (Estudiante estudiante: estudianteList){
            if (estudiante.getCodigo()== codigo){
                estudiante.setFacultad(nuevaFacultad);
                estudiante.setNombre(nuevoNombre);
                estudiante.setGenero(nuevoGenero);
                estudiante.setSemestre(nuevoSemestre);
            }
        }
        return new Estudiante();
    }
}
