import java.util.*;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
class AirlineReservationSystem
{
    public static final String reset = "\u001B[0m";
    public static final String red = "\033[1;31m";
    public static final String green = "\033[1;32m";
    public static final String cyan = "\033[1;36m"; 
    public static final String yellow = "\033[1;33m";
    public static final String white = "\033[1;37m";
    public static final String purple = "\033[1;35m";
    public static final String blue = "\033[1;34m";
    public static final String black = "\033[1;30m";
   
    public static Scanner sc = new Scanner(System.in);
    public static Random rand=new Random();
    public static double ticketPrice=0;
    public static void main(String[] args)
    {
        System.out.println(blue + "Welcome to the Airline Reservation System\n" + reset);
        System.out.println(blue + "-----------------------------------------\n" + reset);
        System.out.println(blue + "Enter Your Login Details\n" + reset);
        System.out.println();
        String name = enterName();
        String email = enterEmail();
        String phoneNumber = enterPhoneNumber();
        System.out.println(white + "\nHello, " + name + "!" + reset);
        System.out.println(cyan + "Please select the type of flight you would like to book:\n" + reset);
        int choice=FlighType();
        int num=0,num2=0;
        String destination="Airline";
         switch (choice)
        {
            case 1:
                destination=bookDomesticFlight();
                num=5000;
                break;
            case 2: num=7000;
                destination=bookInternationalFlight();
                break;
            default:
                System.out.println(red + "Invalid choice" + reset);
        }
        System.out.println();
        System.out.println();
        double startingPrice = Pricegenerator(num);
        startingPrice=startingPrice+ticketPrice;
        double finalPrice = startingPrice;
        System.out.println("Choose the seat you want to book");
        String dayc=day(destination);
        String timec=time(dayc);
        String seat=seatbook();
        System.out.println(yellow + blue + "The final price of the flight is " + " " + finalPrice + reset + reset);  
        String paym=enterPaymentMethod();
        System.out.println(green + "\nPayment Successful" + reset);
        System.out.println();
        Pass(name,seat,destination,timec,dayc,phoneNumber,email,paym);
        System.out.println(blue + "Thank you for using the Airline Reservation System!\n" + reset);
    }
    public static void Pass(String name,String seat,String flight, String time,String date, String phone,String mail,String paym){
        String filePath="D:"+name+".txt";
        int a=70;
        try(FileWriter writer=new FileWriter(filePath, true)){
            writer.write("\t\t\tAirline Reservation System CSE-C\n");
            writer.write("\t\t\t\tBoarding Pass\n");
            for(int i=0;i<a;i++){
                writer.write("-");
            }
            writer.write("\n\t\tPassenger\t:\t"+name);
            writer.write("\t\t\tSeat\t:\t"+seat);
            writer.write("\n\t\tFlying From\t:\tDelhi");
            writer.write("\t\t\t\tGate\t:\tG"+rand.nextInt(25)+"\n");
            writer.write("\t\tFlying To\t:\t");
            for(int i=9;i<flight.length();i++){
                writer.write(flight.charAt(i));
            }
            writer.write("\n\t\tFlight\t:\tBA0"+rand.nextInt(1000));
            writer.write("\n\t\tDeparture Date\t:\t"+date);
            writer.write("\n\t\tDeparture Time\t:\t"+time+"\n");
            for(int i=0;i<a;i++){
                writer.write("-");
            }
            writer.write("\n\t\t\tGATE CLOSES 15 MINUTES FROM DEPARTURE\n");
            for(int i=0;i<a;i++){
                writer.write("-");
            }
            writer.write("\n\t\t\t\t\tPersonal Info\n");
            writer.write("\t\t\tName\t:\t"+name+"\n");
            writer.write("\t\t\tContanct\t:\t"+phone+"\n");
            writer.write("\t\t\tE-mail\t:\t"+mail+"\n");
            writer.write("\t\t\tMode Of Payment\t:\t"+paym+"\n");
            for(int i=0;i<a;i++){
                writer.write("-");
            }
            writer.write("\nTHANK YOU for flying with us. Wish you a happy journey.");
            System.out.println("Boarding made Successfully");
        }
        catch(IOException e){
            System.out.println("An error occurred while writing to the file "+e);
        }
    }
    public static String day(String loc){
        System.out.println("For your flight from "+loc+" flight is available on following dates");
        LocalDateTime datetime=LocalDateTime.now();
        DateTimeFormatter mydate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String date=datetime.format(mydate);
        String date2=date;
        char [] date2arr=date2.toCharArray();
        int a,b,c,d,e,f;
        StringBuilder str=new StringBuilder();
        int arrc[]=new int[5];
        char [] dat=new char[50];
        for(int i=0;i<5;i++){
            a=Date(date.charAt(0));
            b=Date(date.charAt(1));
            c=Date(date.charAt(3));
            d=Date(date.charAt(4));
            // 0 to 9 random integer creating using e
            e=rand.nextInt(10)+1;
            for(int j=0;j<=i;j++){
                if(arrc[j]==e){
                    e=rand.nextInt(10)+1;
                }
            }
            arrc[i]=e;
            f=time(a,b,c,d,e);
            a=f/100;
            b=f%100;
            c=a%10;
            a=a/10;
            d=b%10;
            b=b/10;
            date2arr[0]=datetime(a);
            date2arr[1]=datetime(c);
            date2arr[3]=datetime(b);
            date2arr[4]=datetime(d);
            date2=String.valueOf(date2arr);
            System.out.println((i+1)+". "+date2);
            for(int j=0;j<10;j++){
                dat[i*10+j]=date2arr[j];
            }
        }
        System.out.println("Choose the date you want to fly on : ");
        int num=sc.nextInt();
        num--;
        for(int i=0;i<10;i++){
            str.append(dat[10*num+i]);
        }
        return str.toString();
    }
    public static String time(String day){
        LocalDateTime datetime=LocalDateTime.now();
        DateTimeFormatter mytime = DateTimeFormatter.ofPattern("hh:mm:ss");
        String time=datetime.format(mytime);
        System.out.println("On "+day+" we have these flights");
        int [][]tim=new int[5][6];
        for(int i=0;i<5;i++){
                tim[i][0]=rand.nextInt(3);
                int num=10;
                if(tim[i][0]==2){
                    num=5;
                }
                tim[i][1]=rand.nextInt(num);
                tim[i][2]=rand.nextInt(7);
                num=10;
                if(tim[i][2]==6){
                    num=1;
                }
                tim[i][3]=rand.nextInt(num);
                tim[i][4]=rand.nextInt(7);
                num=10;
                if(tim[i][4]==6){
                    num=1;
                }
                tim[i][5]=rand.nextInt(num);
        }
        for(int i=0;i<5;i++){
            for(int j=0;j<6;j++){
                System.out.print((i)+" ");
                if(j==0){

                }else{
                    if(j%2==0){
                        System.out.print(":");
                    }
                }
                System.out.print(tim[i][j]);
            }
            System.out.println();
        }
        System.out.println("Choose the time of flight you want");
        int num=sc.nextInt();
        StringBuilder str=new StringBuilder();
        for(int i=0;i<6;i++){
            if(i==0){

            }
            else{
                if(i%2==0){
                    str.append(":");
                }
            }
            str.append(tim[num-1][i]);
        }
        return str.toString();
    }
    public static int Date(char ch){
        int num=0;
        switch(ch){
        case '0':num=0;
            break;
        case '1':num=1;
            break;
        case '2':num=2;
            break;
        case '3':num=3;
            break;
        case '4':num=4;
            break;
        case '5':num=5;
            break;
        case '6':num=6;
            break;
        case '7':num=7;
            break;
        case '8':num=8;
            break;
        case '9':num=9;
            break;
        }
        return num;
    }
    public static int time(int a, int b, int c, int d, int e){
        a=10*a+b;
        c=10*c+d;
        switch(c){
        case 1: 
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12: if((a+e)>31){
                c++;
                a=a+e-31;
            }else{
                a=a+e;
            }
            break;
        case 4: 
        case 6:
        case 9:
        case 11: if((a+e)>30){
                c++;
                a=a+e-30;
            }else{
                a=a+e;
            }
            break;
        case 2: if((a+e)>28){
                c++;
                a=a+e-30;
            }else{
                a=a+e;
            }
            break;
        }
        return (a*100+c);
    }
    public static char datetime(int num){
        char ch='A';
        switch(num){
        case 0: ch='0';
            break;
        case 1: ch='1';
            break;
        case 2: ch='2';
            break;
        case 3: ch='3';
            break;
        case 4: ch='4';
            break;
        case 5: ch='5';
            break;
        case 6: ch='6';
            break;
        case 7: ch='7';
            break;
        case 8: ch='8';
            break;
        case 9: ch='9';
            break;
        }
        return ch;
    }  
    public static int FlighType(){
        System.out.println(yellow + "1. Domestic Flight\n" + reset);
        System.out.println(yellow + "2. International Flight\n" + reset);
        System.out.println(cyan + "Enter your choice (1 or 2): \n" + reset);
        int choice = sc.nextInt();
        if(choice<1||choice>2){
            System.out.println(red+"‣ Enter a correct number"+reset);
            FlighType();
        }
        return choice;
    }
    public static double Pricegenerator(int num){
        Random obj=new Random();
        double num1=(double)(obj.nextInt(num)+num+(1/obj.nextInt(100)));
        return num1;
    }
    public static String enterName() {
        System.out.println(yellow + "‣ Enter your name: \n" + reset);
        String name = sc.nextLine();
        return name;
       
    }
    public static String enterEmail() {
        System.out.println(yellow + "‣ Enter your email address: \n" + reset);
        String email = sc.nextLine();
        if(email.contains("@gmail.com")==false){
            System.out.println(red + "‣ Enter a valid e-mail\n" + reset);
            enterEmail();
        }
        return email;
    }
    public static String enterPhoneNumber() {
        System.out.println(yellow + "‣ Enter your phone number:\n " + reset);
        String phoneNumber = sc.nextLine();
        if(phoneNumber.length()==10){
            for (char c : phoneNumber.toCharArray()) {
            if (!Character.isDigit(c)) {
                System.out.println(red + "Enter a valid number\n" + reset);
                enterPhoneNumber();
            }
        }
        }
        else{
            System.out.println(red + "Enter a valid number" + reset);
            enterPhoneNumber();
        }
        return phoneNumber;
    }
    public static String enterPaymentMethod() {
        System.out.println(cyan + "Enter your payment method \n 1.Credit Card \n 2.Debit Card\n 3.Net Banking\n " + reset);
        int paymentMethod = sc.nextInt();
        sc.nextLine();
        String str="Airline";
        switch(paymentMethod){
            case 1: System.out.println(white + "‣ Enter credit card number: " + reset);
                    enterPaymentDetails();
                    str="Credit Card";
                    break;
            case 2: System.out.println(white + "‣ Enter debit card number: " + reset);
                    enterPaymentDetails();
                    str="Debit Card";
                    break;
            case 3: System.out.println(white + "‣ Enter User phone number: " + reset);
                    enterUPIid();
                    str="Net Banking";
                    break;
            default: System.out.println(red + "‣ Enter correct number" + reset);
                    enterPaymentMethod();
                    break;
        }
        return str;
    }
    public static void enterUPIid(){
        String phoneNumber = sc.nextLine();
        if(phoneNumber.length()==10){
            for (char c : phoneNumber.toCharArray()) {
            if (!Character.isDigit(c)) {
                System.out.println(red + "Enter a valid number" + reset);
                enterUPIid();
            }
        }
        }
        else{
            System.out.println(red + "Enter a valid number" + reset);
            enterUPIid();
        }
        System.out.println(cyan + "‣ Enter the UPI ID" + reset);
        String UPI=sc.nextLine();
    }
    public static void enterPaymentDetails() {
        sc.nextLine();
        String cardNumber = sc.nextLine();

        System.out.print(white + "‣ Enter cardholder's name: " + reset);
        String cardHolderName = sc.nextLine();

        System.out.print(white + "‣ Enter expiration month (MM): " + reset);
        int expirationMonth = sc.nextInt();

        System.out.print(white + "‣ Enter expiration year (YYYY): " + reset);
        int expirationYear = sc.nextInt();

        System.out.print(white + "‣ Enter CVV: " + reset);
        int cvv = sc.nextInt();
    }
    public static String bookDomesticFlight() {
        System.out.println(purple + "Choose a place to which you want to book\n1. Delhi to Kolkata\n2. Delhi to Mumbai\n3. Delhi to Lucknow\n4. Delhi to Chennai\n5. Delhi to Goa\n6. Delhi to Guhawati\n7. Delhi to Vishakapattnam" + reset);
        int num=sc.nextInt();
        int num1=0;
        String str="Airline";
        switch(num){
            case 1: str="Delhi to Kolkata"; 
                num1=500;
                    break;
            case 2: str="Delhi to Mumbai"; 
                num1=700;
                    break;
            case 3: str="Delhi to Lucknow"; 
                num1=100;
                    break;
            case 4: str= "Delhi to Chennai";
                num1=1000;
                    break;
            case 5: str="Delhi to Goa"; 
                num1=1200;
                    break;
            case 6: str="Delhi to Guhawati"; 
                num1=900;
                    break;
            case 7: str="Delhi to Vishakapattnam"; 
                num1=1400;
                    break;
            default: System.out.println(red + "Enter a valid number" + reset);
                    bookDomesticFlight();
                    break;
        }
        System.out.println(green + "Domestic Flight Booked !\n" + reset);
        ticketPrice=ticketPrice+num1;
        return str;
    }
    public static String bookInternationalFlight() {
        System.out.println(purple + "Choose a place to which you want to book\n1. Delhi to New Zeleand\n2. Delhi to London\n3. Delhi to New York\n4. Delhi to Los Angeles\n5. Delhi to Italy\n6. Delhi to Japan\n7. Delhi to Bangkok" + reset);
        int num=sc.nextInt();
        int num1=0;
        String str="Airline";
        switch(num){
            case 1: str="Delhi to New Zeleand";
                num1=1500;
                    break;
            case 2: str="Delhi to London"; 
                num1=1700;
                    break;
            case 3: str="Delhi to New York"; 
                num1=1100;
                    break;
            case 4: str="Delhi to Los Angeles"; 
                num1=2000;
                    break;
            case 5: str="Delhi to Italy"; 
                num1=2200;
                    break;
            case 6: str="Delhi to Japan";
                num1=1900;
                    break;
            case 7: str="Delhi to Bangkok"; 
                num1=2400;
                    break;
            default: System.out.println(red + "Enter a valid number" + reset);
                    bookInternationalFlight();
                    break;
        }
        System.out.println(green + "International Flight Booked!" + reset);
        ticketPrice=ticketPrice+num1;
        return str;
    }
    public static String seatbook(){
        System.out.println(" # represents seat is already booked\n* represents seat is available for booking");
        int num=rand.nextInt(25)+5;
        int arr[]=new int[num];
        int arr1[]=new int[2*num];
        char seat[][]=new char[20][10];
        for(int i=0;i<20;i++){
            for(int j=0;j<10;j++){
                seat[i][j]='#';
            }
        }
        for(int i=0;i<num;i++){
            int num1=rand.nextInt(200);
            arr[i]=num1;
        }
        int count=0;
        for(int i=0;i<num;i++){
            arr1[count]=arr[i]/10;
            arr1[count+1]=arr[i]%10;
            count=count+2;
        }
        count=0;
        for(int i=0;i<num;i++){
            seat[arr1[count]][arr1[count+1]]='*';
            count=count+2;
        }
        System.out.print("     ");
        for(char i='A';i<'K';i++){
            if(i=='F'){
                System.out.print("\t\t");
            }
            System.out.print(i+"  ");
        }
        for(int i=0;i<20;i++){
            System.out.println();
            for(int j=0;j<10;j++){
                if(j==0){
                    if(i>8){
                        System.out.print((i+1)+"   ");
                    }
                    else{
                        System.out.print((i+1)+"    ");
                    }
                }
                else if(j==5){
                    int a=3;
                    if(i==a){
                        System.out.print("\tA\t");
                    }
                    else if(i==a*2){
                        System.out.print("\tI\t");
                    }
                    else if(i==a*3){
                        System.out.print("\tS\t");
                    }
                    else if(i==a*4){
                        System.out.print("\tL\t");
                    }
                    else if(i==a*5){
                        System.out.print("\tE\t");
                    }
                    else{
                        System.out.print("\t\t");
                    }
                }
                System.out.print(seat[i][j]+"  ");
            }
        }
        count=1; 
        int row=0,col=0;
            while(count==1){
            System.out.print("\nEnter the row of the seat you want to choose: ");
            row=row();
            System.out.print("Enter the column of the seat you want to choose: ");
            col=column();
            if(seat[row-1][col-1]=='#'){
                System.out.println("This seat is already booked");
                count=1;
                System.out.println("Choose another seat");
            }
            else{
                count=0;
                System.out.println("Your seat is booked");
            }
        }
        col=col+64;
        char ch=(char)col;
        String str=Integer.toString(row);
        str=str+"-"+ch;
        return str;
    }
    public static int row(){
        Scanner sc=new Scanner(System.in);
        int num=sc.nextInt();
        if(num<1||num>20){
            do{
                System.out.println("Enter correct value");
                num=sc.nextInt();
            }while(num<1||num>20);
        }
        return num;
    }
    public static int column(){
        Scanner sc=new Scanner(System.in);
        char ch=sc.next().charAt(0);
        int num=0;
        if(ch=='A'||ch=='a'){
            num=1;
        }else if(ch=='B'||ch=='b'){
            num=2;
        }else if(ch=='C'||ch=='c'){
            num=3;
        }else if(ch=='D'||ch=='d'){
            num=4;
        }else if(ch=='E'||ch=='e'){
            num=5;
        }else if(ch=='F'||ch=='f'){
            num=6;
        }else if(ch=='G'||ch=='g'){
            num=7;
        }else if(ch=='H'||ch=='h'){
            num=8;
        }else if(ch=='I'||ch=='i'){
            num=9;
        }else if(ch=='J'||ch=='j'){
            num=10;
        }else{
            System.out.print("Enter a correct value: ");
            column();
        }
        return num;
    }}
