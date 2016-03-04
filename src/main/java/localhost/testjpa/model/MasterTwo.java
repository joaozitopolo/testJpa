/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package localhost.testjpa.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;

/**
 *
 * @author joaozito
 */
@Entity
@Data
public class MasterTwo {

	@Id
	@GeneratedValue
	Long id;
	
	@Column(nullable = false, length = 80)
	String title;
	
	@OneToMany(mappedBy = "parent")
	private List<DetailTwo> details;

	public MasterTwo() {
	}

	public static MasterTwo toMasterTwo(String title) {
		MasterTwo out = new MasterTwo();
		out.title = title;
		out.details = new ArrayList<>();
		return out;
	}

	public void update(MasterTwo data) {
		data.details.forEach(det -> { det.parent = this; }); // bind current items
		this.details.forEach(det -> {
			boolean found = data.details.stream().filter(det2 -> det.id.equals(det2.id)).count() > 0;
			if(! found) { // remove old item, and put into the list to be removed by JPA
				det.setParent(null);
				data.details.add(det);
			}
		});
		this.title = data.title;
		this.details = data.details;
	}

}
