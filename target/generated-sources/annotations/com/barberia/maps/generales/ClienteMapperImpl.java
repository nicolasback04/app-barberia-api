package com.barberia.maps.generales;

import com.barberia.dtos.ClienteDTO;
import com.barberia.entities.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-08T15:16:23-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.10 (Eclipse Adoptium)"
)
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public ClienteDTO entityToDto(Cliente entity) {
        if ( entity == null ) {
            return null;
        }

        ClienteDTO.ClienteDTOBuilder clienteDTO = ClienteDTO.builder();

        clienteDTO.id( entity.getId() );
        clienteDTO.nombre( entity.getNombre() );
        clienteDTO.apellido( entity.getApellido() );
        clienteDTO.estado( entity.getEstado() );

        return clienteDTO.build();
    }

    @Override
    public Cliente dtoToEntity(ClienteDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Cliente.ClienteBuilder cliente = Cliente.builder();

        cliente.id( dto.getId() );
        cliente.nombre( dto.getNombre() );
        cliente.apellido( dto.getApellido() );
        cliente.estado( dto.getEstado() );

        return cliente.build();
    }

    @Override
    public List<ClienteDTO> beanListToDtoList(List<Cliente> lista) {
        if ( lista == null ) {
            return null;
        }

        List<ClienteDTO> list = new ArrayList<ClienteDTO>( lista.size() );
        for ( Cliente cliente : lista ) {
            list.add( entityToDto( cliente ) );
        }

        return list;
    }
}
