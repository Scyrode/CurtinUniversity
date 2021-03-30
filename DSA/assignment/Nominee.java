/***************************************************************************
 *  FILE: Nominee.java
 *  AUTHOR: Ahmad Allahham - 19170251
 *  UNIT: DSA - Assignment
 *  PURPOSE: A class that Contains the information regarding a Nominee
 *  LAST MOD: 14/10/18
 ***************************************************************************/

public class Nominee {

    // CLASS FIELDS:
    private String stateAb;
    private Integer divisionID;
    private String divisionNm;
    private String partyAb;
    private String partyNm;
    private Integer candidateID;
    private String surname;
    private String givenNm;
    private char elected;
    private char historicElected;

    // ALTERNATE CONSTRUCTOR:
    public Nominee(String inStateAb, Integer inDivisionID, String inDivisionNm, String inPartyAb, String inPartyNm, Integer inCandidateID, String inSurname, String inGivenNm, char inElected, char inHistoricElected) {

	this.setStateAb(inStateAb);
	this.setDivisionID(inDivisionID);
	this.setDivisionNm(inDivisionNm);
	this.setPartyAb(inPartyAb);
	this.setPartyNm(inPartyNm);
	this.setCandidateID(inCandidateID);
	this.setSurname(inSurname);
	this.setGivenNm(inGivenNm);
	this.setElected(inElected);
	this.setHistoricElected(inHistoricElected);

    }

    // GETTERS:
    public String getStateAb() {
	return stateAb;
    }

    public Integer getDivisionID() {
	return divisionID;
    }

    public String getDivisionNm() {
	return divisionNm;
    }

    public String getPartyAb() {
	return partyAb;
    }

    public String getPartyNm() {
	return partyNm;
    }

    public Integer getCandidateID() {
	return candidateID;
    }

    public String getSurname() {
	return surname;
    }

    public String getGivenNm() {
	return givenNm;
    }

    public char getElected() {
	return elected;
    }

    public char getHistoricElected() {
	return historicElected;
    }


    // SETTERS:
    public void setStateAb(String inStateAb) {

	if (inStateAb == null) {
	    throw new IllegalArgumentException("invalid input parameter");
	}

	stateAb = inStateAb;

    }

    public void setDivisionID(Integer inDivisionID) {
	divisionID = inDivisionID;
    }
    
    public void setDivisionNm(String inDivisionNm) {

	if (inDivisionNm == null) {
	    throw new IllegalArgumentException("invalid input parameter");
	}

	divisionNm = inDivisionNm;

    }

    public void setPartyAb(String inPartyAb) {

	if (inPartyAb == null) {
	    throw new IllegalArgumentException("invalid input parameter");
	}

	partyAb = inPartyAb;

    }

    public void setPartyNm(String inPartyNm) {

	if (inPartyNm == null) {
	    throw new IllegalArgumentException("invalid input parameter");
	}

	partyNm = inPartyNm;

    }

    public void setCandidateID(Integer inCandidateID) {
	candidateID = inCandidateID;
    }

    public void setSurname(String inSurname) {

	if (inSurname == null) {
	    throw new IllegalArgumentException("invalid input parameter");
	}

	surname = inSurname;

    }

    public void setGivenNm(String inGivenNm) {

	if (inGivenNm == null) {
	    throw new IllegalArgumentException("invalid input parameter");
	}

	givenNm = inGivenNm;

    }

    public void setElected(char inElected) {
	elected = inElected;
    }

    public void setHistoricElected(char inHistoricElected) {
	historicElected = inHistoricElected;
    }


    // TOSTRING():
    public String toString() {

	String str = stateAb + ", " + Integer.toString(divisionID) + ", " + divisionNm + ", " + partyAb + ", " + partyNm + ", " + Integer.toString(candidateID) + ", " + surname + ", " + givenNm + ", " + elected + ", " + historicElected;

	return str;
    }

}
