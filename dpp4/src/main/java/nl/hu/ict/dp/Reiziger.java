package nl.hu.ict.dp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "reiziger")
public class Reiziger {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reiziger_Sequence")
	@SequenceGenerator(name = "reiziger_Sequence", sequenceName = "REIZIGER_SEQ")
	private int reizigerID;
	private String voorletters;
	private String tussenvoegsel;
	private String achternaam;
	private Date geboortedatum;
	@OneToMany
	private List<OVChipkaart> ov = new ArrayList<OVChipkaart>();

	public List<OVChipkaart> getOv() {
		return ov;
	}

	public void setOv(List<OVChipkaart> ov) {
		this.ov = ov;
	}

	public int getReizigerID() {
		return reizigerID;
	}

	public void setReizigerID(int reizigerID) {
		this.reizigerID = reizigerID;
	}

	public String getVoorletters() {
		return voorletters;
	}

	public void setVoorletters(String voorletters) {
		this.voorletters = voorletters;
	}

	public String getTussenvoegsel() {
		return tussenvoegsel;
	}

	public void setTussenvoegsel(String tussenvoegsel) {
		this.tussenvoegsel = tussenvoegsel;
	}

	public String getAchternaam() {
		return achternaam;
	}

	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}

	public Date getGeboortedatum() {
		return geboortedatum;
	}

	public void setGeboortedatum(Date geboortedatum) {
		this.geboortedatum = geboortedatum;
	}

	public String toString() {
		if (tussenvoegsel == null) {
			return "Reiziger:" + voorletters + ". " + achternaam + " geboren op: " + geboortedatum;
		} else {
			return "Reiziger:" + voorletters + ". " + tussenvoegsel + " " + achternaam + " geboren op: "
					+ geboortedatum;
		}
	}
}