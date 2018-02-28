/**
 * 
 */
package br.com.camara.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.camara.model.Planeta;

/**
 * @author renan
 *
 */
@RepositoryRestResource(collectionResourceRel = "planeta", path = "planeta" + "")
public interface PlanetaRepository extends MongoRepository<Planeta, String> {

	public Planeta findByName(String name);
}
