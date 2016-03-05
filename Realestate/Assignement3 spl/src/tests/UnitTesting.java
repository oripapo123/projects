package tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import repairs.RepairMaterial;
import repairs.RepairTool;
import repairs.Warehouse;

public class UnitTesting{

	/** Object under test.*/
    private WarehouseTest NAMAL_ASHDOD;
 
    /**
     * Set up for a test. creating the warehouse.
     */
    @Before protected void setUp() {
        this.NAMAL_ASHDOD = createWarehouse();
    }
 
    /**
     * This creates the object under test.
     * 
     * @return a {@link Warehouse} instance.
     */
    protected WarehouseTest createWarehouse() {
                return new WarehouseTest(new RepairMaterial[1], new RepairTool[1]);
        }
 
    /**
     * Test method for {@link spl.util.Warehouse#orderTools()}. positive test: 
         * Testing if ordered tools exist and extracted from the warehouse successfully.
     */
    @Test public void testOrderTools() {
        
        assertTrue((NAMAL_ASHDOD.orderTools("Chainsaw", 6)));
    }
 
    /**
     * Test method for {@link spl.util.Warehouse#orderMaterials()}. Positive test:
         * Testing if ordered materials exist and extracted from the warehouse successfully.
     */
    @Test public void testOrderMaterials() {
    	assertTrue((NAMAL_ASHDOD.orderMaterial("Brick", 100)));
    }
    
    /**
     * Test method for {@link spl.util.Warehouse#releaseTools()}. Positive test:
         * Testing if tools are received back and put in the warehouse successfully.
     */
    @Test public void testReleaseTools() {
    	assertTrue((NAMAL_ASHDOD.releaseTools("Chainsaw", 6)));
    }
    
    /**
     * Test method for {@link spl.util.Warehouse#releaseMaterials()}. Positive test:
         * Testing if materials are received back and put in the warehouse successfully.
     */
    @Test public void testReleaseMaterials() {
    	assertTrue((NAMAL_ASHDOD.releaseMaterial("Brick", 100)));
    }
    
    /**
     * Tearing down all test changes for NAMAL_ASHDOD Warehouse object.
     ** Empty, built the tests that will do negative effects on NAMAL_ASHDOD Warehouse object.
     */
    
    @After protected void TearDown() {
        
    }
}
