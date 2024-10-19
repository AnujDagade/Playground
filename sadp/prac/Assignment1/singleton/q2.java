public class q2 {
    
   
    private q2() {
  
    }


    private static class SingletonHelper {
        
        private static final q2 INSTANCE = new q2();
    }

    
    public static q2 getInstance() {
        return SingletonHelper.INSTANCE;
    }

    
    public void showMessage() {
        System.out.println("Hello from Singleton!");
    }
    

    public static void main(String[] args) {

        Runnable task = () -> {
            q2 singleton = q2.getInstance();
            singleton.showMessage();
        };

    
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        Thread thread3 = new Thread(task);

        thread1.start();
        thread2.start();
        thread3.start();


        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

