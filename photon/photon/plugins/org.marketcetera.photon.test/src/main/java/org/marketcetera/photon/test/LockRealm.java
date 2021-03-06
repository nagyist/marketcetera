package org.marketcetera.photon.test;

import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.core.databinding.observable.Realm;

/* $License$ */

/**
 * A realm that obtains a lock before executing.
 * 
 * @author <a href="mailto:will@marketcetera.com">Will Horn</a>
 * @version $Id: LockRealm.java 16154 2012-07-14 16:34:05Z colin $
 * @since 2.0.0
 */
public class LockRealm extends Realm {

    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public boolean isCurrent() {
        return lock.isHeldByCurrentThread();
    }

    protected void syncExec(Runnable runnable) {
        lock();
        try {
            runnable.run();
        } finally {
            unlock();
        }
    }

    public void lock() {
        lock.lock();
    };

    public void unlock() {
        lock.unlock();
    }

}
