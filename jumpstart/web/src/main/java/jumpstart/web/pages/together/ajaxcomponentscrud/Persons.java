package jumpstart.web.pages.together.ajaxcomponentscrud;

import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

@Import(stylesheet = "css/together/ajaxcomponentscrud.css")
public class Persons {

	public enum Mode {
		CREATE, REVIEW, UPDATE;
	}

	// Screen fields

	@Property
	// If we use @ActivationRequestParameter instead of @Persist, then our handler for filter form success would have
	// to render more than just the listZone, it would have to render all other links and forms: it would need a zone
	// around the "Create..." link so it could render it; and it would render the editorZone, which would be destructive
	// if the user has been typing into Create or Update. Alternatively, it could use a custom JavaScript callback to
	// update the partialName in all other links and forms - see AjaxResponseRenderer#addCallback(JavaScriptCallback).
	@Persist
	private String partialName;

	@Property
	private Long listPersonId;

	@Property
	@ActivationRequestParameter
	private Mode editorMode;

	@Property
	@ActivationRequestParameter
	private Long editorPersonId;

	// Generally useful bits and pieces

	@InjectComponent
	private Zone listZone;

	@InjectComponent
	private Zone editorZone;

	@Inject
	private Request request;

	@Inject
	private AjaxResponseRenderer ajaxResponseRenderer;

	// The code

	void onActivate() {
		listPersonId = editorPersonId;
	}

	void onToCreate() {
		editorMode = Mode.CREATE;
		editorPersonId = null;

		if (request.isXHR()) {
			ajaxResponseRenderer.addRender(listZone).addRender(editorZone);
		}
	}

	void onSelectedFromList(Long personId) {
		editorMode = Mode.REVIEW;
		editorPersonId = personId;
		listPersonId = personId;

		if (request.isXHR()) {
			ajaxResponseRenderer.addRender(listZone).addRender(editorZone);
		}
	}

	// /////////////////////////////////////////////////////////////////////
	// CREATE
	// /////////////////////////////////////////////////////////////////////

	void onCanceledFromPersonCreate() {
		editorMode = null;
		editorPersonId = null;

		if (request.isXHR()) {
			ajaxResponseRenderer.addRender(editorZone);
		}
	}

	void onCreatedFromPersonCreate(Long personId) {
		editorMode = Mode.REVIEW;
		editorPersonId = personId;
		listPersonId = personId;

        if (request.isXHR()) {
            ajaxResponseRenderer.addRender(listZone).addRender(editorZone);
        }
	}

	// /////////////////////////////////////////////////////////////////////
	// REVIEW
	// /////////////////////////////////////////////////////////////////////

	void onToUpdateFromPersonReview(Long personId) {
		editorMode = Mode.UPDATE;
		editorPersonId = personId;

		if (request.isXHR()) {
			ajaxResponseRenderer.addRender(editorZone);
		}
	}

	void onDeletedFromPersonReview(Long personId) {
		editorMode = null;
		editorPersonId = null;
		listPersonId = null;

		if (request.isXHR()) {
			ajaxResponseRenderer.addRender(listZone).addRender(editorZone);
		}
	}

	// /////////////////////////////////////////////////////////////////////
	// UPDATE
	// /////////////////////////////////////////////////////////////////////

	void onCanceledFromPersonUpdate(Long personId) {
		editorMode = Mode.REVIEW;
		editorPersonId = personId;

		if (request.isXHR()) {
			ajaxResponseRenderer.addRender(editorZone);
		}
	}

	void onUpdatedFromPersonUpdate(Long personId) {
		editorMode = Mode.REVIEW;
		editorPersonId = personId;
		listPersonId = personId;

		if (request.isXHR()) {
			ajaxResponseRenderer.addRender(listZone).addRender(editorZone);
		}
	}

	// /////////////////////////////////////////////////////////////////////
	// GETTERS ETC.
	// /////////////////////////////////////////////////////////////////////

	public boolean isEditorMode(Mode mode) {
		return editorMode == mode;
	}
}
