package src;

class Stat {
	public static double standardDeviation(long values[], double average) {

		double sum = 0;
		double diff = 0;
		double stDiff = 0;

		for (int i = 0; i < values.length; i++) {
			diff = values[i] - average;
			sum += diff * diff;
		}

		stDiff = Math.sqrt(sum / values.length);
		return stDiff;

	}

	public static double average(long values[]) {

		double mean = 0;
		double sum = 0;
		int total = values.length;

		for (int i = 0; i < values.length; i++) {
			sum += values[i];
		}

		mean = sum / ((1.0) * (total));
		return mean;

	}
}
