import java.util.*;

class bankaccount {
    static void register() {
        Scanner Obj = new Scanner(System.in);
        System.out.println("Enter Your Account Type (Saving/Current): ");
        String type = Obj.nextLine();

        Random random = new Random();
        long Accountno = Math.round(random.nextFloat() * Math.pow(10, 12));
        System.out.println(" Your New Account Number Is : " + Accountno);
        System.out.println("-----------------------------------------------");

        System.out.println("Enter Your Email_id :");
        String emailid = Obj.next();
        Obj.nextLine();
        System.out.println("Enter Your FullName :");
        String fullName = Obj.nextLine();
        System.out.println("Enter Your Mobile :");
        long mobileNo = Obj.nextLong();
        Obj.nextLine();
        System.out.println("Enter Your Age :");
        int age = Obj.nextInt();
        Obj.nextLine();

        if (age <= 18) {
            System.out.println("Sorry You Are Not Eligible For New Bank Account , Age Must be +18 ");
            System.exit(0);
        }

        else {
            while (true) {
                System.out.println("Set Your Pin ");
                int pin1 = Obj.nextInt();
                System.out.println("Confirm Your Pin ");
                int pin2 = Obj.nextInt();

                if (pin1 != pin2) {
                    System.out.println("plz check again entered pin");
                } else {
                    break;
                }
            }
        }

        System.out.println(" Congratulation " + fullName + "!!");
        System.out.println();
        System.out.println(" !! ACCOUNT CREATE SUCCESSFULLY !! ");
        System.out.println();
        System.out.println("Your New Account Number is :  " + Accountno);
        System.out.println("------------------------------------------------------");

        ATM.prompt();
    }
}

class transaction {
    static void withdraw() {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------");
        System.out.println("Enter amount to withdraw :");
        int wcash = sc.nextInt();
        if (wcash <= ATM.balance) {
            ATM.balance = ATM.balance - wcash;
            ATM.history.add(Integer.toString(wcash));
            ATM.history.add("Withdraw");
            System.out.println("Amount Rs" + wcash + "/-withdraw successfully");
            System.out.println("---------------------------");
        } else {
            System.out.println("insufficient balance to withdraw the cash");
            System.out.println("---------------------------");
        }

        ATM.prompt();
    }

    static void deposit() {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------");
        System.out.print("Enter amount to deposit :");
        int dcash = sc.nextInt();
        ATM.updatebalance(dcash);
        ATM.history.add(Integer.toString(dcash));
        ATM.history.add("Deposit");
        System.out.println("Amount Rs." + dcash + "/- deposit successful!");
        System.out.println("---------------------------");
        ATM.prompt();
    }

    static void transfer() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the recipient name:");
        String s = sc.nextLine();
        System.out.println("Enter the account number of " + s);
        String num = sc.nextLine();

        System.out.println("Enter the amount to be transferred to " + s + " :");
        int tcash = sc.nextInt();

        if (tcash <= ATM.balance) {
            ATM.balance = ATM.balance - tcash;
            ATM.history.add(Integer.toString(tcash));
            String transName = "transferred to" + " " + s;
            ATM.history.add(transName);
            System.out.println("Amount Rs." + tcash + "/- transferred successfully to " + s);
            System.out.println("---------------------------");
        } else {
            System.out.println("insufficient balance to transfer the cash");
            System.out.println("---------------------------");
        }
    }
}

class check {
    static void checkbalance() {
        System.out.println("------------------");
        System.out.print("The available balance in the bank account :");
        ATM.showbalance();
        System.out.println("---------------------------");
        ATM.prompt();
    }
}

class his {
    static void transactionhistory() {
        System.out.println("---------------------");
        System.out.println("Transaction History :");
        System.out.println("---------------------");

        for (int i = ATM.history.size() - 1; i > 0; i -= 2) {
            System.out.print("Rs." + ATM.history.get(i - 1) + " ");
            System.out.print(ATM.history.get(i) + " ");
            System.out.println("\n");
        }

        ATM.prompt();
    }
}

public class ATM {
    public static String fullName;
    public static int age;
    public static int balance = 0;
    public static String accnumber;
    public static ArrayList<String> history = new ArrayList<String>();

    static void updatebalance(int dcash) {
        balance = balance + dcash;
    }

    static void showbalance() {
        System.out.println(balance);
    }

    public static void homepage() {
        Scanner sc = new Scanner(System.in);
        System.out.println("WELCOME TO ATM INTERFACE");
        System.out.println("--------------------------");
        System.out.println("select option :");
        System.out.println("1. Register");
        System.out.println("2. Exit");
        System.out.println("Enter choice");
        int choice = sc.nextInt();
        if (choice == 1) {
            bankaccount.register();
        } else {
            if (choice == 2) {
                System.exit(0);
            } else {
                System.out.println("select a value only from the given options :");
                homepage();
            }
        }
    }

    static void prompt() {

        do {
            Scanner sc = new Scanner(System.in);
            int choice = 0;
            System.out.println("---------------------");
            System.out.println("Select option : ");
            System.out.println("1) Withdraw");
            System.out.println("2) Deposit");
            System.out.println("3) Transfer");
            System.out.println("4) Check balance");
            System.out.println("5) Transaction History");
            System.out.println("6) Exit");

            System.out.print("Enter your choice : ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    transaction.withdraw();
                    break;

                case 2:
                    transaction.deposit();
                    break;

                case 3:
                    transaction.transfer();
                    break;

                case 4:
                    check.checkbalance();
                    break;

                case 5:
                    his.transactionhistory();
                    break;

                case 6:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice ...\nSelcet the correct option from above ");

            }
        } while (true);

    }

    public static void main(String[] args) {
        homepage();
    }
}