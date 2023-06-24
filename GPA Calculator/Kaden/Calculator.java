import java.util.Scanner;
import java.util.ArrayList;

public class Calculator {
    private enum Year {
        Freshman(8),
        Sophomore(8),
        Junior(6),
        Senior(5);

        public final int requiredClasses;

        Year(int requiredClasses) {
            this.requiredClasses = requiredClasses;
        }
    }

    private enum Classes {
        FreePeriod,
        English,
        Math,
        Chemistry,
        Elective,
        Language,
        PE,
        History,
        CoreClass,
        CollegeClass;
    }

    private enum Grades {
        A(4),
        B(3),
        C(2),
        D(1),
        F(0);

        public final int value;

        Grades(int value) {
            this.value = value;
        }
    }

    public static void main (String[] arg) {
        try (Scanner scanny = new Scanner(System.in)) {
            System.out.println("Are you a 'Freshman', 'Sophomore', 'Junior', or a 'Senior'?");
            Year year = null;
            while(year == null){
                try {
                    year = Year.valueOf(scanny.nextLine());;
                } catch (Exception e) {
                    System.out.println("Type it correctly, bozo.");
                }
            }

            System.out.println("\nHow many classes do you have?");
            int numberOfClasses = 10;
            while(numberOfClasses < year.requiredClasses || numberOfClasses > 8){
                try {
                    numberOfClasses = Integer.valueOf(scanny.nextLine());
                    if(numberOfClasses < year.requiredClasses || numberOfClasses > 8) System.out.println("Stop lying, bozo.");
                } catch (Exception e) {
                    System.out.println("Type it correctly, bozo.");
                }
            }
            
            System.out.println("\nWhat classes do you take and what's your grade? Example:\n'English'\n'A'");
            for (Classes classes : Classes.values()) {
                System.out.println(classes);
            }
            System.out.println("");
            ArrayList<Classes> classes = new ArrayList<Classes>();
            ArrayList<Grades> grades = new ArrayList<Grades>();
            for (int i = 0; i < numberOfClasses; i++) {
                try {
                    classes.add(Classes.valueOf(scanny.nextLine()));
                    grades.add(Grades.valueOf(scanny.nextLine()));
                    if(i != numberOfClasses - 1) System.out.println("Next Class");
                } catch (Exception e) {
                    System.out.println("Type it correctly, bozo.");
                }
            }

            calculate(classes, grades);
        }
    }

    public static void calculate(ArrayList<Classes> classes, ArrayList<Grades> grades) {
        System.out.println("\nCalculating...");

        String msg = " okay.";
        for (int i = 0; i < classes.size(); i++) {
            switch (grades.get(i)) {
                case A:
                    msg = ", average.";
                    break;
                case B:
                    msg = ", below average.";
                    break;
                case C:
                    msg = ", can't eat dinner.";
                    break;
                case D:
                    msg = ", don't come home.";
                    break;
                case F:
                    msg = ", find a new family.";
                    break;
                default:
                    break;
            }
            System.out.println(classes.get(i) + " is " + grades.get(i) + msg);
        }

        double GPA = 0;
        for (Grades grades2 : grades) {
            GPA += grades2.value;
        }
        GPA = GPA / grades.size();
        if(GPA < 4) msg = ", worthless";
        else msg = ", as expected";
        System.out.println("\nYour GPA is " + GPA + msg);
    }
}