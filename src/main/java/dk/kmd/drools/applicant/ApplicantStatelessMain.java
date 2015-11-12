package dk.kmd.drools.applicant;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

public class ApplicantStatelessMain {

	public static void main(String[] args) {
		KieServices kieServices = KieServices.Factory.get();
		KieContainer kContainer = kieServices.getKieClasspathContainer();

		StatelessKieSession kSession = kContainer.newStatelessKieSession("ksession-applicant-stateless");
		Applicant applicant = new Applicant( "Applicant 1", 13 );
		kSession.execute( applicant );
		PrintUtil.print(applicant);
		applicant = new Applicant( "Applicant 2", 19 );
		kSession.execute( applicant );
		PrintUtil.print(applicant);
	}
}
