/**
 * 
 */
package br.com.camara.service;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import br.com.camara.consumer.Swapi;
import br.com.camara.model.Planeta;
import br.com.camara.repository.PlanetaRepository;

/**
 * @author renan
 *
 */
@Service
public class PlanetaService {

	@Autowired
	PlanetaRepository planetaRepository;
	
	public static final String URL = "https://swapi.co/api/planets/?search=";
	
	public Planeta inserirPlaneta(Planeta planeta) {
		if(existePlaneta(planeta.getName())) {
			throw new EmptyResultDataAccessException("Planeta já cadastrado",1);
		}else {
			return planetaRepository.save(planeta);
		}
	}
	
	public boolean existePlaneta(String nome){
		return planetaRepository.findByName(nome) == null ? false : true;
	}

	public List<Planeta> listarPlanetasCadastrados() throws MalformedURLException, URISyntaxException {
		List<Planeta> list = planetaRepository.findAll();
		
		if (list.isEmpty()) {
			throw new EmptyResultDataAccessException("Nenhum planeta encontrado.",1);
		}else {		
			for(Planeta planeta : list) {
				if(!StringUtils.isEmpty(planeta.getName())) {
					insereAparicoesFilmes(planeta);
				}
			}
		}
		return list;
	}

	public Planeta buscarPlanetaPorNome(String nome) throws MalformedURLException, URISyntaxException{
		Planeta planeta = planetaRepository.findByName(nome);
		if (planeta == null) {			
			throw new EmptyResultDataAccessException("Planeta não foi encontrado.",1);			
		}else {
			insereAparicoesFilmes(planeta);
		}
		return planeta;
	}
	
	public Planeta buscarPlanetaPorId(String id) throws MalformedURLException, URISyntaxException{
		Planeta planeta = planetaRepository.findOne(id);
		if (planeta == null) {			
			throw new EmptyResultDataAccessException("Planeta não foi encontrado.",1);			
		}else {
			insereAparicoesFilmes(planeta);
		}
		return planeta;
	}

	public void removerPlaneta(String idOuNome){
		Planeta planeta = planetaRepository.findByName(idOuNome);
		if(planeta != null) {
			planetaRepository.delete(planeta);
		}else {
			planeta = planetaRepository.findOne(idOuNome);
			if(planeta != null) {
				planetaRepository.delete(planeta);
			}else
				throw new EmptyResultDataAccessException("Planeta não foi encontrado.",1);
		}
	}
	
	private Planeta buscarPlanetaSwapi(String nome) throws MalformedURLException, URISyntaxException{		
		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		CloseableHttpClient closeableHttpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
		httpRequestFactory.setHttpClient(closeableHttpClient);
		RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
		URL url = new URL(URL + nome);
		URI uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
		ResponseEntity<Swapi> responseEntity = restTemplate.getForEntity(uri, Swapi.class);
		List<Planeta> listPlanetas = responseEntity.getBody().getResults();
		Planeta p = null;
		if(!listPlanetas.isEmpty()) {
			p = listPlanetas.get(0);
		}
		return p;
	}
	
	public Planeta insereAparicoesFilmes(Planeta planeta) throws MalformedURLException, URISyntaxException {
		Planeta planetaAux = buscarPlanetaSwapi(planeta.getName());
		if(planetaAux != null) {
			planeta.setAparicoes(planetaAux.getFilms().size());
			planeta.setFilms(planetaAux.getFilms());
		}
		return planeta;
	}
}
