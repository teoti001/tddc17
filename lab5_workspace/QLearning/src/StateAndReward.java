public class StateAndReward {

	static final int ANGLE_INTERVALS = 3;
	
	/* State discretization function for the angle controller */
	public static String getStateAngle(double angle, double vx, double vy) {

		if (Math.abs(angle) < 0.05)
			return "StraightUp";
		long angleInterval = Math.round(Math.abs(angle) / Math.PI * ANGLE_INTERVALS);
		String side = angle > 0 ? "right" : "left";
		return side + angleInterval + ";:";
	}

	/* Reward function for the angle controller */
	public static double getRewardAngle(double angle, double vx, double vy) {


		if (Math.abs(angle) < 0.01)
			return 100;
		long angleInterval = Math.round(Math.abs(angle) / Math.PI * ANGLE_INTERVALS);
		return 80 - angleInterval * (80 / ANGLE_INTERVALS);
	}

	/* State discretization function for the full hover controller */
	public static String getStateHover(double angle, double vx, double vy) {
		String angle_state = getStateAngle(angle, vx, vy);
		String x_state;
		if (Math.abs(vx) < 0.1)
			x_state = "all good in the neighbourhood;";
		else x_state = (vx < 0? "left":"right") + discretize(Math.abs(vx), 4, 0, 7) + ";" ;
		String y_state;
		if (Math.abs(vy) < 0.1)
			y_state = "all good in the neighbourhood;";
		else y_state = (vy < 0? "up":"down") + discretize(Math.abs(vy), 2, 0, 7) + ";" ;
		
		return angle_state + x_state + y_state;
	}

	/* Reward function for the full hover controller */
	public static double getRewardHover(double angle, double vx, double vy) {

		double angle_state = getRewardAngle(angle, vx, vy);
		double x_state;
		if (Math.abs(vx) < 0.1)
			x_state = 100;
		else x_state = 80 - 20 * discretize(Math.abs(vx), 4, 0, 7);
		double y_state;
		if (Math.abs(vy) < 0.1)
			y_state = 100;
		else y_state = 80 - 20 * discretize(Math.abs(vy), 2, 0, 7);
		
		return angle_state + x_state + y_state;
	}

	// ///////////////////////////////////////////////////////////
	// discretize() performs a uniform discretization of the
	// value parameter.
	// It returns an integer between 0 and nrValues-1.
	// The min and max parameters are used to specify the interval
	// for the discretization.
	// If the value is lower than min, 0 is returned
	// If the value is higher than min, nrValues-1 is returned
	// otherwise a value between 1 and nrValues-2 is returned.
	//
	// Use discretize2() if you want a discretization method that does
	// not handle values lower than min and higher than max.
	// ///////////////////////////////////////////////////////////
	public static int discretize(double value, int nrValues, double min,
			double max) {
		if (nrValues < 2) {
			return 0;
		}

		double diff = max - min;

		if (value < min) {
			return 0;
		}
		if (value > max) {
			return nrValues - 1;
		}

		double tempValue = value - min;
		double ratio = tempValue / diff;

		return (int) (ratio * (nrValues - 2)) + 1;
	}

	// ///////////////////////////////////////////////////////////
	// discretize2() performs a uniform discretization of the
	// value parameter.
	// It returns an integer between 0 and nrValues-1.
	// The min and max parameters are used to specify the interval
	// for the discretization.
	// If the value is lower than min, 0 is returned
	// If the value is higher than min, nrValues-1 is returned
	// otherwise a value between 0 and nrValues-1 is returned.
	// ///////////////////////////////////////////////////////////
	public static int discretize2(double value, int nrValues, double min,
			double max) {
		double diff = max - min;

		if (value < min) {
			return 0;
		}
		if (value > max) {
			return nrValues - 1;
		}

		double tempValue = value - min;
		double ratio = tempValue / diff;

		return (int) (ratio * nrValues);
	}

}
