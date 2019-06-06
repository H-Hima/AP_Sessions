package RMIUsecase.RMIServer;

public class TestRMI {
	public String value;
	
	public TestRMI(String value) {
		this.value = value;
	}
	
	public String getUppercaseValue() {
		return value.toUpperCase();
	}
}
