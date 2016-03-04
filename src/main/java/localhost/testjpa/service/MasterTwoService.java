/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localhost.testjpa.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import localhost.testjpa.model.MasterTwo;
import localhost.testjpa.repository.DetailTwoRep;
import localhost.testjpa.repository.MasterTwoRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author joaozito
 */
@Service
public class MasterTwoService {
	
	@Autowired
	MasterTwoRep masterTwoRep;
	
	@Autowired
	DetailTwoRep detailTwoRep;
	
	@PersistenceContext
	EntityManager em;

	/** updates original object with specific update method on model */
	@Transactional
	public Long save(MasterTwo data) {
		MasterTwo model;
		if(data.getId() == null) {
			model = data;
			masterTwoRep.save(model);
			detailTwoRep.save(model.getDetails());
		} else {
			model = findOne(data.getId());
			model.update(data);
			masterTwoRep.save(model);
			model.getDetails().forEach(det -> {
				if(det.getParent() != null) { // only remanescent items
					detailTwoRep.save(det);
				}
			});
		}
		return model.getId();
	}

	public MasterTwo findOne(Long id) {
		return masterTwoRep.findOne(id);
	}
	
}
