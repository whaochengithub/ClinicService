package edu.stevens.cs548.clinic.domain;

public enum SpecializationType {
	
	SURGERY("sur"),
	RADIOLOGY("rad"),
	ONCOLOGY("onc");
	
	private String sp;
	private SpecializationType(String sp) {
		this.sp = sp;
	}
	
	public String getTag() {
		return sp;
	}

	public static SpecializationType fromTag(String sp) {
		for (SpecializationType s : SpecializationType.values()) {
			if (s.getTag().equals(sp)) {
				return s;
			}
		}
		throw new IllegalArgumentException("Unrecognized specialization: "+sp);
	}

}
