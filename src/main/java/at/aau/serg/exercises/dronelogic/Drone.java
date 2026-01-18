package at.aau.serg.exercises.dronelogic;

public class Drone {

    /**
     * recomended by sonarqube
    public static final String SERIAL_NUMBER = "ABC123";
    ✔ Used for constants
    ✔ Convention: uppercase with underscores
    ❌ Not suitable if each object has its own serial number

    private String serialNumber;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
    ✔ Preserves encapsulation
    ✔ Allows validation later
    ✔ Recommended for instance data


    private final String serialNumber;

    public MyClass(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }
    ✔ Thread-safe
    ✔ Prevents accidental modification
    ✔ Common for IDs and serial numbers
     */
    public String serialNumber;

    /**
     * private final String serialNumber;

    public MyClass(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }
        would be the best solution cause every object should have one not changing number
     */

/**
 * Use this only if the firmware version is fixed and shared by all instances.

    public static final int FIRMWARE_VERSION = 5;

    ✔ Constant
    ✔ Immutable
    ✔ Clear intent
    ✔ Prefer int over Integer if null is not needed

    private Integer firmwareVersion;

    public Integer getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(Integer firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    ✔ Encapsulation
    ✔ Allows validation later

    firmware gets updated and different drones could have different firmware
 */

    public Integer firmwareVersion;

    /**
     * Allowed types: Surveyor, Guardian, Courier
     */

    /**
     *  public static final String DRONE_TYPE = "QUADCOPTER";
        ✔ Constant
        ✔ Immutable
        ✔ Clear intent

        private String droneType;

        public String getDroneType() {
            return droneType;
        }

        public void setDroneType(String droneType) {
            this.droneType = droneType;
        }

        ✔ Encapsulation
        ✔ Allows validation later

        private final String droneType;

        public Drone(String droneType) {
            this.droneType = droneType;
        }

        public String getDroneType() {
            return droneType;
        }
        ✔ Safer
        ✔ Thread-safe
        ✔ Clear lifecycle
     */

    public String droneType;

/**
 * private final String droneType;

        public Drone(String droneType) {
            + check if Surveyor,  Guardian,  und  Courier
            this.droneType = droneType;
        }

        public String getDroneType() {
            return droneType;
        }
            donetype does not change after production
 */

    /**
     * private double payload;

    public double getPayload() {
        return payload;
    }

    public void setPayload(double payload) {
        if (payload < 0) {
            throw new IllegalArgumentException("Payload cannot be negative");
        }
        this.payload = payload;
    }
    ✔ Encapsulation
    ✔ Allows validation
    ✔ Safer design

    private final double payload;

    public Drone(double payload) {
        if (payload < 0) {
            throw new IllegalArgumentException("Payload cannot be negative");
        }
        this.payload = payload;
    }

    public double getPayload() {
        return payload;
    }
    ✔ Prevents invalid states
    ✔ Thread-safe
    ✔ Clear lifecycle

     */
    public double payload;

    /**public double getPayload() {
        return payload;
    }

    public void setPayload(double payload) {
        if (payload < 0) {
            throw new IllegalArgumentException("Payload cannot be negative");
        }
        this.payload = payload;
        if the drone gets modivied the load could change
    } */

    /**
     * Used for scan and high-altitude modes, low-altitude ignores this value
     */

    /**
     *  public static final int MAX_OPERATION_RANGE = 10_000; // meters
        ✔ Constant
        ✔ Immutable
        ✔ Clear meaning

    private int operationRange; // meters

    public int getOperationRange() {
        return operationRange;
    }

    public void setOperationRange(int operationRange) {
        if (operationRange < 0) {
            throw new IllegalArgumentException("Operation range cannot be negative");
        }
        this.operationRange = operationRange;
    }
    ✔ Encapsulation
    ✔ Validation
    ✔ Maintainable

    private final int operationRange;

    public Drone(int operationRange) {
        if (operationRange < 0) {
            throw new IllegalArgumentException("Operation range cannot be negative");
        }
        this.operationRange = operationRange;
    }

    public int getOperationRange() {
        return operationRange;
    }
    ✔ Prevents invalid state
    ✔ Thread-safe
    ✔ Clear lifecycle
     */

    public int operationRange;

    /**
     *  public int getOperationRange() {
        return operationRange;
    }

    public void setOperationRange(int operationRange) {
        if (operationRange < 0) {
            throw new IllegalArgumentException("Operation range cannot be negative");
        }
        this.operationRange = operationRange;
    }
        if the drone get modi the range can change
     */

    /**
     * 1 := lowAltitude (guardian always has this disabled)
     * 2 := highAltitude
     * 3 := scan (only surveyor has this, always)
     */

    /**
     *  public static final short OPERATION_MODE_AUTO = 1;
        ✔ Constant
        ✔ Immutable
        ✔ Clear intent

        private short operationMode;

        public short getOperationMode() {
            return operationMode;
        }

    public void setOperationMode(short operationMode) {
        if (operationMode < 0 || operationMode > 3) {
            throw new IllegalArgumentException("Invalid operation mode");
        }
        this.operationMode = operationMode;
    }
    ✔ Encapsulation
    ✔ Validation
    ✔ Safer design

    Best practice (highly recommended): Use an enum
    Using short for modes is error-prone. An enum is much clearer.

    public enum OperationMode {
        MANUAL,
        AUTO,
        RETURN_HOME
    }
    private OperationMode operationMode;

    public OperationMode getOperationMode() {
        return operationMode;
    }

    public void setOperationMode(OperationMode operationMode) {
        this.operationMode = operationMode;
    }
    ✔ Self-documenting
    ✔ Type-safe
    ✔ Prevents invalid values
     */
    public short operationMode;

    /** 
     *  public enum OperationMode {
        MANUAL,
        AUTO,
        RETURN_HOME
    }
    private OperationMode operationMode;

    public OperationMode getOperationMode() {
        return operationMode;
    }

    public void setOperationMode(OperationMode operationMode) {
        this.operationMode = operationMode;
    }
        enums sind anpassungsfähig und überichtlich es sollte noch nach NULL geprüft werden
     */

    /**
     * None (null), thermal, camera, jammer, medical
     */
    /** public static final String PAYLOAD_EFFECT = "REDUCED_RANGE";
        ✔ Constant
        ✔ Immutable
        ✔ Clear intent

        private String payloadEffect;

        public String getPayloadEffect() {
            return payloadEffect;
        }

        public void setPayloadEffect(String payloadEffect) {
            this.payloadEffect = payloadEffect;
        }
        ✔ Encapsulation
        ✔ Safer design

        Best practice improvement: Use an enum
        If payloadEffect comes from a known set of values, an enum is much better than String.

        public enum PayloadEffect {
            NONE,
            REDUCED_RANGE,
            REDUCED_SPEED
        }
        private PayloadEffect payloadEffect;

        public PayloadEffect getPayloadEffect() {
            return payloadEffect;
        }

        public void setPayloadEffect(PayloadEffect payloadEffect) {
            this.payloadEffect = payloadEffect;
        }
        ✔ Type-safe
        ✔ Self-documenting
        ✔ Prevents invalid strings
     */
    public String payloadEffect;

    /**
     * public enum PayloadEffect {
            NONE,
            REDUCED_RANGE,
            REDUCED_SPEED
        }
        private PayloadEffect payloadEffect;

        public PayloadEffect getPayloadEffect() {
            return payloadEffect;
        }

        public void setPayloadEffect(PayloadEffect payloadEffect) {
            this.payloadEffect = payloadEffect;
        }
            wieder nach NULL überprüfen und selbsterklrender
     */

    /**
     * Indicates if a guardian currently has their shield mode enabled
     */

    /**
     *  public static final boolean SHIELD_MODE_ENABLED = true;
        ✔ Constant
        ✔ Immutable

        ⚠ Rarely useful for booleans representing state

        private boolean shieldModeEnabled;

        public boolean isShieldModeEnabled() {
            return shieldModeEnabled;
        }

        public void setShieldModeEnabled(boolean enabled) {
            this.shieldModeEnabled = enabled;
        }
        ✔ Encapsulation
        ✔ Follows JavaBean naming (isX for boolean)
        ✔ Allows logic later

        private final boolean shieldModeEnabled;

        public Drone(boolean shieldModeEnabled) {
            this.shieldModeEnabled = shieldModeEnabled;
        }

        public boolean isShieldModeEnabled() {
            return shieldModeEnabled;
        }
        ✔ Thread-safe
        ✔ Prevents accidental change
     */
    public boolean shieldModeEnabled;

    /**
     * private boolean shieldModeEnabled;

        public boolean isShieldModeEnabled() {
            return shieldModeEnabled;
        }

        public void setShieldModeEnabled(boolean enabled) {
            this.shieldModeEnabled = enabled;
        }
            true false option die geändert werden kann
     */
    /**
     * Available battery of surveyor
     */
    /**
     *  public static final int MAX_BATTERY = 100; // percent
        ✔ Constant
        ✔ Immutable
        ✔ Clear intent

    private int battery; // percentage: 0–100

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        if (battery < 0 || battery > 100) {
            throw new IllegalArgumentException("Battery must be between 0 and 100");
        }
        this.battery = battery;
    }
    ✔ Encapsulation
    ✔ Validation
    ✔ Safer design

    private final int battery;

    public Drone(int battery) {
        if (battery < 0 || battery > 100) {
            throw new IllegalArgumentException("Battery must be between 0 and 100");
        }
        this.battery = battery;
    }

    public int getBattery() {
        return battery;
    }
    ✔ Prevents invalid states
    ✔ Thread-safe
     */
    public int battery;
    /**
     * public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        if (battery < 0 || battery > 100) {
            throw new IllegalArgumentException("Battery must be between 0 and 100");
        }
        this.battery = battery;
        status dauernd am ändern
     * @return
     */

        /* bei enum nutzung muss lediglich der string geändert werden */
    public String getDescription() {
        switch (droneType) {
            case "Surveyor":
                return "Surveyor: A drone specialized in scanning and mapping areas.";
            case "Guardian":
                return "Guardian: A drone designed to patrol and protect zones.";
            case "Courier":
                return "Courier: A drone optimized for fast package delivery.";
            default:
                throw new IllegalStateException("Unexpected drone type: " + droneType);
        }
    }

    public boolean isCertified() {
        return firmwareVersion.equals(12);
    }

    /**
     * public boolean isCertified() {
        return firmwareVersion == 12;
}
    equals castet 12 in Integer, == ist primitive und nullsafe
     */
}
