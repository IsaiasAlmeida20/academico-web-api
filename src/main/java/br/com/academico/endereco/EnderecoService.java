package br.com.academico.endereco;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.jvnet.hk2.annotations.Service;

@Service 
@Named("enderecoservicedefaut")
public class EnderecoService implements IEnderecoService{
    
    public List<Endereco> listar() {
        List<Endereco> listEnderecos = new ArrayList<Endereco>();
        listEnderecos.add(new Endereco(49300000, "Rua a", "Macaé", "Tobias Barreto", "SE"));
        listEnderecos.add(new Endereco(49400000, "Rua b", "Centro", "Lagarto", "SE"));
        return listEnderecos;
    }

    public Endereco recuperar(int id) {
        Endereco endereco;
        if(id != 999) {
            endereco = new Endereco(49300000, "Rua a", "Macaé", "Tobias Barreto", "SE");
            endereco.setId(id);
        }else {
            throw new EnderecoNaoExisteException();
        }
        return endereco;
    }

    public int criar(Endereco endereco) {
        if(endereco.getCEP() != 88888) {
            endereco.setId(200);
        }else {
            throw new CEPEnderecoInvalidoException();
        }
        return endereco.getId();
    }

    public Endereco atualizar(int id, Endereco endereco) {
        if(id != 999) {
            endereco.setId(id);
            endereco.setRua("Rua Nova");
        }else {
            throw new EnderecoNaoExisteException();
        }
        return endereco;
    }

    public int deletar(int id) {
        return id;
    }

    public Endereco mudarStatus(int id, EnderecoEnum status) {
        Endereco endereco = new Endereco();
        endereco.setId(id);
        endereco.setStatus(status);
        return endereco;
    }

}
