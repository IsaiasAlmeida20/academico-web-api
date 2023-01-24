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
        listEnderecos.add(new Endereco(49300000L, "Rua a", "Macaé", "Tobias Barreto", "SE"));
        listEnderecos.add(new Endereco(49400000L, "Rua b", "Centro", "Lagarto", "SE"));
        return listEnderecos;
    }

    public Endereco recuperar(Long id) {
        Endereco endereco;
        if(id != 999L) {
            endereco = new Endereco(49300000L, "Rua a", "Macaé", "Tobias Barreto", "SE");
            endereco.setId(id);
        }else {
            throw new EnderecoNaoExisteException();
        }
        return endereco;
    }

    public Long criar(Endereco endereco) {
        if(endereco.getCep() != 88888L) {
            endereco.setId(200L);
        }else {
            throw new CEPEnderecoInvalidoException();
        }
        return endereco.getId();
    }

    public Endereco atualizar(Long id, Endereco endereco) {
        if(id != 999L) {
            endereco.setId(id);
            endereco.setRua("Rua Nova");
        }else {
            throw new EnderecoNaoExisteException();
        }
        return endereco;
    }

    public Long deletar(Long id) {
        return id;
    }

    public Endereco mudarStatus(Long id, EnderecoEnum status) {
        Endereco endereco = new Endereco();
        endereco.setId(id);
        endereco.setStatus(status);
        return endereco;
    }

}
