package domain;

import javax.persistence.*;


//one type of ticket is E_ticket//
@Entity
@DiscriminatorValue("E-ticket")
public class E_ticket extends Tickets{
	
	
	//constructors//
    public E_ticket (){}
	
	
	public E_ticket (String ticketno, int charges){
		this.ticketno=ticketno;
		this.charges=1;
	}
	
	//Sets and Gets for the class.//
		public Integer getTicketid() {
	        return ticketid;
	    }
		
		public void setTicketno (String ticketno){
			this.ticketno = ticketno;
		}
		
		public String getTicketno(){
			return ticketno;
		}
		
		public void setCharges(int charges){
			this.charges = charges;
		}
		
		public int getCharges(){
			return charges;
		}

}
