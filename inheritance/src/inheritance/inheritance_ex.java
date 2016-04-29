package inheritance;

class Vehicle{
	public int getName()
	{
		int i=0;
		return i;
	}
}

class Car extends Vehicle{
	public int getName()
	{
		int i=11;
		return i;
	}
	public String getWheel()
	{
		String i="car";
		return i;
	}
}
class Car2 extends Vehicle{
	public int getName()
	{
		int i=11;
		return i;
	}
	public String getWheel()
	{
		String i="car";
		return i;
	}
}
class D extends Car{
	public int getName()
	{
		int i=11;
		return i;
	}
	public String getWheel()
	{
		String i="car";
		return i;
	}
}

public class inheritance_ex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vehicle obj = new Car();
		System.out.println(obj.getName());
		System.out.println(((Car)obj).getName());
		System.out.println(((Car)obj).getWheel());
	//	System.out.println(obj.getWheel());
		Car obj1 = new Car();
		System.out.println(obj1.getName());
		System.out.println(obj1.getWheel());
		
		Vehicle obj2 = new Vehicle();
		System.out.println(obj2.getName());
	//	System.out.println(((Car)obj2).getWheel());
		
		int n=-1;
		System.out.println(n%4);
		int[][] dirShifts = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
		for(int i=0;i<4;i++)
			System.out.println(dirShifts[i][0]+ " "+dirShifts[i][1]);
	}
}
