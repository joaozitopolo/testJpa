/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localhost.testjpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Data;

/**
 *
 * @author joaozito
 */
@Entity
@Data
public class DetailTwo {
	
	@Id
	@GeneratedValue
	Long id;
	
	@ManyToOne
	MasterTwo parent;
	
	
	@Column(nullable = false, length = 80)
	String title;

	public static DetailTwo toDetailTwo(MasterTwo parent, String title) {
		DetailTwo out = new DetailTwo();
		out.parent = parent;
		out.title = title;
		return out;
	}
	
	public static DetailTwo toDetailTwo(MasterTwo parent, Long id, String title) {
		DetailTwo out = new DetailTwo();
		out.parent = parent;
		out.id = id;
		out.title = title;
		return out;
	}
	
}
