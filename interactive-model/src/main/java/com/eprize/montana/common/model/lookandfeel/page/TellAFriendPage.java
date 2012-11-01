package com.eprize.montana.common.model.lookandfeel.page;

import com.eprize.montana.common.model.lookandfeel.form.TellAFriendForm;


/**
 * 
 * @author Tom.Myers
 *
 */
public class TellAFriendPage extends AbstractPage {

	/* */
	private static final long serialVersionUID = 1L;
	
	/* */
	private TellAFriendForm tellAFriendForm;
	
	/**
	 * 
	 */
	public TellAFriendPage() {
		super();
	}

	/**
	 * @return the tellAFriendForm
	 */
	public TellAFriendForm getTellAFriendForm() {
		return tellAFriendForm;
	}

	/**
	 * @param tellAFriendForm the tellAFriendForm to set
	 */
	public void setTellAFriendForm(TellAFriendForm tellAFriendForm) {
		this.tellAFriendForm = tellAFriendForm;
	}

	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.lookandfeel.page.AbstractPage#getDefaultContent()
	 */
	public String getDefaultContent() {

		StringBuilder sb = new StringBuilder();
		
		sb.append("<H1>Tell your friends</H1>").append(EOL);
		sb.append("Fill out the form below to tell some friends about this promotion. They'll receive the email from you.").append(EOL);		
		sb.append("</P>").append(EOL);
		
		sb.append(this.tellAFriendForm.getContent());
		
		return sb.toString();
	}
}