package edu.stevens.cs548.clinic.domain;

public interface IProviderDAO {
	
	public static class ProviderExn extends Exception {
		private static final long serialVersionUID = 1L;
		public ProviderExn (String msg) {
			super(msg);
		}
	}
	
	public Provider getProviderByDbId(long id) throws ProviderExn;
	
	public Provider getProviderByNPI (long npi) throws ProviderExn;
	
	public void addProvider (Provider prv) throws ProviderExn;
	
	public void deleteProviders() throws ProviderExn;
	
	public void deleteAll();

}
