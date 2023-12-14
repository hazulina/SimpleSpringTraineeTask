package org.trainee.entity.dictionaries;

/**
 * Enum for Task's status restriction and validating.
 */
public enum TaskStatusEnum {
    COMPLETE("COMPLETE"), AT_WORK("AT WORK");

    private final String taskStatus;

    TaskStatusEnum(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    /**
     * Check if input value is valid.
     *
     * @param input user's input string.
     * @return {@link #taskStatus} from existing Enum if input is correct.
     */
    public static String checkValue(String input) {
        for (TaskStatusEnum status : TaskStatusEnum.values()) {
            if (status.getTaskStatus().equalsIgnoreCase(input)) {
                return status.getTaskStatus();
            }
        }
        throw new IllegalArgumentException(String.format("WRONG TASK STATUS >%s<", input));
    }
}
