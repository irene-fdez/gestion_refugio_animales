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

import es.uvigo.esei.mei.refugio.entidades.Ciudad;
import es.uvigo.esei.mei.refugio.entidades.Tipo;
import es.uvigo.esei.mei.refugio.servicios.TipoService;
import es.uvigo.esei.mei.refugio.entidades.EspeciesAnimales;

@Controller
@RequestMapping("/tipos")
public class TipoController {
	@Autowired
	TipoService tipoService;

	@GetMapping
	public String prepararListarTipos(Model modelo) {
		List<Tipo> tipos = tipoService.buscarTodos();
		modelo.addAttribute("tipos", tipos);
		modelo.addAttribute("nombre", "");
		modelo.addAttribute("raza", "");
		modelo.addAttribute("especie", "");
		return "tipo/listado_tipos";
	}

	@PostMapping
	public String actualizarListarTipo(@RequestParam(required = false) String nombre, @RequestParam(required = false) String raza, 
			@RequestParam(required = false) String especie, Model modelo) {
		
		List<Tipo> tipos;
		if (	((nombre != null) && !nombre.isEmpty())
			||	((raza != null) && !raza.isEmpty()) 
			||	((especie != null) ) 
		){
			tipos = tipoService.buscarNombreRazaEspecie(nombre, raza, especie);
		} else {
			tipos = tipoService.buscarTodos();
		}
		modelo.addAttribute("tipos", tipos);	
		return "tipo/listado_tipos";
	}

	@GetMapping("nuevo")
	public ModelAndView prepararNuevoTipo() {
		Tipo tipo = new Tipo();
		List<Tipo> tipos = tipoService.buscarTodos();

		ModelAndView result = new ModelAndView();
		result.addObject("tipo", tipo);
		result.addObject("tipos", tipos);
		result.addObject("esNuevo", true);
		result.setViewName("tipo/editar_tipo");
		return result;
	}

	@PostMapping("nuevo")
	public String crearTipo(@Valid @ModelAttribute("tipo") Tipo tipo, BindingResult resultado, Model modelo) {
		if (!resultado.hasErrors()) {
			tipoService.crear(tipo);
			return "redirect:/tipos";
		} else {
			modelo.addAttribute("path", "/tipos/nuevo");
            modelo.addAttribute("error", "No se ha podido insertar el tipo");
            modelo.addAttribute("binding_result", resultado.toString());
            modelo.addAttribute("return", "/tipos");
            return "error";
		}
	}

	@GetMapping("{id}")
	public String prepararEditarTipo(@PathVariable("id") Long id, Model modelo) {
		try {
			Tipo tipo = tipoService.buscarPorID(id);
			modelo.addAttribute("tipo", tipo);
			modelo.addAttribute("esNuevo", false);		
			return "tipo/editar_tipo";
		} catch (EntityNotFoundException e) {
			modelo.addAttribute("path", id+"/editar");
			modelo.addAttribute("error", "Tipo no encontrado");
	        modelo.addAttribute("return", "/tipos");
			return "error";
		}
	}

	@PostMapping("{id}")
	public String actualizarTipo(@Valid @ModelAttribute Tipo tipo, BindingResult resultado, Model modelo) {
		if (!resultado.hasErrors()) {
			tipoService.modificar(tipo);
			return "redirect:/tipos";
		} else {
			modelo.addAttribute("path", "/editar");
            modelo.addAttribute("error", "No se ha podido actualizza el tipo");
            modelo.addAttribute("binding_result", resultado.toString());
            modelo.addAttribute("return", "/tipos");
            return "error";
		}
	}
	
	@GetMapping("{id}/detalle")
    public String verCompeticion(@PathVariable("id") Long id, Model modelo) {
        Tipo tipo = tipoService.buscarPorID(id);
        if (tipo != null) {
            modelo.addAttribute(tipo);
            modelo.addAttribute("return", "/tipos");
            return "tipo/detalle_tipo";
        } else {
        	modelo.addAttribute("path", id+ "/detalle");
            modelo.addAttribute("message", "No se ha podido encontrar el tipo");
            modelo.addAttribute("return", "/tipos");
            return "error_message";
        }
    }
	
	@GetMapping("{id}/eliminar")
    public String borrarTipo(@PathVariable("id") Long id, Model modelo) {
        Tipo tipo = tipoService.buscarPorID(id);
        if (tipo != null) {
			tipoService.eliminar(tipo);
			return "redirect:/tipos";
		} else {
			modelo.addAttribute("path", id+ "/eliminar");
			modelo.addAttribute("error", "No se ha podido eliminar el tipo");
			modelo.addAttribute("return", "/tipos");
			return "error";
		}
    }
}
