package hw1;

public class Balloon {

	/**
	 * constant
	 */

	private final double STANDARD_PRESSURE = 1013.25;
	private final double STANDARD_VOL = 61234.0;
	private final double GAS_CONST = 287.05;

	/**
	 * instance variables
	 */

	private double initialOutsideAirTemp;
	private double initialWindDirection;
	private double tetherRemaining;
	private double balloonMass;
	private double fuelBurnRate;
	private double outsideAirTemp;
	private double windDirection;
	private double balloonTemp;
	private double tetherLen;
	private double fuelRemaining;
	private double pressureOut;
	private double pressureIn;
	private double netForce;
	private double liftForce;
	private double gravityForce;
	private double accelNet;
	private double altitude;
	private double fuelUsed;
	private double velocity;
	private double time;
	private double minutes;
	private double seconds;

	/**
	 * makes new simulation with the initial air temperature in celsius and wind direction in degrees
	 * the initial air temperature inside the balloon equals the outside air temperature
	 * all other variables are initialized to zero
	 */

	public Balloon(double airTemperature, double windDirection) {

		this.initialWindDirection = windDirection;
		this.initialOutsideAirTemp = airTemperature;
		this.outsideAirTemp = airTemperature;
		this.windDirection = windDirection;
		this.balloonTemp = airTemperature;

		this.fuelRemaining = 0;
		this.fuelBurnRate = 0;
		this.balloonMass = 0;
		this.velocity = 0;
		this.tetherLen = 0;
		this.time = 0;
		this.altitude = 0;
		this.fuelUsed = 0;
	}

	/**
	 * retrieves remaining fuel available to heat up balloon
	 */

	public double getFuelRemaining() {

		return this.fuelRemaining;
	}

	/**
	 * sets the remaining usable fuel
	 */

	public void setFuelRemaning(double fuel) {

		this.fuelRemaining = Math.max(0, fuel);
	}

	/**
	 * retrieves the mass of balloon
	 */

	public double getBalloonMass() {

		return this.balloonMass;
	}

	/**
	 * sets the mass of balloon
	 */

	public void setBalloonMass(double mass) {

		this.balloonMass = Math.max(0, mass);
	}

	/**
	 * retrieves the outside air temperature in celsius
	 */

	public double getOutsideAirTemp() {

		return this.outsideAirTemp;
	}

	/**
	 * sets the outside air temperature in celsius
	 */

	public void setOutsideAirTemp(double temp) {

		this.outsideAirTemp = temp;
	}

	/**
	 * retrieves the fuel burn rate
	 */

	public double getFuelBurnRate() {

		return this.fuelBurnRate;
	}

	/**
	 * sets the fuel burn rate
	 */

	public void setFuelBurnRate(double rate) {

		this.fuelBurnRate = rate;
	}

	/**
	 * retrieves the balloon temperature
	 */

	public double getBalloonTemp() {

		return this.balloonTemp;
	}

	/**
	 * sets the balloon temperature.
	 */

	public void setBalloonTemp(double temp) {

		this.balloonTemp = temp;
	}

	/**
	 * retrieves the velocity of the balloon
	 */

	public double getVelocity() {

		return this.velocity;
	}

	/**
	 * retrieves the altitude of the balloon
	 */

	public double getAltitude() {

		return altitude;
	}

	/**
	 * retrieves the length of the tether
	 */

	public double getTetherLength() {

		return this.tetherLen;
	}

	/**
	 * sets the length of the tether
	 */

	public void setTetherLength(double length) {

		this.tetherLen = length;
	}

	/**
	 * retrieves the remaining length of the tether
	 */

	public double getTetherRemaining() {

		return tetherRemaining = tetherLen;
	}

	/**
	 * retrieves the wind direction in degrees limited to [0, 360]
	 */

	public double getWindDirection() {

		return this.windDirection;
	}

	/**
	 * updates the wind direction by adding the value between -360 and 360,
	 * adjusted later to be in the range [0, 360]
	 */

	public void changeWindDirection(double deg) {

		this.windDirection += deg;
		this.windDirection = (this.windDirection + 360) % 360;
	}

	/**
	 * retrieves the elapsed time in minutes
	 */

	public long getMinutes() {

		return (long)(this.minutes = (time / 60) % 60);
	}

	/**
	 * retrieves the elapsed time in seconds
	 */

	public long getSeconds() {

		return (long)(this.seconds = time % 60);
	}

	/**
	 * indicates that one second has passed during the simulation
	 */

	public void update() {

		this.time++;

		this.fuelUsed = Math.min(this.fuelRemaining, this.fuelBurnRate);
		this.fuelRemaining -= this.fuelUsed;
		this.balloonTemp = this.balloonTemp + this.fuelUsed + ((this.outsideAirTemp - this.balloonTemp) * 0.1);
		pressureOut = (STANDARD_PRESSURE / (GAS_CONST * (outsideAirTemp + 273.15)));
		pressureIn = (STANDARD_PRESSURE / (GAS_CONST * (balloonTemp + 273.15)));
		liftForce = (STANDARD_VOL * (pressureOut - pressureIn) * 9.81);
		gravityForce = balloonMass * 9.81;
		accelNet = (liftForce - gravityForce) / balloonMass;
		velocity += accelNet;
		altitude = Math.min(Math.max(0, altitude + velocity), tetherLen);
	}

	/**
	 * resets the simulation to its initial state
	 */

	public void reset() {

		this.balloonTemp = initialOutsideAirTemp;
		this.windDirection = initialWindDirection;
		this.outsideAirTemp = initialOutsideAirTemp;

		this.fuelRemaining = 0;
		this.fuelBurnRate = 0;
		this.balloonMass = 0;
		this.velocity = 0;
		this.tetherLen = 0;
		this.time = 0;
		this.altitude = 0;
		this.fuelUsed = 0;
	}
}