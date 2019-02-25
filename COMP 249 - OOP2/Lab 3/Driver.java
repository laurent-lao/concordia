public class Driver {

	public static void main(String [] args)
	{
		// create array of two nodes
		Node [] nodesArray = new Node[2];

		// create a server and stores in array
		nodesArray[0] = new Server(7, 192, "SVR0001");

		// create a switch and stores in array
		nodesArray[1] = new Switch(10, 168, "SWCN0001");

		// print both nodes
		for (int i = 0; i < nodesArray.length; i++)
		{
			System.out.println(nodesArray[i]);
		}
	}
}
