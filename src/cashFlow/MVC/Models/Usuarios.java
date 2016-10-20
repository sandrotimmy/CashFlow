
package cashFlow.MVC.Models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Usuarios implements Serializable {

    @Id
    private int idUsuario;
    @Column(length = 100)
    private String nomeUsuario;
    @Column(length = 10)
    private String nickUsuario;
    @Column(length = 8)
    private String senhaUsuario;

    public Usuarios(int idUsuario, String nomeUsuario, String nickUsuario, String senhaUsuario) {
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.nickUsuario = nickUsuario;
        this.senhaUsuario = senhaUsuario;
    }

    public Usuarios() {
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public void setNickUsuario(String nickUsuario) {
        this.nickUsuario = nickUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getNickUsuario() {
        return nickUsuario;
    }
 
    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
