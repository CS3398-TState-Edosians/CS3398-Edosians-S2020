import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        final long start_time = System.currentTimeMillis();
        final int total_size = 10;
        Checkpoint A = new Checkpoint(total_size,start_time);
        Checkpoint B = new Checkpoint(total_size,start_time);

        Checkpoint C = new Checkpoint(total_size,start_time){
            @Override
            public void run() {
                final int sec = 9;
                int ct = 0;
                Queue<Person> flight = new LinkedList<>();
                while (ct<total_size) {
                    Person a = getFinal_queue().poll();
                    if(a!=null) {
                        ct++;
                        double time_arrived = System.currentTimeMillis()-start_time;
                        System.out.println("Person " + a.getPerson_id() + " arrived at CheckPoint C: " + " at " + time_arrived/1000 + " seconds.");
                        long milliseconds = (long) ((Math.random() * ((sec) + 1)) * 1000);
                        a.setQueue_two_time(milliseconds);
                        try {
                            Thread.sleep(milliseconds);

                            double time = System.currentTimeMillis()-start_time;
                            double queue_two_time = time - time_arrived;
                            a.setQueue_two_time(queue_two_time);
                            flight.add(a);
                            System.out.println("Person "+ a.getPerson_id() + " waited " + queue_two_time/1000 + " seconds " +" at Checkpoint: "+ " C.");

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                double avg = 0;
                while(!flight.isEmpty()){
                    Person a = flight.poll();
                    avg = avg+a.getQueue_one_time()+a.getQueue_two_time();
                }
                avg = avg/(total_size*1000);
                System.out.println("All Passengers Checked! Average Time In queue:"+avg);
            }
        };

        Thread a = new Thread(A);
        Thread b = new Thread(B);
        Thread c = new Thread(C);

        a.start();
        b.start();
        c.start();
    }
}
