/**
 * 
 */
package com.eprize.montana.common.model.lookandfeel.content;

/**
 * 
 * @author tmyers
 * 
 */
public class PromotionImageCopy extends AbstractPromotionCopy {

	/* */
	private static final long serialVersionUID = 1L;
	
	/* */
	protected String filename;
	
	/* */
	protected String alternateText;
	
	/* */
	protected byte[] imageContentByteArray;

	/**
	 * 
	 */
	public PromotionImageCopy() {
		super();
	}

	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * @return the alternateText
	 */
	public String getAlternateText() {
		return alternateText;
	}

	/**
	 * @param alternateText the alternateText to set
	 */
	public void setAlternateText(String alternateText) {
		this.alternateText = alternateText;
	}

	/**
	 * @return the imagecontentByteArray
	 */
	public byte[] getImageContentByteArray() {
		return imageContentByteArray;
	}

	/**
	 * @param imageCcontentByteArray the imageContentByteArray to set
	 */
	public void setImageContentByteArray(byte[] imageContentByteArray) {
		this.imageContentByteArray = imageContentByteArray;
	}
}