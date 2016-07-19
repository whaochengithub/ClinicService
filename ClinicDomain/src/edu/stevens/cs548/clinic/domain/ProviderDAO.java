package edu.stevens.cs548.clinic.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class ProviderDAO implements IProviderDAO {

	private EntityManager em;
	private TreatmentDAO treatmentDAO;
	
	//5. a. Return the provider for a primary key.
	public Provider getProviderByDbId(long id) throws ProviderExn {
		Provider pv = em.find(Provider.class, id);
		if (pv == null) {
			throw new ProviderExn("Provider not found: primary key = " + id);
		} else {
			pv.setTreatmentDAO(this.treatmentDAO);
			return pv;
		}
	}

	//5. b. Return a provider aggregate given a NPI
	public Provider getProviderByNPI(long npi) throws ProviderExn {
		TypedQuery<Provider> query = 
				em.createNamedQuery("SearchProviderByNPI", Provider.class)
				.setParameter("npi", npi);
		List<Provider> providers = query.getResultList();
		if(providers.size() > 1)
			throw new ProviderExn("Duplicate provider records: nationl provider id = " +npi);
		else if (providers.size() < 1)
			throw new ProviderExn("Provider not found: provider id = " + npi);
		else {
			Provider pv = providers.get(0);
			pv.setTreatmentDAO(this.treatmentDAO);
			return pv;
		}
	}

	//4. Add a provider to a clinic.
	public void addProvider(Provider prv) throws ProviderExn {
		long npi = prv.getNpi();
		TypedQuery<Provider> query = 
				em.createNamedQuery("SearchProviderByNPI", Provider.class).setParameter("npi", npi);
		List<Provider> providers = query.getResultList();
		if (providers.size() < 1) {
			em.persist(prv);
			prv.setTreatmentDAO(this.treatmentDAO);
		}
		else {
			Provider prv2 = providers.get(0);
			throw new ProviderExn("Insertion: provider with provider id ("+ npi 
					+") already exists.\n** Name: "+ prv2.getName());
		}
	}
	
	public void deleteProviders() throws ProviderExn {
		TypedQuery<Provider> query = em.createQuery("select pv from Provider pv", Provider.class);
		List<Provider> prvs = query.getResultList();
		if (prvs.size() < 1)
			throw new ProviderExn("No providers to delete.");
		else
			for(Provider prv : prvs){
				em.remove(prv);
			}
	}
	
	public void deleteAll() {
		Query update = em.createQuery("delete from Provider pv");
		update.executeUpdate();
	}

	public ProviderDAO (EntityManager em) {
		this.em = em;
		this.treatmentDAO = new TreatmentDAO(em);
	}

}
