package javaapplication10;
import java.text.SimpleDateFormat;  
import java.text.ParseException;   
import java.util.Calendar;
import java.util.Date;   
import java.util.Locale;

/**
 *
 * @author dell
 */
public class JavaApplication10 {

    static long get_num_of_days(String  first_date, String second_date)   
    {  
         SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");  
         long days_difference=0;
         try { 
         Date start = sdf.parse(first_date);   
         Date end = sdf.parse(second_date);
         
         long time_difference = end.getTime() - start.getTime();                 //get difference in time between start and end dates in msec
         days_difference = (time_difference / (1000*60*60*24)) % 365;   
         }
          catch (ParseException excep) {   
            excep.printStackTrace();   
        }    
       return days_difference+1;                         // add 1 since both end and start dates are included 
    }   
    
    static int get_number_of_weeks(long num_of_days){
     int num_of_weeks=(int) (num_of_days/7);
     return num_of_weeks;
    }
  
    
    static String get_date(String start,int num_of_days_to_increase){
     String new_date="";
     SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
     SimpleDateFormat day_month = new SimpleDateFormat("dd MMMM", Locale.ENGLISH);
     Calendar c = Calendar.getInstance();
     try{
     c.setTime(sdf.parse(start));
     c.add(Calendar.DATE,num_of_days_to_increase);                  // number of days to add
     new_date = day_month.format(c.getTime());                         
     }  catch (ParseException excep) {   
            excep.printStackTrace();   
     }
     return new_date;
    }
    
    
   static void print_plan(int num_of_weeks,String start_date){
     int weeks_passed=0;                                               // keeps track of weeks passed
     int week_in_block_index=0;                                        //the index in the block of weeks containing recovery/build1/build2/key
     String start=start_date;
   
    
     
     if(num_of_weeks<8)
        System.out.printf("Error! Plan cannot contain less than 8 weeks.");
     else {
         
        weeks_passed++;System.out.printf("Week #%d - Test - from %S to %S\n",weeks_passed,get_date(start,(weeks_passed-1)*7),get_date(start,weeks_passed*7-1));
        weeks_passed++;System.out.printf("Week #%d -Test - from %S to %S\n",weeks_passed,get_date(start,(weeks_passed-1)*7),get_date(start,weeks_passed*7-1));

        int type_of_plan =  num_of_weeks % 4;                              //type of plan depending on number of weeks (8/9/10/11)
        
        if (type_of_plan==1)           //min number of weeks 9
        {
            weeks_passed++;System.out.printf("Week #%d - Filler - from %S to %S \n",weeks_passed,get_date(start,(weeks_passed-1)*7),get_date(start,weeks_passed*7-1));
             
        }
        else{
            week_in_block_index = 4 - type_of_plan;                             //indicates which week we are starting at in block (recovery/build1/build2/key)
        }
        
        while(weeks_passed < num_of_weeks-2)
        {
            weeks_passed++;
            if (week_in_block_index%4 == 0)
               System.out.printf("Week #%d -Recovery - from %S to %S \n",weeks_passed,get_date(start,(weeks_passed-1)*7),get_date(start,weeks_passed*7-1));
            else if (week_in_block_index%4 == 1)
                 System.out.printf("Week #%d -Build 1 -from %S to %S \n",weeks_passed,get_date(start,(weeks_passed-1)*7),get_date(start,weeks_passed*7-1));
            else if (week_in_block_index%4 == 2)
                 System.out.printf("Week #%d -Build 2- from %S to %S \n",weeks_passed,get_date(start,(weeks_passed-1)*7),get_date(start,weeks_passed*7-1));
            else if (week_in_block_index%4 == 3)
                 System.out.printf("Week #%d - Key - from %S to %S \n",weeks_passed,get_date(start,(weeks_passed-1)*7),get_date(start,weeks_passed*7-1));
            week_in_block_index++;
        }

        weeks_passed++;System.out.printf("Week #%d - Taper - from %S to %S \n",weeks_passed,get_date(start,(weeks_passed-1)*7),get_date(start,weeks_passed*7-1));
        weeks_passed++;System.out.printf("Week #%d - Race - from %S to %S \n",weeks_passed,get_date(start,(weeks_passed-1)*7),get_date(start,weeks_passed*7-1));
                }
}
    
    public static void main(String[] args) {
         String start_date= "6-6-2021" ;                 //enter start date
         String end_date =  "14-8-2021";                 //enter end date
         long num_of_days= get_num_of_days(start_date,end_date);        
         int num_of_weeks=get_number_of_weeks(num_of_days);
         print_plan(num_of_weeks,start_date);
        
    }
    
}
