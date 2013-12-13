package jumpstart.web.pages.examples.wizard;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;

@Import(stylesheet = "css/examples/wizard.css")
public class WizardUsingFormFragmentsSuccess {

	// The activation context

	@Property
	private int approvedAmount;

	@Property
	private String approvedApplicantName;

	// The code

	public void set(int approvedAmount, String approvedApplicantName) {
		// In the real world we would typically receive the credit request's id instead of these fields
		this.approvedAmount = approvedAmount;
		this.approvedApplicantName = approvedApplicantName;
	}

	void onActivate(int approvedAmount, String approvedApplicantName) throws Exception {
		// In the real world we would typically receive the credit request's id instead of these fields
		this.approvedAmount = approvedAmount;
		this.approvedApplicantName = approvedApplicantName;
	}

	Object[] onPassivate() {
		// In the real world we would typically passivate the credit request's id instead of these fields
		return new Object[] { approvedAmount, approvedApplicantName };
	}

	void setupRender() {
		// In the real world we would typically have been passed the persisted credit requests's id, so we'd retrieve
		// the credit request from the database, but in this example we were passed the fields to render.
	}

	Object onRestart() {
		return WizardUsingFormFragments.class;
	}
}
