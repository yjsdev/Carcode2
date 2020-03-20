package service;

/**
 * 
 * @author kim
 *
 */
public interface Car {
	void move(String command) throws Exception;
	int getPositionX();
	int getPositionY();
	String getOrientation();
}

