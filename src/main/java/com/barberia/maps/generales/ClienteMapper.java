package com.barberia.maps.generales;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.barberia.dtos.ClienteDTO;
import com.barberia.entities.Cliente;

/**
 * @author nicope
 * @version 1.0
 * 
 *          Clase Mapper de tipo (Cliente) que permite mapear los datos un objeto de tipo 
 *          (ClienteDTO) a uno de tipo (Cliente) o viceversa.
 *          
 *          Esto ayuda a mantener el código limpio y mantenible, ya que separa las 
 *          preocupaciones de las diferentes capas de la aplicación.
 * 
 */

@Mapper
public interface ClienteMapper {

	ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);
	
	ClienteDTO entityToDto(Cliente entity);
	
	@InheritInverseConfiguration
	Cliente dtoToEntity(ClienteDTO dto);
	
	List<ClienteDTO> beanListToDtoList(List<Cliente> lista);
}
