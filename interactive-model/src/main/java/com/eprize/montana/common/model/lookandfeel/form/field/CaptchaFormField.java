package com.eprize.montana.common.model.lookandfeel.form.field;

/**
 * 
 * @author Tom.Myers
 *
 */
public class CaptchaFormField extends AbstractFormField {

	/* */
	private static final long serialVersionUID = 1L;

	/* */
	protected byte[] image;
	
	/* */
	protected String response;
	
	/**
	 * 
	 */
	public CaptchaFormField() {
		super();
		this.name = "captcha_field";
		this.displayLabelId = "Please type the characters you see in the box";
		this.content = this.getDefaultContent();
	}

	/**
	 * @return the image
	 */
	public byte[] getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(byte[] image) {
		this.image = image;
	}

	/**
	 * @return the response
	 */
	public String getResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(String response) {
		this.response = response;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.eprize.montana.common.model.lookandfeel.form.field.AbstractFormField#getDefaultContent()
	 */
	public String getDefaultContent() {

		StringBuilder sb = new StringBuilder();
		
		// TODO: TDM: Display image.  There is more analysis/modeling to be done with respect to CAPTCHAs.
		
		// TODO: TDM: When doing internationalization, replace this with a resource bundle look up for the given locale.
		sb.append(this.getDisplayLabelId());
		sb.append("<INPUT type='text' name='");
		sb.append(this.getName());
		sb.append("' />").append(EOL);
		
		return sb.toString();
	}
}