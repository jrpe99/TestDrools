package dk.kmd.drools.applicant;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

public class ApplicantStatefulMain {

	public static void main(String[] args) {
    	KieSession kSession = null;
		try {
			KieServices kieServices = KieServices.Factory.get();
			KieContainer kContainer = kieServices.getKieClasspathContainer();
			kSession = kContainer.newKieSession("ksession-applicant-stateful");
			
			Applicant applicant = new Applicant( "Applicant 1", 15 );
			FactHandle applicant1 = kSession.insert( applicant );

			applicant = new Applicant( "Applicant 2", 19 );
			
			FactHandle applicant2 = kSession.insert( applicant );
			applicant = new Applicant( "Applicant 3", 28 );
			
			FactHandle applicant3 = kSession.insert( applicant );
			applicant = new Applicant( "Applicant 4", 17 );
			
			FactHandle applicant4 = kSession.insert( applicant );

			List<Applicant> allowedApplicants = new ArrayList<>();
			List<Applicant> notAllowedApplicants = new ArrayList<>();
			kSession.setGlobal( "allowedApplicants", allowedApplicants );
			kSession.setGlobal( "notAllowedApplicants", notAllowedApplicants);
			
			System.out.println("Check all applicants");
			kSession.fireAllRules();

			System.out.println("#### Allowed applicants ########");
			PrintUtil.print(allowedApplicants);
			System.out.println("################################");
			System.out.println("#### Not allowed applicants ####");
			PrintUtil.print(notAllowedApplicants);
			System.out.println("################################");
			
			System.out.println("Remove not allowed applicants");
			kSession.delete(applicant1);
			kSession.delete(applicant4);
			
			System.out.println("Update allowed applicants to be able to re-trigger rules");
			kSession.update(applicant2, kSession.getObject(applicant2));
			kSession.update(applicant3, kSession.getObject(applicant3));
			
			allowedApplicants = new ArrayList<>();
			notAllowedApplicants = new ArrayList<>();
			kSession.setGlobal( "allowedApplicants", allowedApplicants );
			kSession.setGlobal( "notAllowedApplicants", notAllowedApplicants);

			System.out.println("Check all applicants again");
			kSession.fireAllRules();

			System.out.println("#### Allowed applicants ########");
			PrintUtil.print(allowedApplicants);
			System.out.println("################################");
			System.out.println("#### Not allowed applicants ####");
			PrintUtil.print(notAllowedApplicants);
			System.out.println("################################");

		} finally {
			if(kSession != null)kSession.dispose();
		}
	}
}
