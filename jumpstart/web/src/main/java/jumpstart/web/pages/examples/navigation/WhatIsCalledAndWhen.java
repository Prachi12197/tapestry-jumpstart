package jumpstart.web.pages.examples.navigation;

import jumpstart.web.pages.Index;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.slf4j.Logger;

public class WhatIsCalledAndWhen {
	
	// Screen fields

	private String name;

	// Generally useful bits and pieces

	@Inject
	private Logger logger;

	// The code

	void pageLoaded() {
		logger.debug("   ");
		logger.debug("pageLoaded()");
	}

	void pageAttached() {
		logger.debug("   ");
		logger.debug("pageAttached()");
	}

	void pageDetached() {
		logger.debug("pageDetached()");
	}

	void onPassivate() {
		logger.debug("...onPassivate()");
	}

	void onActivate() {
		logger.debug("...onActivate()");
	}

	void setupRender() {
		logger.debug("...setupRender()");
	}

	void beginRender() {
		logger.debug("...beginRender()");
	}

	void afterRender() {
		logger.debug("...afterRender()");
	}

	void cleanupRender() {
		logger.debug("...cleanupRender()");
	}

	Object onGoHome() {
		logger.debug("...onGoHome()");
		return Index.class;
	}

	Object onAction() {
		logger.debug("...onAction()");
		return Index.class;
	}

	void onPrepareForRender() {
		logger.debug("...onPrepareForRender()");
	}

	void onPrepare() {
		logger.debug("...onPrepare()");
	}

	void onPrepareForSubmit() {
		logger.debug("...onPrepareForSubmit()");
	}

	void onValidateFromName() {
		logger.debug("...onValidateFromName()");
	}

	void onSelected() {
		logger.debug("...onSelected()");
	}

	void onValidateFromForm() {
		logger.debug("...onValidateFromForm()");
	}

	void onSuccess() {
		logger.debug("...onSuccess()");
	}

	void onFailure() {
		logger.debug("...onFailure()");
	}

	Object onSubmit() {
		logger.debug("...onSubmit()");
		return Index.class;
	}

	public String getName() {
		logger.debug("...getName()");
		return name;
	}

	public void setName(String name) {
		logger.debug("...setName()");
		this.name = name;
	}

	public String getMessage() {
		logger.debug("...getMessage()");
		return "This message is generated by getMessage().";
	}
	
	public String getExampleExpansion1() {
		return "${firstName}";
	}
	
	public String getExampleExpansion2() {
		return "${context:images/icon.png}";
	}
	
	public String getExampleParameters1() {
		return "<t:TextField t:value=\"firstName\"/>";
	}
}