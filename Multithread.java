
class JustAClass {
    synchronized  public void what(){
        for(int i=0;i<5;i++){
        System.out.println(Thread.currentThread()+":     "+ (i+1));
        }
    }
}
class AnotherThread implements Runnable{
    Thread t;
    JustAClass hm;
    AnotherThread(String name,JustAClass jc ){
        t=new Thread(this,name);
        hm=jc;
    }
    
    
    public void print(JustAClass A){
       A.what();
    }
    
    public void run(){
        print(this.hm);
        
    }
    
}

public class Multithread {
    public static void main(String args[]) {
        JustAClass js=new JustAClass();
        AnotherThread at=new AnotherThread("Second",js);
        AnotherThread at_3=new AnotherThread("Third",js);
 
        at.t.start();
        at_3.t.start();
        Thread main=Thread.currentThread();
        try{
        at.t.join();
        at_3.t.join();
        for(int i=0;i<5;i++){
            System.out.println(main+":     "+ (i+1));
        }
        }
        catch(InterruptedException e)
        {
            System.out.println("Interrupted main");
        }
    }
}
