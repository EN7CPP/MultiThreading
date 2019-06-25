class Producer implements Runnable{
    private Q q;
    Thread t;
    public Producer(Q que){
        this.q=que;
        this.t=new Thread(this,"Thread_2");
        
    }
    public void run(){
        int l=0;
        while(true){
            q.put(l++);
        }
        
        
    }
    
}
class Consumer implements Runnable{
    private Q q;
    Thread t;
    public Consumer(Q que){
        this.q=que;
        this.t=new Thread(this,"Thread_3");
    }
    public void run(){
        while(true){
            q.get();
        }
    }
    
}
class Q{
 private int k;
 public boolean valueSet=false;
 synchronized public void put(int a){
     while(valueSet){
         try{
             wait();
         }
         catch(InterruptedException e){
             System.out.println("Interrupted");
         }
         
         }
     this.k=a;
     valueSet=true;
     System.out.println("Put : "+k);
     notify();
 }   
 synchronized public int get(){
     while(!valueSet){
         try{
         wait();
         }
         catch(InterruptedException e){
             System.out.println("Interrupted");
         }
     }
     
     System.out.println("Got : " + k);
     valueSet=false;
     notify();
     return k;
 }        
}
public class ProducerConsumer {
    public static void main(String args[]) {
        Q q =new Q();
        Producer p=new Producer(q);
        Consumer c=new Consumer(q);
        p.t.start();
        c.t.start();
        
        
    }
}
