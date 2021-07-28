package domain;

import javax.persistence.*;

//one type of ticket is E_card//
@Entity
@DiscriminatorValue("E-card")
public class E_card extends Tickets{
	
	//this column is unique only for E_cards//
	@Column(name="duration")
	private int duration;
	
	//E_cards are associated with passengers with OneToOne//
    @ManyToOne(fetch=FetchType.LAZY, 
            cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name="passengerid")
	private Passengers passenger;
	
	
	//constructors//
	public E_card () {}
	
	public E_card(String ticketno, int charges, int duration){
		this.ticketno=ticketno;
		this.charges=1;
		this.duration=duration;
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
		
		public void setDuration(int duration){
			this.duration = duration;
		}
		
		public int getDuration(){
			return duration;
		}
		
	
}
