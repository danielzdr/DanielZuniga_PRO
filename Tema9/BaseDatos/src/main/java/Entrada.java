import database.DBConector;
import dto.UsuarioDTO;
import model.Usuario;

import java.sql.Connection;

public class Entrada {
    public static void main(String[] args) {
        UsuarioDTO usuarioDTO= new UsuarioDTO();
        Connection connection= DBConector.getConnection();
        Usuario usuario= new Usuario("Pablo", "Soriano","Pablo.soriano@gmail.com","1234", 0, 0);
        usuarioDTO.insertarUsuarios(usuario);
        usuarioDTO.insertarUsariosPS(new Usuario("Pablo", "Soriano", "pablito.sorianito@gmail.com","9876",0,0));
        usuarioDTO.borrarUsuario(500);
    }
}
