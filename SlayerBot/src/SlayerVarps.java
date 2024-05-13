public enum SlayerVarps {
    SLAYER_TASK_SIZE(394),
    SLAYER_TASK_CREATURE(395),
    SLAYER_TASK_LOCATION(2096);

    private final int value;

    // Constructor
    SlayerVarps(int value) {
        this.value = value;
    }

    // Getter for the value
    public int getValue() {
        return value;
    }
}
