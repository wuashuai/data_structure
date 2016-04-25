package a1;

// We have checked the Javadoc output and it was OK.

/** NetId: zw377, my437. Time spent: 4 hours, 15 minutes.
 *This is a class to record the information of a PhD
 *
 * @author Zhongru Wu && Mengyuan Yang 
 */

public class PhD {
	private String name = ""; 	//Name of the person with a PhD,
	                            //a String of length > 0
	private int year = 0;		//year PhD was awarded
	private int month = 0;		//month the PhD was awarded. In range 1..12
	private char gender = 'M';	//gender of the person, 'M' means male and 
	                            //'F' means female 
	private PhD advisor1= null; //store the advisor's name, null if unknown
	private PhD advisor2= null; //store the advisor's name, null if unknown
	private int advisee=0;		//number of PhD advisees of this person

	/**
	 * Constructor: an instance for a person with name n, gender g,
	 * PhD year y, and PhD month m. Its advisors are unknown, 
	 * and it has no advisees.
	 * Precondition: n has at least 1 character, m is in 1..12, and g is 'M' 
	 * for male or 'F' for female.
	 */
	public PhD(String n, char g,int y, int m){
		assert n != null;
		assert g == 'M' || g == 'F';
		assert m >= 1 && m <=12;

		this.name = n;
		this.gender = g;
		this.year = y;
		this.month = m;
	}

	/**
	 * Constructor: a PhD with name n, gender g, PhD year y, PhD month m, first
	 * advisor adv, and no second advisor.
	 * Precondition: n has at least 1 char, g is 'F' for female or 'M' for
	 *  male, m is in
	 * 1..12, and adv is not null.
	 */
	public PhD(String n, char g, int y, int m, PhD adv){
		assert n != null;
		assert g == 'M' || g == 'F';
		assert m >= 1 && m <=12;
		assert adv!= null;

		this.name = n;
		this.gender = g;
		this.year = y;
		this.month = m;
		advisor1 = adv;
	}

	/**
	 * Constructor: a PhD with name n, gender g, PhD year y, PhD month m, first
	 * advisor adv1, and second advisor adv2.
	 * Precondition: n has at least 1 char, g is 'F' for female or 'M' for 
	 * male, m is in 1..12, adv1 and adv2 are not null,
	 *  and adv1 and adv2 are different.
	 */
	public PhD(String n, char g, int y, int m, PhD adv1, PhD adv2){
		assert n != null;
		assert g == 'M' || g == 'F';
		assert m >= 1 && m <=12;
		assert adv1!=null && adv2!=null;
		assert adv1!=adv2;

		this.name = n;
		this.gender = g;
		this.year = y;
		this.month = m;
		advisor1 = adv1;
		advisor2 = adv2;
	}

	/** return the name*/
	public String getName() {
		return name;
	}
	/** return the year*/
	public int getYear() {
		return year;
	}
	/** return the year*/
	public int getMonth() {
		return month;
	}
	/** return the year*/
	public boolean isMale(){
		return gender == 'M';
	}
	/** return the year*/
	public PhD getFirstAdvisor() {
		return advisor1;
	}
	/** return the year*/
	public PhD getSecondAdvisor() {
		return advisor2;
	}
	/** return the number of the advisee*/
	public int numAdivsee(){
		return advisee;
	}

	/**
	 * This method is to add a first advisor
	 * @param p is the first advisor
	 * Precondition: Precondition: this person's first advisor is unknown and
	 *  p is not null
	 */
	public void addFirstAdvisor(PhD p){
		assert advisor1==null;
		assert p!=null;
		advisor1 = p;
	}

	/**
	 * This method is to add a second advisor
	 * @param p is the second advisor
	 * Precondition: This person's first advisor is known, their 
	 * second advisor is unknown, p is not null, and p is different from 
	 * this person's first advisor
	 */
	public void addSecondAdvisor(PhD p){
		assert advisor1!=null;
		assert advisor2==null;
		assert p!=null;
		assert p!=advisor1;
		advisor2 = p;
		
	}

	/**
	 * Return value of "this person got their PhD after p did."
	 * Precondition: p is not null.
	 * @return true if person p get the PhD later
	 */
	public boolean isYoungerThan(PhD p){
		assert p!=null;
		return this.year < p.getYear();
	}

	/**
	 * Return value of "this person and p are intellectual siblings.
	 * If p is null, they are not siblings. 
	 * @return true if two people are intellectual siblings"
	 */
	public boolean isPhDSibling(PhD p) {
		return this.advisor1 == p.advisor1 || this.advisor1 == p.advisor2||
				this.advisor2 == p.advisor1 ||this.advisor2 == p.advisor2 
				|| p.advisor1!=null;
	}
}
