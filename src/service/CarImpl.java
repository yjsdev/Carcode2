package service;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @author Kim
 *
 */
public class CarImpl implements Car {

	volatile int x = 1;
	volatile int y = 1;
	volatile int orientationPoint = 0;
	volatile String[] orientations = {"N","E","S","W"};
	ReentrantLock lock = new ReentrantLock();
	
	@Override
	public void move(String command) throws Exception{
		lock.lock();
		final int backX=x;
		final int backy=y;
		final int backPoint = orientationPoint;
		try {
			String[] steps = command.split("");
			int temp = 0;
			while(steps.length>temp) {
				if("+".equals(steps[temp])) {
					if("N".equals(orientations[orientationPoint])) {
						x+=1;
					}
					if("E".equals(orientations[orientationPoint])) {
						y+=1;
					}
					if("S".equals(orientations[orientationPoint])) {
						x-=1;
					}
					if("E".equals(orientations[orientationPoint])) {
						y-=1;
					}
				}
				if("-".equals(steps[temp])) {
					if("N".equals(orientations[orientationPoint])) {
						x-=1;
					}
					if("E".equals(orientations[orientationPoint])) {
						y-=1;
					}
					if("S".equals(orientations[orientationPoint])) {
						x+=1;
					}
					if("E".equals(orientations[orientationPoint])) {
						y+=1;
					}
				}
				if("R".equals(steps[temp])) {
					if(orientationPoint<3) {
						orientationPoint+=1;
					}else {
						orientationPoint =0;
					}
				}
				if("L".equals(steps[temp])) {
					if(orientationPoint>0) {
						orientationPoint-=1;
					}else {
						orientationPoint =3;
					}
				}
				
				if(this.x>4||this.y>4||this.x<1||this.y<1) {
					x=backX;
					y=backy;
					orientationPoint= backPoint;
					System.out.println(backPoint);
					throw new RuntimeException("moveout");
				}
				temp+=1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			if("moveout".equals(e.getMessage()))throw new RuntimeException("move OutsideException!");
		} finally {
			lock.unlock();
		}
		
	}

	@Override
	public int getPositionX() {
		return x;
	}

	@Override
	public int getPositionY() {
		return y;
	}

	@Override
	public String getOrientation() {
		return orientations[orientationPoint];
	}

	
}
