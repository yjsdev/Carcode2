package service;

public class MainClass {

	public static void main(String[] args) {
		//"+"forward "-"backwards "R" right "L" left
		Car car = new CarImpl();
		try {
			car.move("+++R");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("X="+car.getPositionX()+" Y="+car.getPositionY()+" Orientation="+car.getOrientation());
	}
}
