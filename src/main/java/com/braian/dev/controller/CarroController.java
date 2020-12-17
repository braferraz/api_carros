package com.braian.dev.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.braian.dev.entity.Carro;
import com.braian.dev.repository.CarroRepository;

@RestController
public class CarroController {
	@Autowired
	private CarroRepository _carroRepository;
	
	@RequestMapping(value= "/carro", method = RequestMethod.GET)
	public List<Carro> Get(){
		return _carroRepository.findAll();
	}
	
	@RequestMapping(value ="/carro/{id}", method = RequestMethod.GET)
	public ResponseEntity<Carro> GetById(@PathVariable(value = "id")long id){
		Optional<Carro> carro = _carroRepository.findById(id);
		if(carro.isPresent()) {
			return new ResponseEntity<>(carro.get(), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/carro", method = RequestMethod.POST)
	public Carro Post(@Validated @RequestBody Carro carro) {
		return _carroRepository.save(carro);
	}
	
	@RequestMapping(value ="/carro/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Carro> Put(@PathVariable(value="id")long id, @Validated @RequestBody Carro newCarro){
		Optional<Carro> oldCarro = _carroRepository.findById(id);
		if(oldCarro.isPresent()) {
			Carro carro = oldCarro.get();
			carro.setMarca(newCarro.getMarca());
			carro.setModelo(newCarro.getModelo());
			carro.setPlaca(newCarro.getPlaca());
			carro.setAlugado(newCarro.getAlugado());
			_carroRepository.save(carro);
			return new ResponseEntity<Carro>(carro, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
