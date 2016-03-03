/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localhost.testjpa.service;

import java.util.logging.Logger;
import localhost.testjpa.AbstractTest;
import localhost.testjpa.model.DetailOne;
import localhost.testjpa.model.MasterOne;
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
public class MasterOneServiceTest extends AbstractTest {
	
	Logger logger = Logger.getLogger(MasterOneServiceTest.class.getName());
	
	@Autowired
	MasterOneService masterOneService;
	
	public MasterOneServiceTest() {
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
		MasterOne model = masterOneService.findOne(id);
		assertModel(model, 3);
	}
		
	@Test
	public void updateDetailsTest() {
		// prepare
		Long id = createModel();
		MasterOne model = masterOneService.findOne(id);
		assertModel(model, 3);
		
		// update
		model.getDetails().add(DetailOne.toDetailOne(model, "extra detail"));
		masterOneService.save(model);
		
		// verify
		MasterOne modelB = masterOneService.findOne(id);
		assertModel(modelB, 4);
	}
	
	
	@Test
	public void saveDetachedTest() {
		// prepare
		Long id = createModel();
		MasterOne model = masterOneService.findOne(id);
		assertModel(model, 3);
		DetailOne detail = model.getDetails().get(0);
		
		
		// update with detached
		MasterOne detached = MasterOne.toMasterOne(model.getTitle());
		detached.setId(id);
		detached.getDetails().add(DetailOne.toDetailOne(null, detail.getId(), "other"));
		masterOneService.saveDetached(detached);
		
		// verify
		MasterOne modelB = masterOneService.findOne(id);
		assertModel(modelB, 3);  // the detached detail will be updated. others details will be untouched
		long count = modelB.getDetails().stream().filter(det -> "other".equals(det.getTitle())).count();
		Assert.assertEquals("updated title", 1, count);
	}
		
	private Long createModel() {
		MasterOne model = MasterOne.toMasterOne("master one test 1");
		model.getDetails().add(DetailOne.toDetailOne(model, "detail one test 1"));
		model.getDetails().add(DetailOne.toDetailOne(model, "detail one test 2"));
		model.getDetails().add(DetailOne.toDetailOne(model, "detail one test 3"));
		return masterOneService.save(model);
	}
	
	private void assertModel(MasterOne model, int expectedSize) {
		Assert.assertNotNull("model", model);
		Assert.assertEquals("details size", expectedSize, model.getDetails().size());
	}
	
}
