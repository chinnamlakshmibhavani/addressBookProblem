import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class AddressBookProblem
{
    static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception
    {
        AddressBook addressBook = new AddressBook();
        while (true)
        {
            System.out.println("   ");
            System.out.println("1.create a new contact");
            System.out.println("2.fill the contact details");
            System.out.println("3.display all contacts");
            System.out.println("4.view contact information");
            System.out.println("5.exit");
            System.out.print("enter your choice:");
            int choice = scanner.nextInt();
            switch (choice)
            {
                case 1:
                    addressBook.createNewContact();
                    break;
                case 2:
                    addressBook.fillDetails();
                    break;
                case 3:
                    addressBook.displayDetails();
                    break;
                case 4:
                    addressBook.viewContactInformation();
                    break;
                case 5:
                    System.out.println("Closing");
                    System.exit(0);
                default:
                    System.out.println("please enter the correct choice");
                    break;
                  }

        }

    }
}




class AddressBook
{
    static final Scanner scanner = new Scanner(System.in);
    static Set<String> emptyContacts = new HashSet<>();
    static Set<String> nonEmptyContacts = new HashSet<>();

    void createNewContact() throws Exception
    {
        System.out.print("Enter name of the contact:");
        String contactName = scanner.nextLine();
        File file = new File(contactName);
        if (file.exists())
        {
            System.out.println("contact " + file.getName() + " already exists!");
        }
        else
        {
            if (file.createNewFile())
            {
                System.out.println("new contact " + file.getName() + " is created successfully");
                emptyContacts.add(file.getName());
            }
            else
            {
                System.out.println("file creation failed!");
            }
        }

    }


 void fillDetails() throws Exception
    {
        System.out.print("enter empty contact name which is going to be filled:");
        String contactName = scanner.nextLine();
         if (emptyContacts.contains(contactName))
        {

            FileWriter fw = new FileWriter(contactName);
            String details = "";
            System.out.print("enter first name:");
            details += scanner.nextLine() + "\n";
            System.out.print("enter last name:");
            details += scanner.nextLine() + "\n";
            System.out.print("enter address:");
            details += scanner.nextLine() + "\n";
            System.out.print("enter city:");
            details += scanner.nextLine() + "\n";
            System.out.print("enter state:");
            details += scanner.nextLine() + "\n";
            System.out.print("enter zip:");
            details += scanner.nextLine() + "\n";
            System.out.print("enter phone number:");
            details += scanner.nextLine() + "\n";
            fw.write(details);
            emptyContacts.remove(contactName);
            nonEmptyContacts.add(contactName);
            fw.close();
        }
        else
        {
            System.out.println(contactName + " is not empty contact or it is not created");
            System.out.println("use other option 1 to create new contact");
        }

    }

    void displayDetails()
    {

        boolean flag = false;
        if (emptyContacts.size() != 0)
        {
            System.out.println("the empty contacts are:");
            for (String contact : emptyContacts)
            {
                System.out.println(contact);
            }
            flag=true;
            }
        if (nonEmptyContacts.size() != 0)
        {
            System.out.println("the non empty contacts are:");
            for (String contact : nonEmptyContacts)
            {
                System.out.println(contact);
            }
            flag=true;
        }
        if(!flag)
        {
            System.out.println("no contacts are created yet");
        }

    }
    void viewContactInformation()throws Exception
    {
        System.out.print("enter name of the contact to view:");
        String contactName = scanner.nextLine();
        if(emptyContacts.contains(contactName))
        {
            System.out.println("pleast fill the contact "+ contactName+ " before viewing it");
            return;
        }
        else if(!nonEmptyContacts.contains(contactName))
        {
            System.out.println("please create the contact "+contactName+" before viewing it");
            return;
        }
        System.out.println("the content of "+ contactName + " is:");
        FileReader fileReader = new FileReader(contactName);
        int character;
        while ((character = fileReader.read()) != -1)
        {
            System.out.print((char) character);
        }
        fileReader.close();
    }
}


                
