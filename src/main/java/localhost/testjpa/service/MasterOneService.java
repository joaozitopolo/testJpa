/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localhost.testjpa.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import localhost.testjpa.model.MasterOne;
import localhost.testjpa.repository.DetailOneRep;
import localhost.testjpa.repository.MasterOneRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author joaozito
 */
@Service
public class MasterOneService {
	
	@Autowired
	MasterOneRep masterOneRep;
	
	@Autowired
	DetailOneRep detailOneRep;
	
	@PersistenceContext
	EntityManager em;

	@Transactional
	public Long save(MasterOne model) {
		masterOneRep.save(model);
		detailOneRep.save(model.getDetails());
		return model.getId();
	}

	@Transactional
	public Long saveDetached(MasterOne model) {
		masterOneRep.save(model);
		model.getDetails().forEach(det -> { det.setParent(model); });
		detailOneRep.save(model.getDetails());
		return model.getId();
	}

	public MasterOne findOne(Long id) {
		MasterOne out = masterOneRep.findOne(id);
		return out;
	}
	
}
