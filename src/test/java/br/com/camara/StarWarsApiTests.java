package br.com.camara;
/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.camara.model.Planeta;
import br.com.camara.repository.PlanetaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StarWarsApiTests {

    @Autowired
    PlanetaRepository planetaRepository;

    Planeta planeta1, planeta2, planeta3;

    @Before
    public void setUp() {

        planetaRepository.deleteAll();

        planeta1 = planetaRepository.save(new Planeta("Planeta1", "Clima1", "Terreno1"));
        planeta2 = planetaRepository.save(new Planeta("Planeta2", "Clima2", "Terreno2"));
        planeta3 = planetaRepository.save(new Planeta("Planeta3", "Clima3", "Terreno3"));
    }

    @Test
    public void setsIdOnSave() {

        Planeta planeta1 = planetaRepository.save(new Planeta("Planeta1", "Clima1", "Terreno1"));

        assertThat(planeta1.getName()).isNotNull();
    }

    @Test
    public void findByNome() {

        List<Planeta> result = (List<Planeta>) planetaRepository.findByName("Planeta3");

        assertThat(result).hasSize(1).extracting("Nome").contains("Planeta3");
    }

    @Test
    public void findsByExample() {

        Planeta planeta = new Planeta(null, "Planeta2","");

        List<Planeta> result = planetaRepository.findAll(Example.of(planeta));

        assertThat(result).hasSize(2).extracting("Nome").contains("Planeta1", "Planeta2");
    }
}