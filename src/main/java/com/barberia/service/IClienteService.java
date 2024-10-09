package com.barberia.service;

import org.springframework.http.ResponseEntity;

import com.barberia.dtos.ClienteDTO;
import com.barberia.dtos.ResponseDTO;

/**
 * @author nicope
 * @version 1.0
 * 
 *          Esta interfaz es la capa intermedia entre la capa de presentación y
 *          la capa de acceso a datos. Esta oculta los detalles de
 *          implementación de la capa de acceso a datos.
 * 
 */

public interface IClienteService {

	public ResponseEntity<ResponseDTO> obtenerCliente();
	
	public ResponseEntity<ResponseDTO> findClientById(Integer id);
	
	public ResponseEntity<ResponseDTO> guardarCliente(final ClienteDTO cliente);
	
	public ResponseEntity<ResponseDTO> eliminarCliente(final Integer id);
	
	public ResponseEntity<ResponseDTO> actualizarCliente(Integer id, ClienteDTO clienteDTO);
}
