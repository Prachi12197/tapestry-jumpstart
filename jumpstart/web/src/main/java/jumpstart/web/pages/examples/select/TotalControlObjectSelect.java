package jumpstart.web.pages.examples.select;

import java.util.List;

import javax.ejb.EJB;

import jumpstart.business.domain.person.Person;
import jumpstart.business.domain.person.iface.IPersonFinderServiceLocal;
import jumpstart.web.encoders.examples.PersonEncoder;
import jumpstart.web.models.examples.select.PersonSelectModel;

import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;

@Import(stylesheet = "css/examples/select.css")
public class TotalControlObjectSelect {
	static private final int MAX_RESULTS = 30;

	// The activation context

	private Long personId;

	// Screen fields

	@Property
	private SelectModel personsModel;

	@Property
	private Person person;

	// Generally useful bits and pieces

	@EJB
	private IPersonFinderServiceLocal personFinderService;

	// The code
	
	void onActivate(EventContext context) {
		if (context.getCount() > 0) {
			personId = context.get(Long.class, 0);
		}
	}

	Long onPassivate() {
		return person == null ? null : person.getId();
	}

	void onPrepare() {
		// Get all persons - ask business service to find them (from the database)
		List<Person> persons = personFinderService.findPersons(MAX_RESULTS);
		
		if (personId != null) {
			person = findPersonInList(personId, persons);
		}

		personsModel = new PersonSelectModel(persons);
	}

	void onValidateFromForm() {
		personId = person == null ? null : person.getId();
	}

	private Person findPersonInList(Long personId, List<Person> persons) {
		for (Person person : persons) {
			if (person.getId().equals(personId)) {
				return person;
			}
		}
		return null;
	}

	public PersonEncoder getPersonEncoder() {
		return new PersonEncoder(personFinderService);
	}

}
