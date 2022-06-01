import java.util.LinkedList;

public class Level4 {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println("First: " + list.getFirst());
        System.out.println("Last: " + list.getLast());
        System.out.println(summation(2));

        int month = 1;
        System.out.println(month + ", " + monthString(month));
        month = 6;
        System.out.println(month + ", " + monthString(month));

    }
    public static int summation(int i){
        if(i == 0){
            return 0;
        }
        else{
            return i + summation(i-1);
        }

    }
    public static String monthString(int i){
        String month = "";
        switch (i) {
            case 1:  month = "January";
                break;
            case 2:  month = "February";
                break;
            case 3:  month = "March";
                break;
            case 4:  month = "April";
                break;
            case 5:  month = "May";
                break;
            case 6:  month = "June";
                break;
            case 7:  month = "July";
                break;
            case 8:  month = "August";
                break;
            case 9:  month = "September";
                break;
            case 10: month = "October";
                break;
            case 11: month = "November";
                break;
            case 12: month = "December";
                break;
            default: month = "Invalid month";
                break;
        }
        return month;
    }
}
