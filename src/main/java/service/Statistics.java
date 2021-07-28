package service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


import java.util.List;
import persistence.JPAUtil;


//The service of statistics issuance//

public class Statistics {
	
    private EntityManager em;
	
	public Statistics() {
		em = JPAUtil.getCurrentEntityManager();
	}
	
	
	
	
	
	public List<Statistics> getStatistics(int id){
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		List<Statistics> statistics = null;
	
		
		
		statistics = em.createQuery("from Routes r join fetch r.stations s where r.fullness = (select max(r.fullness) from Routes r where r.id like :id)")
				                    .setParameter("id", id).getResultList();
		
		
		tx.commit();
		return statistics; 
		
		
		
				
		
	}
            
        
        
}
