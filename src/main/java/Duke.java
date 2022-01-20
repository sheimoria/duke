import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();

    private static String isPlural(int n) {
        if (n < 1 || n > 1) {
            return "s ";
        }
        return " ";
    }

    public static void main(String[] args) {
        try {
            String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);

            System.out.println("Hello! I'm Duke");

            Scanner sc = new Scanner(System.in);

            System.out.println("What can I do for you?");
            String command = sc.nextLine();

            while (!command.equals("bye")) {
                if (command.equals("list")) {
                    System.out.println("Here are the tasks in your list:");

                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.printf("%d.%s%n", i + 1, tasks.get(i));
                    }
                } else {
                    String[] commandArr = command.split(" ");
                    String type = commandArr[0];

                    if (type.equals("mark")) {
                        int i = Integer.parseInt(commandArr[1]) - 1;
                        Task task = tasks.get(i);
                        task.markAsDone();

                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(task);
                    } else if (type.equals("unmark")) {
                        int i = Integer.parseInt(commandArr[1]) - 1;
                        Task task = tasks.get(i);
                        task.markAsNotDone();

                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(task);
                    } else if (type.equals("delete")) {
                        int i = Integer.parseInt(commandArr[1]) - 1;
                        Task task = tasks.remove(i);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(task);
                    } else {
                        String[] descriptionArr = Arrays.copyOfRange(commandArr, 1, commandArr.length);
                        if (descriptionArr.length == 0) {
                            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                        }
                        String description = String.join(" ", descriptionArr);

                        if (type.equals("todo")) {
                            Task todo = new ToDo(description);
                            tasks.add(todo);

                            System.out.println("Got it. I've added this task:");
                            System.out.println(todo);
                        } else if (type.equals("event")) {
                            String[] eventArr = description.split(" /at ");
                            String name = eventArr[0];
                            String time = eventArr[1];

                            Task event = new Event(name, time);
                            tasks.add(event);

                            System.out.println("Got it. I've added this task:");
                            System.out.println(event);
                        } else if (type.equals("deadline")) {
                            String[] deadlineArr = description.split(" /by ");
                            String name = deadlineArr[0];
                            String time = deadlineArr[1];

                            Task deadline = new Deadline(name, time);
                            tasks.add(deadline);

                            System.out.println("Got it. I've added this task:");
                            System.out.println(deadline);
                        } else {
                            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                        }
                    }
                    System.out.println("Now you have " + tasks.size() + " task" + isPlural(tasks.size()) + "in the list.");
                }
                command = sc.nextLine();
            }
            sc.close();
            System.out.println("Bye. Hope to see you again soon!");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}