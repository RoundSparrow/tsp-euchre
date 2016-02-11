package euchre.gui.pictures;

import java.net.URL;

import javax.swing.*;

/**
 * A class for managing card pictures, to make them easier to access. Acts primarily as a library of pictures.
 * 
 * @author Neil MacBay (nmmacbay)
 */
public class PictureManager {
	
	//Variables for the various card images. naming convention of suite-value
	private ImageIcon h9;
	private ImageIcon h10;
	private ImageIcon ha;
	private ImageIcon hj;
	private ImageIcon hq;
	private ImageIcon hk;
	private ImageIcon d9;
	private ImageIcon d10;
	private ImageIcon da;
	private ImageIcon dj;
	private ImageIcon dq;
	private ImageIcon dk;
	private ImageIcon s9;
	private ImageIcon s10;
	private ImageIcon sa;
	private ImageIcon sj;
	private ImageIcon sq;
	private ImageIcon sk;
	private ImageIcon c9;
	private ImageIcon c10;
	private ImageIcon ca;
	private ImageIcon cj;
	private ImageIcon cq;
	private ImageIcon ck;
	//Other card images that are not exactly "cards".
	private ImageIcon back;
	private ImageIcon sidewaysBack;
	private ImageIcon empty;
	private ImageIcon sidewaysEmpty;


	public URL getImageCommonPath(String inFilename)
	{
		URL outPath = getClass().getResource("/euchre/gui/pictures/" + inFilename);
		return outPath;
	}

	public ImageIcon getImageIconForFile(String inFilename)
	{
		return new javax.swing.ImageIcon(getImageCommonPath(inFilename));
	}

	/**
	 * Constructs a new picture manager object.
	 */
	public PictureManager(){
		//assign the various pictures to their variables.
		h9 = getImageIconForFile("9h.png");
		h10 = getImageIconForFile("10h.png");
		ha = getImageIconForFile("ah.png");
		hj = getImageIconForFile("jh.png");
		hq = getImageIconForFile("qh.png");
		hk = getImageIconForFile("kh.png");
		d9 = getImageIconForFile("9d.png");
		d10 = getImageIconForFile("10d.png");
		da = getImageIconForFile("ad.png");
		dj = getImageIconForFile("jd.png");
		dq = getImageIconForFile("qd.png");
		dk = getImageIconForFile("kd.png");
		s9 = getImageIconForFile("9s.png");
		s10 = getImageIconForFile("10s.png");
		sa = getImageIconForFile("as.png");
		sj = getImageIconForFile("js.png");
		sq = getImageIconForFile("qs.png");
		sk = getImageIconForFile("ks.png");
		c9 = getImageIconForFile("9c.png");
		c10 = getImageIconForFile("10c.png");
		ca = getImageIconForFile("ac.png");
		cj = getImageIconForFile("jc.png");
		cq = getImageIconForFile("qc.png");
		ck = getImageIconForFile("kc.png");
		back = getImageIconForFile("back.png");
		sidewaysBack = getImageIconForFile("back_sideways.png");
		empty = getImageIconForFile("empty.png");
		sidewaysEmpty = getImageIconForFile("empty_sideways.png");
				
	}
	
	/**
	 * Given the suite of the card and its value this method will return the corresponding picture. 
	 * 
	 * suit:
	 * h - hearts
	 * d - diamonds
	 * s - spades
	 * c - clubs
	 * b - back-side of a card (not a literal suit)
	 * e - empty card place-holder (not a literal suite, if picked value does not matter)
	 * value:
	 * 9 - 9
	 * 0 - 10
	 * j - jack
	 * q - queen
	 * k - king
	 * a - ace
	 * s - sideways (for use w/ b-suit, if the back-side card is not sideways pass anything but this value)
	 * 
	 * @param suit The suit to find the picture of.
	 * @param value The value to find the picture of.
	 * @return The picture of the card, null if no match found.
	 */
	public ImageIcon getPicture(char suit, char value){
		if (suit == 'e'){
			if (value == 's'){
				return sidewaysEmpty;
			}else{
				return empty;
			}
		}else if (suit == 'b'){
			if (value == 's'){
				return sidewaysBack;
			}else{
				return back;
			}
		}else if (suit == 'h'){
			switch (value) {
			case '9':
				return h9;
			case '0':
				return h10;
			case 'a':
				return ha;
			case 'j':
				return hj;
			case 'q':
				return hq;
			case 'k':
				return hk;
			default:
				break;
			}
		}else if (suit == 'd'){
			switch (value) {
			case '9':
				return d9;
			case '0':
				return d10;
			case 'a':
				return da;
			case 'j':
				return dj;
			case 'q':
				return dq;
			case 'k':
				return dk;
			default:
				break;
			}
		}else if (suit == 's'){
			switch (value) {
			case '9':
				return s9;
			case '0':
				return s10;
			case 'a':
				return sa;
			case 'j':
				return sj;
			case 'q':
				return sq;
			case 'k':
				return sk;
			default:
				break;
			}
		}else if (suit == 'c'){
			switch (value) {
			case '9':
				return c9;
			case '0':
				return c10;
			case 'a':
				return ca;
			case 'j':
				return cj;
			case 'q':
				return cq;
			case 'k':
				return ck;
			default:
				break;
			}
		}
		return null;
	}
}
