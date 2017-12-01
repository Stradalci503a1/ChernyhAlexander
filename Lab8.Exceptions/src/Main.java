public class Main {
    public static void main(String[] args) {
        Locker locker = new Locker(5);
        locker.lock();
        try {
            locker.set(7);
        } catch (LockerLockedException e) {
            System.out.println(e.toString());
        }
        finally {
            System.out.println(locker.get());
        }
        locker.unlock();
        try {
            locker.set(1);
        } catch (LockerLockedException e) {
            System.out.println(e.toString());
        }
        finally {
            System.out.println(locker.get());
        }
    }
}
