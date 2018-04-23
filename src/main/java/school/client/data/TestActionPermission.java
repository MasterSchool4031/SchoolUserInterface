package school.client.data;

import java.security.PermissionCollection;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestActionPermission {

	@Test
	public void testActionPermission_null(){
		try{
		TrainingUpdatePermission perm = new TrainingUpdatePermission("toto",null);
		fail("action null ne soulève pas d'exception");
		}catch(NullPointerException e){
			
		}
	}

	@Test
	public void testActionPermission_toto(){
		try{
		TrainingUpdatePermission perm = new TrainingUpdatePermission("toto","toto");
		fail("action null ne soulève pas d'exception");
		}catch(IllegalArgumentException e){
			
		}
	}

	@Test
	public void testgetActions_add(){
		TrainingUpdatePermission perm = new TrainingUpdatePermission("toto","add");
		assertEquals("add",perm.getActions());
	}

	@Test
	public void testgetActions_delete(){
		TrainingUpdatePermission perm = new TrainingUpdatePermission("toto","delete");
		assertEquals("delete",perm.getActions());
	}

	@Test
	public void testgetActions_all(){
		TrainingUpdatePermission perm = new TrainingUpdatePermission("toto","all");
		assertEquals("all",perm.getActions());
	}

	@Test
	public void testgetActions_delete_add(){
		TrainingUpdatePermission perm = new TrainingUpdatePermission("toto","delete,add");
		assertEquals("delete,add",perm.getActions());
	}
	
	@Test
	public void testImplies_1(){
		TrainingUpdatePermission demande = new TrainingUpdatePermission("toto","add");
		TrainingUpdatePermission accord = new TrainingUpdatePermission("toto","add");
		assertTrue(accord.implies(demande));
	}
	
	@Test
	public void testImplies_2(){
		TrainingUpdatePermission demande = new TrainingUpdatePermission("toto.dede","add");
		TrainingUpdatePermission accord = new TrainingUpdatePermission("toto.*","add");
		assertTrue(accord.implies(demande));
	}
	
	@Test
	public void testImplies_3(){
		TrainingUpdatePermission demande = new TrainingUpdatePermission("toto.dede","add");
		TrainingUpdatePermission accord = new TrainingUpdatePermission("toto.*","all");
		assertTrue(accord.implies(demande));
	}
	
	@Test
	public void testImplies_4(){
		TrainingUpdatePermission demande = new TrainingUpdatePermission("toto.dede","add");
		TrainingUpdatePermission accord = new TrainingUpdatePermission("toto.*","delete,add");
		assertTrue(accord.implies(demande));
	}
	
	@Test
	public void testImplies_5(){
		TrainingUpdatePermission demande = new TrainingUpdatePermission("toto.dede","add");
		TrainingUpdatePermission accord = new TrainingUpdatePermission("toto.*","add,delete");
		assertTrue(accord.implies(demande));
	}
	
	@Test
	public void testImplies_6(){
		TrainingUpdatePermission demande = new TrainingUpdatePermission("toto.dede","all");
		TrainingUpdatePermission accord = new TrainingUpdatePermission("toto.*","add,delete");
		assertTrue(accord.implies(demande));
	}
	
	@Test
	public void testImplies_False_1(){
		TrainingUpdatePermission demande = new TrainingUpdatePermission("toto.dede","add");
		TrainingUpdatePermission accord = new TrainingUpdatePermission("toto.toto","add");
		assertFalse(accord.implies(demande));
	}
	
	@Test
	public void testImplies_False_2(){
		TrainingUpdatePermission demande = new TrainingUpdatePermission("toto","delete");
		TrainingUpdatePermission accord = new TrainingUpdatePermission("toto","add");
		assertFalse(accord.implies(demande));
	}
	
	@Test
	public void testImplies_False_3(){
		TrainingUpdatePermission demande = new TrainingUpdatePermission("toto","add,delete");
		TrainingUpdatePermission accord = new TrainingUpdatePermission("toto.dede","delete");
		assertFalse(accord.implies(demande));
	}
	
	@Test
	public void testEquals_1(){
		TrainingUpdatePermission perm1 = new TrainingUpdatePermission("toto","add,delete");
		TrainingUpdatePermission perm2 = new TrainingUpdatePermission("toto","delete");
		assertFalse(perm1.equals(perm2));
	}
	
	@Test
	public void testEquals_2(){
		TrainingUpdatePermission perm1 = new TrainingUpdatePermission("toto.dede","delete");
		TrainingUpdatePermission perm2 = new TrainingUpdatePermission("toto","delete");
		assertFalse(perm1.equals(perm2));
	}
	
	@Test
	public void testEquals_3(){
		TrainingUpdatePermission perm1 = new TrainingUpdatePermission("toto","de"+"lete");
		TrainingUpdatePermission perm2 = new TrainingUpdatePermission("toto","delete");
		assertTrue(perm1.equals(perm2));
	}
	
	@Test
	public void testNewPermisionCollection_1(){
		TrainingUpdatePermission demande = new TrainingUpdatePermission("toto.dede","add");
		TrainingUpdatePermission accord1 = new TrainingUpdatePermission("toto.*","add");
		TrainingUpdatePermission accord2 = new TrainingUpdatePermission("toto.dede","delete");
		PermissionCollection col = accord1.newPermissionCollection();
		col.add(accord1);
		col.add(accord2);
		assertTrue(col.implies(demande));
	}
	
	@Test
	public void testNewPermisionCollection_2(){
		TrainingUpdatePermission demande = new TrainingUpdatePermission("toto.dede","add");
		TrainingUpdatePermission accord1 = new TrainingUpdatePermission("toto.*","delete");
		TrainingUpdatePermission accord2 = new TrainingUpdatePermission("toto.dede","add");
		PermissionCollection col = accord1.newPermissionCollection();
		col.add(accord1);
		col.add(accord2);
		assertTrue(col.implies(demande));
	}
	
	@Test
	public void testNewPermisionCollection_3(){
		TrainingUpdatePermission demande = new TrainingUpdatePermission("toto.dede","all");
		TrainingUpdatePermission accord1 = new TrainingUpdatePermission("toto.*","delete");
		TrainingUpdatePermission accord2 = new TrainingUpdatePermission("toto.dede","add");
		PermissionCollection col = accord1.newPermissionCollection();
		col.add(accord1);
		col.add(accord2);
		assertTrue(col.implies(demande));
	}
	
	@Test
	public void testNewPermisionCollection_4(){
		TrainingUpdatePermission demande = new TrainingUpdatePermission("toto.dede","add");
		TrainingUpdatePermission accord1 = new TrainingUpdatePermission("toto.dede","delete");
		TrainingUpdatePermission accord2 = new TrainingUpdatePermission("toto.dede","add");
		PermissionCollection col = accord1.newPermissionCollection();
		col.add(accord1);
		col.add(accord2);
		assertTrue(col.implies(demande));
	}
	
	@Test
	public void testNewPermisionCollection_5(){
		TrainingUpdatePermission demande = new TrainingUpdatePermission("toto.dede","add");
		TrainingUpdatePermission accord2 = new TrainingUpdatePermission("toto.dede","add");
		TrainingUpdatePermission accord1 = new TrainingUpdatePermission("toto.dede","delete");
		PermissionCollection col = accord1.newPermissionCollection();
		col.add(accord1);
		col.add(accord2);
		assertTrue(col.implies(demande));
	}
	
	@Test
	public void testNewPermisionCollection_6(){
		TrainingUpdatePermission demande = new TrainingUpdatePermission("toto.dede","all");
		TrainingUpdatePermission accord1 = new TrainingUpdatePermission("toto.*","delete");
		TrainingUpdatePermission accord2 = new TrainingUpdatePermission("toto.dede.bof","add");
		PermissionCollection col = accord1.newPermissionCollection();
		col.add(accord1);
		col.add(accord2);
		assertFalse(col.implies(demande));
	}
	
	@Test
	public void testNewPermisionCollection_7(){
		TrainingUpdatePermission demande = new TrainingUpdatePermission("toto.dede","add");
		TrainingUpdatePermission accord1 = new TrainingUpdatePermission("toto.*","delete");
		PermissionCollection col = accord1.newPermissionCollection();
		col.add(accord1);
		assertFalse(col.implies(demande));
	}
}
