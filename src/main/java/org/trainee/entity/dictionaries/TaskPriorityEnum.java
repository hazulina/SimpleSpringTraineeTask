package org.trainee.entity.dictionaries;

/**
 * Enum for Task's priority restriction and validating.
 */
public enum TaskPriorityEnum {
    LOW("LOW"),
    MEDIUM("MEDIUM"),
    HIGH("HIGH");

    private final String taskPriority;

    TaskPriorityEnum(String taskPriority) {
        this.taskPriority = taskPriority;
    }

    public String getTaskPriority() {
        return taskPriority;
    }

    /**
     * Check if input value is valid.
     * @param input user's input string.
     * @return {@link #taskPriority} from existing Enum if input is correct.
     */
    public static String checkValue(String input) {
        for (TaskPriorityEnum priority : TaskPriorityEnum.values()) {
            if (priority.getTaskPriority().equalsIgnoreCase(input)) {
                return priority.getTaskPriority();
            }
        }
        throw new IllegalArgumentException(String.format("WRONG TASK PRIORITY >%s<", input));
    }
}
