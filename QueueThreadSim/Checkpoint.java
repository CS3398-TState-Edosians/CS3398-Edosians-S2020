import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Checkpoint implements Runnable {
    private static int processed=0;
    private static int size_total = 0;
    private static boolean done = false;
    private static long  time_startt;
    private String checkpoint_name;
    private static Queue<Person> final_queue = new LinkedList<>();


    private Check_queue c_queue;
    Checkpoint(int size_t,long time){
        c_queue = new Check_queue(size_t,time);
        size_total = size_t;
        time_startt = time;
    }

    @Override
    public void run() {
        final String checkpoint_id = Thread.currentThread().getName();

        if(checkpoint_id.equals("Thread-1")){
            checkpoint_name = "A";
        }
        else if(checkpoint_id.equals("Thread-0")){
            checkpoint_name = "B";
        }
        Thread a = new Thread(c_queue);
        //System.out.println(a.getName()+Thread.currentThread().getName());
        a.start();
        processes_queue();
    }

    private synchronized void processes_queue(){
        final int sec = 9; // 1-9 second wait time at Checkpoints

        while (!getDone()) {
            Person a = c_queue.getCurrent_queue().poll();
            if(a!=null) {
                addToProcessed();
                //System.out.println(a.getArrival()/1000);
                long milliseconds = (long) ((Math.random() * ((sec) + 1)) * 1000);
                a.setQueue_one_time(milliseconds);
                try {
                    wait(milliseconds);
                    double time = System.currentTimeMillis()-time_startt;
                    double queue_one = time - a.getArrival();
                    a.setQueue_one_time(queue_one);
                    System.out.println("Person "+ a.getPerson_id() + " waited " + queue_one/1000 + " seconds " +" at Checkpoint: "+ checkpoint_name);
                    final_queue.add(a);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(getProcessed()==getSize_total()){
                setDone();
            }
        }
    }

    private synchronized boolean getDone(){
        return done;
    }

    private synchronized void setDone(){
        done=true;
    }

    private synchronized int getSize_total(){
        return size_total;
    }

    private synchronized int getProcessed(){
        return processed;
    }

    private synchronized void addToProcessed(){
        processed++;
    }

    public synchronized Queue<Person> getFinal_queue(){
        return final_queue;
    }
}
