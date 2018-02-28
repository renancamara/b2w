/**
 * 
 */
package br.com.camara.resource;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.camara.model.Planeta;
import br.com.camara.service.PlanetaService;

/**
 * @author renan
 *
 */
@RestController
public class PlanetaResource {

	@Autowired
	PlanetaService planetaService;
	
	@PostMapping("/adicionarPlaneta")
	public ResponseEntity<Planeta> inserir(@Valid @RequestBody Planeta planeta, HttpServletResponse response) {
		Planeta planetaSalvo = planetaService.inserirPlaneta(planeta);
		return ResponseEntity.status(HttpStatus.CREATED).body(planetaSalvo);		
	}
	
	@GetMapping("/listarPlanetas")
	public List<Planeta> listarTodos() throws MalformedURLException, URISyntaxException{
		return planetaService.listarPlanetasCadastrados();
	}
	
	@GetMapping("buscarPorNome/{nome}")
	public Planeta buscaPorNome(@PathVariable String nome) throws MalformedURLException, URISyntaxException {
		return planetaService.buscarPlanetaPorNome(nome);
	}
	
	@GetMapping("buscarPorId/{id}")
	public Planeta buscaPorId(@PathVariable String id) throws MalformedURLException, URISyntaxException {
		return planetaService.buscarPlanetaPorId(id);
	}
	
	@DeleteMapping("/removerPlaneta/{idOuNome}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removePlaneta(@PathVariable String idOuNome) {
		planetaService.removerPlaneta(idOuNome);
	}
}
