public class Person {
    protected static int person_count=0;
    protected int person_id=0;
    protected double queue_one_time;
    protected double queue_two_time;
    protected double arrival;

    Person(double arrival){
        this.arrival = arrival;
        person_count++;
        person_id = person_count;
    }

    public double getArrival() {
        return arrival;
    }

    public void setQueue_one_time(double queue_one_time) {
        this.queue_one_time = queue_one_time;
    }

    public void setQueue_two_time(double queue_two_time) {
        this.queue_two_time = queue_two_time;
    }

    public int getPerson_id() {
        return person_id;
    }

    public double getQueue_one_time() {
        return this.queue_one_time;
    }

    public double getQueue_two_time() {
        return this.queue_two_time;
    }
}
