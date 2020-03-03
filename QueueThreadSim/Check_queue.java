import java.util.LinkedList;
import java.util.Queue;

public class Check_queue implements Runnable {
    private int current_size = 0;
    private static int size=0;
    private  static int total_size=0;
    private static long  time_start;
    private String checkpoint_name;
    private Queue<Person> current_queue = new LinkedList<>();

    Check_queue(int size_t,long time){
        total_size = size_t;
        time_start = time;
    }

    @Override
    public void run() {
        loadQueueRandomly();
    }


    private synchronized void loadQueueRandomly(){
        final String checkpoint_id = Thread.currentThread().getName();

        if(checkpoint_id.equals("Thread-4")){
            checkpoint_name = "A";
        }
        else if(checkpoint_id.equals("Thread-3")) {
            checkpoint_name = "B";
        }
        try {

            while (++size<=total_size) {
                int sec = 5; //people show up 1-5 seconds from each other
                long wait = (long) ((Math.random() * ((sec) + 1)) * 1000);
                wait(wait);

                double time = System.currentTimeMillis()-time_start;
                Person front_dude = new Person(time);
                System.out.println("Person " + front_dude.getPerson_id() + " arrived at CheckPoint: "+ checkpoint_name + " at " + time/1000 + " seconds.");
                current_queue.add(front_dude);
                wait(1000);
                notify();

            }
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private double randomTime(){
        return Math.random();
    }


    public Queue<Person> getCurrent_queue() {
        return current_queue;
    }

    public int getCurrent_size() {
        return current_size;
    }

    public void setCurrent_size(int current_size) {
        this.current_size = current_size;
    }
}
