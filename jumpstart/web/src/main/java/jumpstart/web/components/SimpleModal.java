// Based on http://readyareyou.blogspot.com.au/2012/11/tapestry5-bootstrap-modal-dialog.html .

package jumpstart.web.components;

import javax.inject.Inject;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.apache.tapestry5.services.ajax.JavaScriptCallback;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

public class SimpleModal implements ClientElement {

	// Parameters

	@Parameter(name = "componentClientId", value = "prop:componentResources.id", defaultPrefix = BindingConstants.LITERAL)
	private String componentClientId;

	// Generally useful bits and pieces

	@Inject
	private JavaScriptSupport javaScriptSupport;

	@Inject
	private AjaxResponseRenderer ajaxResponseRenderer;

	@Inject
	private Request request;

	// The code

	@Override
	public String getClientId() {
		return componentClientId;
	}

	void afterRender() {
		javaScriptSupport.require("simple-modal").invoke("activate").with(componentClientId, new JSONObject());
	}

	public void hide() {
		if (request.isXHR()) {
			ajaxResponseRenderer.addCallback(makeScriptToHideModal());
		}
	}

	private JavaScriptCallback makeScriptToHideModal() {
		return new JavaScriptCallback() {
			public void run(JavaScriptSupport javascriptSupport) {
				javaScriptSupport.require("simple-modal").invoke("hide").with(componentClientId);
			}
		};
	}

}
