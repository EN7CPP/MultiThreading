class JustAThread implements Runnable{
    public Thread t;
    private boolean suspendFlag;
    private String name;
    
    public JustAThread(String name){
        t=new Thread(this,name);
        suspendFlag=false;
        this.name=name;
    }
    synchronized public void suspendThread(){
        suspendFlag=true;
    }
    synchronized public void resumeThread(){
        suspendFlag=false;
        notify();
    }
    public String getName(){
        return this.name;
    }
    
    public void run (){
        try{
        for(int i=0;i<15;i++){
            System.out.println(Thread.currentThread().getName()+": "+i);
            Thread.sleep(500);
            synchronized(this){
                while(suspendFlag){
                    
                   wait();
                }
            }
        }
        }
        catch(InterruptedException e){
            System.out.println("Interrupted");
        }
        
        
        
    }
    
}


public class SuspendingAndResuming {

    public static void main(String args[]) {
            JustAThread jt=new JustAThread("Thread_1");
            JustAThread jt_1=new JustAThread("Thread_2");
            jt_1.t.start();
            jt.t.start();
            
            try{
                Thread.sleep(1000);
                jt.suspendThread();
                System.out.println("Suspending Thread: "+jt.getName());
                Thread.sleep(1000);
                jt.resumeThread();
                System.out.println("Resuming Thread: "+jt.getName());
                Thread.sleep(1000);
                jt_1.suspendThread();
                System.out.println("Suspending Thread: "+jt_1.getName());
                Thread.sleep(1000);
                jt_1.resumeThread();
                System.out.println("Resuming Thread: "+jt_1.getName());
                
                //Waiting for the threads to joim
                
                jt_1.t.join();
                jt.t.join();
                
            }
            catch(InterruptedException e){
                System.out.println("Interrupted");
            }
            
    }
    
    
    
}
