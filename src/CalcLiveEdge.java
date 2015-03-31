import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class CalcLiveEdge
{

    public static void main(String[] args)
    {
        System.out.println("Calculate Live Edge");
        System.out.println("-------------------");
        
        if (args.length < 4)
        {
            System.out.println("Usage: CalcLiveEdge availabilityStartTime duration timescale startNumber");
            System.out.println("");
            System.out.println("Example: CalcLiveEdge 2015-03-30T09:30:10Z 60060000 10000000 1427139101");
            
            return;
        }

        DateFormat  dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        
        Date availabilityStartTime;
        try
        {
            availabilityStartTime = dateFormat.parse(args[0]);
        } catch (ParseException e)
        {
            System.out.println("Your availabilityStartTime ist not in the following format: YYYY-MM-DDTHH:mm:ssZ");
            return;
        }
        
        Date    now                     = Calendar.getInstance().getTime();

        long    secondsDifference   = (now.getTime() - availabilityStartTime.getTime()) / 1000;
        long    segmentDuration     = Long.parseLong(args[1]);
        long    segmentTimescalce   = Long.parseLong(args[2]);
        double  segmentDurationSec  = (double)segmentDuration / (double) segmentTimescalce;
        long    startNumber         = Long.parseLong(args[3]);
        long    liveEdge            = (long) (startNumber +  secondsDifference / segmentDurationSec);
        
        System.out.println("AvailabilityStartTime: " + availabilityStartTime);
        System.out.println("Now: " + now);
        System.out.println("Segment duration in seconds: " + segmentDurationSec);
        System.out.println("Seconds difference: " + secondsDifference);
        System.out.println("Live Edge Segment Number: " + liveEdge);
    }

}
