package com.jclscred.service;

import com.jclscred.domain.Cliente;
import com.jclscred.domain.Endereco;
import com.jclscred.dto.ClienteDTO;
import com.jclscred.dto.ClienteNewDTO;
import com.jclscred.repository.ClienteRepository;
import com.jclscred.repository.EnderecoRepository;
import com.jclscred.service.exception.DataIntegrityException;
import com.jclscred.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository repository;
	@Autowired
	EnderecoRepository enderecoRepository;

	public Cliente find(Long id) {
		Optional<Cliente> cliente = repository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. Id: " + id + ", tipo: " + Cliente.class.getName()));
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repository.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	public Cliente update(Cliente obj) {
		Cliente objOld = find(obj.getId());
		updateData(obj, objOld);	
		return repository.save(obj);
	}

	public void delete(Long id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Exclusão não permitida! Este cliente possui relacionamento com outras tabelas.");
		}
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

	public Cliente fromDto(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), objDTO.getCpf(), objDTO. getRg(), objDTO.getRenda(), objDTO.getSenha());
	}

	public Cliente fromDto(ClienteNewDTO objNewDTO) {
		Cliente cliente = new Cliente(null, objNewDTO.getNome(), objNewDTO.getEmail(), objNewDTO.getCpf(), objNewDTO.getRg(), objNewDTO.getRenda(), objNewDTO.getSenha());
		Endereco endereco = new Endereco(null, objNewDTO.getLogradouro(), objNewDTO.getNumero(), objNewDTO.getComplemento(), objNewDTO.getBairro(), objNewDTO.getCep(), cliente);
		cliente.getEnderecos().add(endereco);
		return cliente;
	}
	
	private void updateData(Cliente obj, Cliente objOld) {
		obj.setCpf(objOld.getCpf());
	}
}
