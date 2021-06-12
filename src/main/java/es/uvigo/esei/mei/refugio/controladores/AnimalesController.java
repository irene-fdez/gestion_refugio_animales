package es.uvigo.esei.mei.refugio.controladores;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import es.uvigo.esei.mei.refugio.entidades.Animal;
import es.uvigo.esei.mei.refugio.servicios.AnimalService;
import es.uvigo.esei.mei.refugio.entidades.Persona;
import es.uvigo.esei.mei.refugio.servicios.PersonaService;
import es.uvigo.esei.mei.refugio.entidades.Tipo;
import es.uvigo.esei.mei.refugio.servicios.TipoService;

@Controller
@RequestMapping("/animales")
public class AnimalesController {
	@Autowired
	AnimalService animalService;
	
	@Autowired
	PersonaService personaService;
	
	@Autowired
	TipoService tipoService;

	@GetMapping
	public String prepararListarAnimales(Model modelo) {
		List<Animal> animales = animalService.buscarTodos();
		modelo.addAttribute("animales", animales);
		modelo.addAttribute("nombre", "");
		return "animal/listado_animales";
	}

	@PostMapping
	public String actualizarListarAnimal(@RequestParam(required = false) String nombre, 
			@RequestParam(required = false) String color, Model modelo) {

		List<Animal> animales;
		if ((nombre != null) && !nombre.isEmpty() || (color != null) && !color.isEmpty() ) {
			animales = animalService.buscarNombreColor(nombre, color);
		} else {
			animales = animalService.buscarTodos();
		}
		modelo.addAttribute("animales", animales);
		return "animal/listado_animales";
	}

	@GetMapping("nuevo")
	public ModelAndView prepararNuevoAnimal() {
		Animal animal = new Animal();
		List<Animal> animales = animalService.buscarTodos();
		List<Persona> personas = personaService.buscarTodos();
		List<Tipo> tipos = tipoService.buscarTodos();

		ModelAndView result = new ModelAndView();
		result.addObject("animal", animal);
		result.addObject("animales", animales);
		result.addObject("personas", personas);
		result.addObject("tipos", tipos);
		result.addObject("esNuevo", true);
		result.setViewName("animal/editar_animal");
		return result;
	}

	@PostMapping("nuevo")
	public String crearAnimal(@Valid @ModelAttribute("animal") Animal animal, BindingResult resultado, Model modelo) {
		System.out.print(animal);
		if (!resultado.hasErrors()) {
			animalService.crear(animal);
			return "redirect:/animales";
		} else {
			modelo.addAttribute("path", "/animales/nuevo");
			modelo.addAttribute("message", "No se ha podido insertar el animal");
			modelo.addAttribute("binding_result", resultado.toString());
			modelo.addAttribute("return", "/animales");
			return "error";
		}
	}

	@GetMapping("{id}")
	public String prepararEditarAnimal(@PathVariable("id") Long id, Model modelo) {
		try {
			Animal animal = animalService.buscarPorId(id);
			List<Persona> personas = personaService.buscarTodos();
			List<Tipo> tipos = tipoService.buscarTodos();
			
			modelo.addAttribute("animal", animal);
			modelo.addAttribute("personas", personas);
			modelo.addAttribute("tipos", tipos);
			modelo.addAttribute("esNuevo", false);
			return "animal/editar_animal";
		} catch (EntityNotFoundException e) {
			modelo.addAttribute("path", id + "/editar");
			modelo.addAttribute("error", "Animal no encontrado");
			modelo.addAttribute("return", "/animales");
			return "error";
		}
	}

	@PostMapping("{id}")
	public String actualizarAnimal(@Valid @ModelAttribute Animal animal, BindingResult resultado, Model modelo) {

		if (!resultado.hasErrors()) {
			animalService.modificar(animal);
			return "redirect:/animales";
		} else {
			modelo.addAttribute("path", "/editar");
			modelo.addAttribute("error", "No se ha podido actualizar el animal");
			modelo.addAttribute("binding_result", resultado.toString());
            modelo.addAttribute("return", "/acogidas");
			return "error";
		}
	}

	@GetMapping("{id}/detalle")
	public String verAnimal(@PathVariable("id") Long id, Model modelo) {
		Animal animal = animalService.buscarPorId(id);
		if (animal != null) {
			modelo.addAttribute(animal);
			modelo.addAttribute("return", "/animales");
			return "animal/detalle_animal";
		} else {
			modelo.addAttribute("path",  id + "/detalle");
			modelo.addAttribute("message", "No se ha podido encontrar el animal");
			modelo.addAttribute("return", "/animales");
			return "error_message";
		}
	}

	@GetMapping("{id}/eliminar")
	public String borrarAnimal(@PathVariable("id") Long id, Model modelo) {
		Animal animal = animalService.buscarPorId(id);
		if (animal != null) {
			animalService.eliminar(animal);
			return "redirect:/animales";
		} else {
			modelo.addAttribute("path", id + "/eliminar");
			modelo.addAttribute("error", "No se ha podido eliminar el animal");
			modelo.addAttribute("return", "/animales");
			return "error";
		}
	}
}
