
public class Rectangle {

	private double width;
	private double height;
	
	public Rectangle(double w, double h)
	{
		width = w;
		height = h;
	}
	
	public Rectangle(Rectangle rectangle)
	{
		this.width = rectangle.width;
		this.height = rectangle.height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
	public double getArea()
	{
		return width * height;
	}
	
	public static double getTotalArea(Rectangle[] rectangles)
	{
		// 	Storing area size
		double areaSum = 0;
		//	read elements
		for (int i = 0; i < rectangles.length; i++)
		{
			areaSum += rectangles[i].getArea();
		}
		
		return areaSum;
	}

	public String toString() {
		return "Rectangle's width: " + width + ", height: " + height + ", and area: " + getArea();
	}
	
	
}
