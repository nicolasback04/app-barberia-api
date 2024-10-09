package com.barberia.serviceImpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.barberia.dtos.ResponseDTO;
import com.barberia.dtos.UsuarioDTO;
import com.barberia.entities.Usuario;
import com.barberia.maps.generales.UsuarioMapper;
import com.barberia.repositories.UsuarioRepository;
import com.barberia.service.IUsuarioService;
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
public class UsuarioServiceImpl implements IUsuarioService {

	private final UsuarioRepository usuarioRepository;
	
	/**
	 * Método que permite obtener todos los ususarios .
	 */
	@Override
	public ResponseEntity<ResponseDTO> obtenerUsuarios() {
		log.info("Inicio método Obtener Users");

		ResponseDTO responseDTO = ResponseDTO.builder().statusCode(HttpStatus.OK.value())
				.message(Constants.CONSULTA_EXITOSAMENTE)
				.objectResponse(UsuarioMapper.INSTANCE.beanListToDtoList(this.usuarioRepository.findAll()))
				.count(this.usuarioRepository.count()).build();

		return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}
	
	
	/**
	 * Método que permite optener los usuario por Id.
	 */
	@Override
	public ResponseEntity<ResponseDTO> findUserById(Integer id) {
		log.info("Inicio metodo para obtener usuario por id");

		Optional<Usuario> userOptional = usuarioRepository.findById(id);

		ResponseDTO responseDTO;
		if (userOptional.isPresent()) {
			UsuarioDTO userDTO = UsuarioMapper.INSTANCE.entityToDto(userOptional.get());
			long count = usuarioRepository.countUserById(id);

			responseDTO = ResponseDTO.builder().statusCode(HttpStatus.OK.value())
					.message(Constants.CONSULTA_EXITOSAMENTE).objectResponse(userDTO).count(count).build();
		} else {
			responseDTO = ResponseDTO.builder().statusCode(HttpStatus.NOT_FOUND.value())
					.message("El Usuario con Id " + id + " no se encuentra.").objectResponse(null).count(0L).build();
		}

		return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO);
	}
	
	/**
	 * Método que permite registrar un usuario.
	 */
	@Override
	public ResponseEntity<ResponseDTO> guardarUsuario(UsuarioDTO usuarioDTO) {
	    System.out.println("guardar usuario -> " + usuarioDTO);

	    Usuario usuario = UsuarioMapper.INSTANCE.dtoToEntity(usuarioDTO);

	    if (usuario.getContrasena() == null || usuario.getEstado() == null) {
	        ResponseDTO responseDTO = ResponseDTO.builder()
	                .statusCode(HttpStatus.BAD_REQUEST.value())
	                .message("La contraseña y el estado son obligatorios.")
	                .objectResponse(null)
	                .count(0L)
	                .build();
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
	    }

	    usuario = this.usuarioRepository.save(usuario);

	    ResponseDTO responseDTO = ResponseDTO.builder()
	            .statusCode(HttpStatus.OK.value())
	            .message(Constants.GUARDADO_EXITOSAMENTE)
	            .objectResponse(usuario) // Puedes devolver el usuario guardado si lo deseas
	            .count(1L)
	            .build();

	    return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
	}
	
	@Override
	public ResponseEntity<ResponseDTO> eliminarUsuario(Integer id) {
		try {
			log.info("Inicio método de eliminar Usuario");
			this.usuarioRepository.deleteById(id);

			return new ResponseEntity<ResponseDTO>(
					Utils.mapearRespuesta(Constants.ELIMINADO_EXITOSAMENTE, HttpStatus.OK.value()), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<ResponseDTO>(
					Utils.mapearRespuesta(Constants.NO_SE_PUEDE_ELIMINAR, HttpStatus.ACCEPTED.value()),
					HttpStatus.ACCEPTED);
		}
	}
	
	@Override
	public ResponseEntity<ResponseDTO> actualizarUsuario(Integer id, UsuarioDTO usuarioDTO) {
		Usuario existingUser = usuarioRepository.findById(id).orElse(null);
		ResponseDTO responseDTO;

		if (existingUser != null) {
			existingUser.setContrasena(usuarioDTO.getContrasena() != null ? usuarioDTO.getContrasena() : existingUser.getContrasena());
			existingUser.setEstado(usuarioDTO.getEstado() != null ? usuarioDTO.getEstado() : existingUser.getEstado());

			UsuarioDTO usuarioDTOR = UsuarioMapper.INSTANCE.entityToDto(usuarioRepository.save(existingUser));

			responseDTO = ResponseDTO.builder().statusCode(HttpStatus.OK.value())
					.message(Constants.ACTUALIZADO_EXITOSAMENTE).objectResponse(usuarioDTOR).count(1L).build();
		} else {
			responseDTO = ResponseDTO.builder().statusCode(HttpStatus.NOT_FOUND.value())
					.message("Usuario no encontrado para el Id: " + id).objectResponse(null).count(0L).build();
		}
		return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO);
	}
}
