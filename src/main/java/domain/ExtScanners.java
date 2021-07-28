package domain;

import javax.persistence.*;

//one type of scanners is exit scanners//
@Entity
@DiscriminatorValue("ExtScanners")
public class ExtScanners extends Scanners{
    
	
	//constructors//
	public ExtScanners() {}
	
	public ExtScanners(String scannerno){
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
