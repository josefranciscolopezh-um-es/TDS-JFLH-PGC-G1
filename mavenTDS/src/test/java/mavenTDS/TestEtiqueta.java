package mavenTDS;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import appVideo.dominio.Etiqueta;

public class TestEtiqueta {
	
	Etiqueta e;

	@Before
	public void setUp() throws Exception {
		e = new Etiqueta("Música");
		e.setId(13);
	}
	
	@Test
	public void testGetId() {
		assertEquals(e.getId(), 13);
	}
	
	@Test
	public void testSetId() {
		e.setId(2);
		assertEquals(e.getId(), 2);
	}

	@Test
	public void testGetNombre() {
		assertEquals(e.getNombre(), "Música");
	}
	
	@Test
	public void testEquals() {
		Etiqueta e2 = new Etiqueta("Música");
		e2.setId(5);
		assert(e.equals(e2));
	}

}
