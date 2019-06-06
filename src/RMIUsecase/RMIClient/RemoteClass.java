package RMIUsecase.RMIClient;

@RemoteClassName(remoteClassName = "RMIUsecase.RMIServer.TestRMI")
public class RemoteClass implements RMIInterface {
	public String getUppercaseValue() {
		return "An arbitrary string";
	}
}

class RemoteClass2 implements RMIInterface {
	public String getUppercaseValue() {
		return "An arbitrary string";
	}
}
