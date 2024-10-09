package com.barberia.service;

import org.springframework.http.ResponseEntity;

import com.barberia.dtos.ResponseDTO;
import com.barberia.dtos.UsuarioDTO;

/**
 * @author nicope
 * @version 1.0
 * 
 *          Esta interfaz es la capa intermedia entre la capa de presentación y
 *          la capa de acceso a datos. Esta oculta los detalles de
 *          implementación de la capa de acceso a datos.
 * 
 */

public interface IUsuarioService {

	public ResponseEntity<ResponseDTO> obtenerUsuarios();
	
	public ResponseEntity<ResponseDTO> findUserById(Integer id);
	
	public ResponseEntity<ResponseDTO> guardarUsuario(final UsuarioDTO usuario);
	
	public ResponseEntity<ResponseDTO> eliminarUsuario(final Integer id);
	
	public ResponseEntity<ResponseDTO> actualizarUsuario(Integer id, UsuarioDTO usuarioDTO);
}
