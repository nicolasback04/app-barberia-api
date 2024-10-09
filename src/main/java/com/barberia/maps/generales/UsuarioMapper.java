package com.barberia.maps.generales;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.barberia.dtos.UsuarioDTO;
import com.barberia.entities.Usuario;

/**
 * @author nicope
 * @version 1.0
 * 
 *          Clase Mapper de tipo (Usuario) que permite mapear los datos un objeto de tipo 
 *          (UsuarioDTO) a uno de tipo (Usuario) o viceversa.
 *          
 *          Esto ayuda a mantener el código limpio y mantenible, ya que separa las 
 *          preocupaciones de las diferentes capas de la aplicación.
 * 
 */

@Mapper
public interface UsuarioMapper {

	UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);
	
	UsuarioDTO entityToDto(Usuario entity);
	
	@InheritInverseConfiguration
	Usuario dtoToEntity(UsuarioDTO dto);
	
	List<UsuarioDTO> beanListToDtoList(List<Usuario> lista);
}
