package dk.kmd.drools.applicant;

import java.util.List;

public class PrintUtil {
	public static void print(List<Applicant> applicants) {
		for (Applicant applicant : applicants) {
			print(applicant);
		}
	}

	public static void print(Applicant applicant) {
		System.out.format( "%s age %s - allowed to apply: %s \n",applicant.getName(), applicant.getAge(), applicant.isValid());
	}
}
