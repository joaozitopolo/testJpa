/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localhost.testjpa.service;

import java.util.logging.Logger;
import localhost.testjpa.AbstractTest;
import localhost.testjpa.model.DetailTwo;
import localhost.testjpa.model.MasterTwo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author joaozito
 */
public class MasterTwoServiceTest extends AbstractTest {
	
	private static final String DETAIL_TEST_UPDATED = "detail test updated";
	
	Logger logger = Logger.getLogger(MasterTwoServiceTest.class.getName());
		
	@Autowired
	MasterTwoService masterTwoService;
	
	public MasterTwoServiceTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}

	@Test
	public void saveModelTest() {
		// create
		Long id = createModel();
		
		// verify
		MasterTwo model = masterTwoService.findOne(id);
		assertModel(model, 3);
	}
		
	@Test
	public void updateModelTest() {
		// prepare
		Long id = createModel();
		MasterTwo model = masterTwoService.findOne(id);
		assertModel(model, 3);
		
		// update
		MasterTwo update = MasterTwo.toMasterTwo("updated master two");
		update.setId(id);
		update.getDetails().add(DetailTwo.toDetailTwo(null, "detail test"));
		update.getDetails().add(DetailTwo.toDetailTwo(null, model.getDetails().get(0).getId() , DETAIL_TEST_UPDATED));
		masterTwoService.save(update);
		
		// verify model
		MasterTwo modelB = masterTwoService.findOne(id);
		assertModel(modelB, 2);
		
		// verify texts
		Assert.assertEquals("title updated", update.getTitle(), modelB.getTitle());
		boolean updated = modelB.getDetails().stream().filter(det -> DETAIL_TEST_UPDATED.equals(det.getTitle())).count() > 0;
		Assert.assertTrue("detail title updated", updated);
	}
	
	private Long createModel() {
		MasterTwo model = MasterTwo.toMasterTwo("master one test 1");
		model.getDetails().add(DetailTwo.toDetailTwo(model, "detail test 1"));
		model.getDetails().add(DetailTwo.toDetailTwo(model, "detail test 2"));
		model.getDetails().add(DetailTwo.toDetailTwo(model, "detail test 3"));
		return masterTwoService.save(model);
	}
	
	private void assertModel(MasterTwo model, int expectedSize) {
		Assert.assertNotNull("model", model);
		Assert.assertEquals("details size", expectedSize, model.getDetails().size());
	}
	
}
