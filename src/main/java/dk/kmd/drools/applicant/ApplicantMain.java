package dk.kmd.drools.applicant;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class ApplicantMain {

	public static void main(String[] args) {
		KieServices kieServices = KieServices.Factory.get();
		KieContainer kContainer = kieServices.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-applicant");
		Applicant applicant = new Applicant( "Mr John Smith", 18 );
		kSession.insert( applicant );
		kSession.fireAllRules();
	}
}
