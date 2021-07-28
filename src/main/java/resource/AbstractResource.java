package resource;

import javax.persistence.EntityManager;

import persistence.JPAUtil;

public class AbstractResource {

	protected EntityManager getEntityManager() {

		return JPAUtil.getCurrentEntityManager();

		}


}
