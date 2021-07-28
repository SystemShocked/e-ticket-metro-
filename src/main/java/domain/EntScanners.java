package domain;

import javax.persistence.*;



//one type of scanners is entrance scanners//
@Entity
@DiscriminatorValue("EntScanners")
public class EntScanners extends Scanners{
	
	
	//constructors//
    public EntScanners() {}
 	
	public EntScanners(String scannerno){
		this.scannerno = scannerno;
	}
	
	//Setters and getters//
	public Integer getScannerid() {
	        return scannerid;
	}
		
	public void setScannerno(String scannerno){
			this.scannerno = scannerno;
	}
		
	public String getScannerno(){
			return scannerno;
	}
		

	

}
