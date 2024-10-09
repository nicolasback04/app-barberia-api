package com.barberia.serviceImpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.barberia.dtos.ClienteDTO;
import com.barberia.dtos.ResponseDTO;
import com.barberia.entities.Cliente;
import com.barberia.maps.generales.ClienteMapper;
import com.barberia.repositories.ClienteRepository;
import com.barberia.service.IClienteService;
import com.barberia.utils.Constants;
import com.barberia.utils.Utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author nicope
 * @version 1.0
 * 
 *          Clase que implementa la interfaz de la lógica de negocio.
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class ClienteServiceImpl implements IClienteService{

	private final ClienteRepository clienteRepository;
	
	/**
	 * Método que permite obtener todos los ususarios .
	 */
	@Override
	public ResponseEntity<ResponseDTO> obtenerCliente() {
		log.info("Inicio método Obtener Users");

		ResponseDTO responseDTO = ResponseDTO.builder().statusCode(HttpStatus.OK.value())
				.message(Constants.CONSULTA_EXITOSAMENTE)
				.objectResponse(ClienteMapper.INSTANCE.beanListToDtoList(this.clienteRepository.findAll()))
				.count(this.clienteRepository.count()).build();

		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}
	
	/**
	 * Método que permite optener los usuario por Id.
	 */
	@Override
	public ResponseEntity<ResponseDTO> findClientById(Integer id) {
		log.info("Inicio metodo para obtener usuario por id");

		Optional<Cliente> clientOptional = clienteRepository.findById(id);

		ResponseDTO responseDTO;
		if (clientOptional.isPresent()) {
			ClienteDTO clienteDTO = ClienteMapper.INSTANCE.entityToDto(clientOptional.get());
			long count = clienteRepository.countClientById(id);

			responseDTO = ResponseDTO.builder().statusCode(HttpStatus.OK.value())
					.message(Constants.CONSULTA_EXITOSAMENTE).objectResponse(clienteDTO).count(count).build();
		} else {
			responseDTO = ResponseDTO.builder().statusCode(HttpStatus.NOT_FOUND.value())
					.message("El Cliente con Id " + id + " no se encuentra.").objectResponse(null).count(0L).build();
		}

		return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO);
	}
	
	/**
	 * Método que permite registrar un usuario.
	 */
	@Override
	public ResponseEntity<ResponseDTO> guardarCliente(ClienteDTO cliente) {
		boolean permiteGuardar = false;
		List<Cliente> listClient = this.clienteRepository.findAll();
		System.out.println("guardar usuario -> " + cliente);
		if (Objects.nonNull(cliente.getId())) {
			for (Cliente u : listClient) {
				if (Objects.nonNull(listClient)) {
					permiteGuardar = true;
				} else {
					permiteGuardar = false;
					break;
				}
			}
		}
		if (permiteGuardar) {
			log.info("Inicio método de guardar usuario");

			cliente.setId(cliente.getId());
			cliente.setNombre(cliente.getNombre());
			cliente.setApellido(cliente.getApellido());
			this.clienteRepository.save(ClienteMapper.INSTANCE.dtoToEntity(cliente));
			log.info("Fin metodo de guardar cliente");

			ResponseDTO responseDTO = ResponseDTO.builder().statusCode(HttpStatus.OK.value())
					.message(Constants.GUARDADO_EXITOSAMENTE).objectResponse(null).count(1L).build();

			return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
		} else {

			ResponseDTO responseDTO = ResponseDTO.builder().statusCode(HttpStatus.ACCEPTED.value())
					.message(Constants.USUARIO_NO_PUEDE_GUARDAR).objectResponse(null).count(0L).build();

			return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDTO);
		}
	}
	
	@Override
	public ResponseEntity<ResponseDTO> eliminarCliente(Integer id) {
		try {
			log.info("Inicio método de eliminar cliente");
			this.clienteRepository.deleteById(id);

			return new ResponseEntity<ResponseDTO>(
					Utils.mapearRespuesta(Constants.ELIMINADO_EXITOSAMENTE, HttpStatus.OK.value()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ResponseDTO>(
					Utils.mapearRespuesta(Constants.NO_SE_PUEDE_ELIMINAR, HttpStatus.ACCEPTED.value()),
					HttpStatus.ACCEPTED);
		}
	}
	
	@Override
	public ResponseEntity<ResponseDTO> actualizarCliente(Integer id, ClienteDTO clienteDTO) {
		Cliente existingClient = clienteRepository.findById(id).orElse(null);
		ResponseDTO responseDTO;

		if (existingClient != null) {
			existingClient.setNombre(clienteDTO.getNombre() != null ? clienteDTO.getNombre() : existingClient.getNombre());
			existingClient.setApellido(clienteDTO.getApellido() != null ? clienteDTO.getApellido() : existingClient.getApellido());
			existingClient.setEstado(clienteDTO.getEstado() != null ? clienteDTO.getEstado() : existingClient.getEstado());

			ClienteDTO clienteDTOR = ClienteMapper.INSTANCE.entityToDto(clienteRepository.save(existingClient));

			responseDTO = ResponseDTO.builder().statusCode(HttpStatus.OK.value())
					.message(Constants.ACTUALIZADO_EXITOSAMENTE).objectResponse(clienteDTOR).count(1L).build();
		} else {
			responseDTO = ResponseDTO.builder().statusCode(HttpStatus.NOT_FOUND.value())
					.message("Usuario no encontrado para el Id: " + id).objectResponse(null).count(0L).build();
		}
		return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO);
	}
}
