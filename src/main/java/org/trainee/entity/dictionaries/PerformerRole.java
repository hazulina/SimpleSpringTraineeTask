package org.trainee.entity.dictionaries;

/**
 * Enum for Performer's role restriction and validating.
 */
public enum PerformerRole {
PM("MANAGEMENT"),DEV("DEVELOPMENT"),TESTER("TESTING");

    private final String role;

    PerformerRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    /**
     * Check if input value is valid.
     * @param input user's input string.
     * @return {@link #role} from existing Enum if input is correct.
     */
    public static String checkValue(String input) {
        for (PerformerRole performerRole : PerformerRole.values()) {
            if (performerRole.getRole().equalsIgnoreCase(input)) {
                return performerRole.getRole();
            }
        }
        throw new IllegalArgumentException(String.format("WRONG PERFORMER ROLE >%s<", input));
    }
}
