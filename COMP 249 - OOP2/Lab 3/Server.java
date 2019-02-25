public class Server extends Node {

	private int VmNb;

	// Constructor
	public Server(int VmNb, int id, String serialNumber)
	{
		this.VmNb = VmNb;
		this.id = id;
		this.serialNumber = serialNumber;
	}

	// Override toString
	public String toString() {
		return ("This Server's number of Virtual machine is: " + VmNb + ", id is: " + id + " and serial number is: ");
	}
}
