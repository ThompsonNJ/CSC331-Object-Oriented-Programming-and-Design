package test1;

//DO NOT MAKE ANY CHANGES TO THIS CLASS
//TEST YOUR CODE BY RUNNING THE MAIN METHOD DEFINED BELOW
public class PaintTest {
	public static void main(String[] args) {
		Paint p1 = new Paint("Azure", 78, 90, 234, 34.50);
		Paint p2 = new Paint("Dirt", 58, 110, 214, 24.50);
		Paint p3 = new Paint("Blue", 78, 90, 234, 20.50);

		System.out.println(p1);
		// Must print “name = Azure, red = 78, green =90, blue = 234, price = 34.50”

		System.out.println(p2);
		// Must print “name = Dirt, red = 58, green =110, blue = 214, price = 24.50”

		System.out.println(p3);
		// Must print “name = Blue, red = 78, green =90, blue = 234, price = 20.50”
		
		System.out.println(p1.equals(p2)); // must print false
		System.out.println(p1.equals(p3)); // must print true
		System.out.println(p2.equals(p1)); // must print false
		System.out.println(p1.equals(p1)); // must print true
		System.out.println(p2.equals(p2)); // must print true

		System.out.println(p1.distanceTo(p2)); // must print 34.64101...
		System.out.println(p2.distanceTo(p1)); // must print 34.64101...
		System.out.println(p1.distanceTo(p1)); // must print 0.0
		System.out.println(p2.distanceTo(p2)); // must print 0.0
		System.out.println(p1.distanceTo(p3)); // must print 0.0

		Paint p4 = p1.mix(p2);
		System.out.println(p4);
		// Must print “name = Azure Dirt”, red = 68, green = 100, blue = 224, price = 29.50"

		p1.updatePrice(45.58);
		System.out.println(p1);
		// Must print “name = Azure, red = 78, green = 90, blue = 234, price = 45.58”

	}
}
