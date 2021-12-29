package com.jclscred.service;

import com.jclscred.domain.Cliente;
import com.jclscred.dto.ClienteDTO;
import com.jclscred.dto.ClienteNewDTO;
import com.jclscred.repository.ClienteRepository;
import com.jclscred.service.exception.DataIntegrityException;
import com.jclscred.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	public Cliente findById(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado. Id: " + id + ", tipo: " + Cliente.class.getName()));
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		obj.setSenha(passwordEncoder.encode(obj.getSenha()));

		obj = clienteRepository.save(obj);
		return obj;
	}

	public Cliente update(Cliente obj) {
		Cliente objOld = findById(obj.getId());

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		obj.setSenha(passwordEncoder.encode(obj.getSenha()));

		updateData(obj, objOld);	
		return clienteRepository.save(obj);
	}

	public void delete(Long id) {
		findById(id);
		try {
			clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Exclusão não permitida! Este cliente possui empréstimos contratados.");
		}
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}

	public Cliente fromDto(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), objDTO.getCpf(), objDTO. getRg(),
				objDTO.getRenda(), objDTO.getSenha(), objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(),
				objDTO.getBairro(), objDTO.getCep());
	}

	public Cliente fromDto(ClienteNewDTO objNewDTO) {
		Cliente cliente = new Cliente(null, objNewDTO.getNome(), objNewDTO.getEmail(), objNewDTO.getCpf(), objNewDTO.getRg(), objNewDTO.getRenda(), objNewDTO.getSenha(),
				                       objNewDTO.getLogradouro(), objNewDTO.getNumero(), objNewDTO.getComplemento(), objNewDTO.getBairro(), objNewDTO.getCep());
		return cliente;
	}
	
	private void updateData(Cliente obj, Cliente objOld) {
		obj.setCpf(objOld.getCpf());
	}
}
