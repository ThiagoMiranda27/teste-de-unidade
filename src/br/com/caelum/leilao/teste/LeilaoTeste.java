package br.com.caelum.leilao.teste;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class LeilaoTeste {
	
	@Test
	public void deveReceberUmLance() {
		Leilao leilao = new Leilao("MacBook Pro 15");
		assertEquals(0, leilao.getLances().size());
		
		leilao.propoe(new Lance(new Usuario("Ronaldo"), 2000));
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
		
	}
	
	@Test
    public void deveReceberVariosLances() {
		Leilao leilao = new Leilao("MacBook Pro 15");
		leilao.propoe(new Lance(new Usuario("Ronaldo"), 2000));
		leilao.propoe(new Lance(new Usuario("Fulano"), 3000));
		
		assertEquals(2, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
		assertEquals(3000.0, leilao.getLances().get(1).getValor(), 0.00001);
    }

	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		Leilao leilao = new Leilao("MacBook Pro 15");
		Usuario ronaldo = new Usuario("Ronaldo");
		leilao.propoe(new Lance(ronaldo, 2000.0));
		leilao.propoe(new Lance(ronaldo, 3000.0));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
		
	}
	
	@Test
	public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario() {
		Leilao leilao = new Leilao("MacBook Pro 15");
		Usuario ronaldo = new Usuario("Ronaldo");
		Usuario sergio = new Usuario("Sergio");
		
		leilao.propoe(new Lance(ronaldo, 2000.0));
		leilao.propoe(new Lance(sergio, 3000.0));
		
		leilao.propoe(new Lance(ronaldo, 4000.0));
		leilao.propoe(new Lance(sergio, 5000.0));
		
		leilao.propoe(new Lance(ronaldo, 6000.0));
		leilao.propoe(new Lance(sergio, 7000.0));
		
		leilao.propoe(new Lance(ronaldo, 8000.0));
		leilao.propoe(new Lance(sergio, 9000.0));
		
		leilao.propoe(new Lance(ronaldo, 10000.0));
		leilao.propoe(new Lance(sergio, 11000.0));
		
		//deve ser ignorado
		leilao.propoe(new Lance(ronaldo, 12000.0));
		
		assertEquals(10, leilao.getLances().size());
		assertEquals(11000.0, leilao.getLances().get(leilao.getLances().size()-1).getValor(),0.00001);
		
	}
}
