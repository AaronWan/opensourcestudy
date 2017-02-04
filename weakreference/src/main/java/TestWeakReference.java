import org.junit.Test;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * Created by Aaron on 03/02/2017.
 */
public class TestWeakReference {
    public static void main(String[] args) throws Exception{
        ReferenceQueue<Car> referenceQueue=new ReferenceQueue<>();
        WeakReference<Car> weakCar=new WeakReference<>(new Car(2000,"red"),referenceQueue);
        waitGC(weakCar);
        System.out.println(referenceQueue.poll());
    }
    private static void waitGC(Reference<Car> weakCar)throws Exception {
        int i=0;
        while (true){
            if(weakCar.get()!=null){
                String temp="temp"+i;
                if(i++%10000==0){
                    Thread.sleep(1000);
                    System.out.print(".");
                }
                if(i%100000==0){
                    System.gc();
                }
            }else {
                System.out.println("is null");
                break;
            }
        }
    }
    @Test
    public void testSoftReference() throws Exception{
        ReferenceQueue<Car> referenceQueue=new ReferenceQueue<>();
        for(int i=0;i<100000000;i++){
            Car car=new Car(2000,"red");
            SoftReference<Car> weakCar=new SoftReference<Car>(car,referenceQueue);
            waitGC(weakCar);
        }
        System.out.println(referenceQueue.poll());
    }



}
