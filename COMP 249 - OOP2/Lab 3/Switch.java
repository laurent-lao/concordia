public class Switch extends Node {

	private int portsNb;

	// Constructor
	public Switch(int portsNb, int id, String serialNumber)
	{
		this.portsNb = portsNb;
		this.id = id;
		this.serialNumber = serialNumber;
	}

	// Override to String
	public String toString() {
		return ("This Switch's number of ports is: " + portsNb + ", id is: " + id + " and serial number is: ");
	}
}
