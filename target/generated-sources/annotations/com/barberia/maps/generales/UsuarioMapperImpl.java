package com.barberia.maps.generales;

import com.barberia.dtos.UsuarioDTO;
import com.barberia.entities.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-08T15:16:22-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.10 (Eclipse Adoptium)"
)
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public UsuarioDTO entityToDto(Usuario entity) {
        if ( entity == null ) {
            return null;
        }

        UsuarioDTO.UsuarioDTOBuilder usuarioDTO = UsuarioDTO.builder();

        usuarioDTO.id( entity.getId() );
        usuarioDTO.contrasena( entity.getContrasena() );
        usuarioDTO.estado( entity.getEstado() );

        return usuarioDTO.build();
    }

    @Override
    public Usuario dtoToEntity(UsuarioDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Usuario.UsuarioBuilder usuario = Usuario.builder();

        usuario.id( dto.getId() );
        usuario.contrasena( dto.getContrasena() );
        usuario.estado( dto.getEstado() );

        return usuario.build();
    }

    @Override
    public List<UsuarioDTO> beanListToDtoList(List<Usuario> lista) {
        if ( lista == null ) {
            return null;
        }

        List<UsuarioDTO> list = new ArrayList<UsuarioDTO>( lista.size() );
        for ( Usuario usuario : lista ) {
            list.add( entityToDto( usuario ) );
        }

        return list;
    }
}
