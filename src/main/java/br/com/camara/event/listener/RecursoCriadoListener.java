/**
 * 
 */
package br.com.camara.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.camara.event.RecursoCriadoEvent;

/**
 * @author renan
 *
 */
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent>{

	@Override
	public void onApplicationEvent(RecursoCriadoEvent recursoCriadoEvent) {
		HttpServletResponse response = recursoCriadoEvent.getResponse();
		String nome = recursoCriadoEvent.getNome();
		
		adicionarHeaderLocation(response, nome);
	}

	private void adicionarHeaderLocation(HttpServletResponse response, String nome) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{nome}")
				.buildAndExpand(nome).toUri();
		response.setHeader("Location", uri.toASCIIString());
	}
}
