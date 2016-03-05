# testJpa
Test Java JPA with spring-boot

## proposals
This is a basic start project using Spring-Boot and JPA, with the models MasterOne and DetailOne.

## Tests
There are some usual comportaments about tests using JPA. You will be surprised about the correct approach used by JPA to decide 
between write (or not) the list objects.

### MasterOne tests
Single saves and updates using MasterOne->List<DetailOne>

### MasterTwo tests
Saves and updates model, using model.update(updateData) method
The old details are erased, and is replaced with the new details
