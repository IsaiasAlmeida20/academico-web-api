package br.com.academico.sala;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class SalaServiceTest {

    private SalaService salaService;

    @Before
    public void init() {
        salaService = new SalaService();
    }

    @Test
    public void teste_recuperar_lista_salas() {
        List<Sala> listaSalas = salaService.listar();
        assertTrue("O retorno do método deve ser uma lista de salas: ",listaSalas instanceof List<?>);
    }

    @Test
    public void test_recuperar_sala_por_id() {
        Sala sala = salaService.recuperar(10);
        assertTrue("O retorno do método recuperar deve ser um objeto Sala: ", sala instanceof Sala);
    }

    @Test
    public void teste_criar_sala() {
        Sala sala = new Sala(1, 30, true,true, false);
        int idSala = salaService.criar(sala);
        assertTrue("O retorno do metodo criar deve ser o ID de uma Sala criado", idSala == (int)idSala);
    }

    @Test
    public void teste_atualizar_sala_por_id(){
        Sala sala = new Sala(1, 30, true,true, false);
        Sala salaAtualizada = salaService.atualizar(16, sala);
        assertTrue("O retorno do método atualizar deve ser um objeto Sala: ", salaAtualizada instanceof Sala);
    }

    @Test
    public void teste_deletar_sala_por_id(){
        int idSalaDeletada = salaService.deletar(587);
        assertTrue("O retorno do método deletar deve ser um ID da Sala deletada: ", idSalaDeletada == (int)idSalaDeletada);
    }
    
}
