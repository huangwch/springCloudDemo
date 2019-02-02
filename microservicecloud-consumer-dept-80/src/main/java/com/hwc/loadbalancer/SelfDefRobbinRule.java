package com.hwc.loadbalancer;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class SelfDefRobbinRule extends AbstractLoadBalancerRule {

    private static final int COUNT = 5; // 每台服务器调用的次数
    private static final AtomicReference<IntPair> INT_PAIRS = new AtomicReference<IntPair>(new IntPair(0, 0));
    private static Logger log = LoggerFactory.getLogger(RoundRobinRule.class);

    public SelfDefRobbinRule() {
    }

    public SelfDefRobbinRule(ILoadBalancer lb) {
        this();
        setLoadBalancer(lb);
    }

    private static class IntPair {
        final int cycle;
        final int nextServerCyclicCounter;
        public IntPair(int cycle, int nextServerCyclicCounter) {
            this.cycle = cycle;
            this.nextServerCyclicCounter = nextServerCyclicCounter;
        }
    }

    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            log.warn("no load balancer");
            return null;
        }
        Server server = null;
        int count = 0;
        while (server == null && count++ < 10) {
            List<Server> reachableServers = lb.getReachableServers();
            List<Server> allServers = lb.getAllServers();
            int upCount = reachableServers.size();
            int serverCount = allServers.size();
            if ((upCount == 0) || (serverCount == 0)) {
                log.warn("No up servers available from load balancer: " + lb);
                return null;
            }
            int nextServerIndex = getNextServerIndex(serverCount);
            server = allServers.get(nextServerIndex);

            if (server == null) {
                /* Transient. */
                Thread.yield();
                continue;
            }
            if (server.isAlive() && (server.isReadyToServe())) {
                return (server);
            }
            // Next.
            server = null;
        }

        if (count >= 10) {
            log.warn("No available alive servers after 10 tries from load balancer: "
                    + lb);
        }
        return server;
    }

    private int getNextServerIndex(int serverCount) {
        while (true) {
            IntPair currentVals = INT_PAIRS.get();
            int currnetCycle = currentVals.cycle, currentServerCyclicCounter = currentVals.nextServerCyclicCounter;
            int nextCycle, nextServerCyclicCounter;
            if (currnetCycle < COUNT) {
                nextCycle = currnetCycle + 1;
                nextServerCyclicCounter = currentServerCyclicCounter;
            } else {
                nextCycle = 1;
                nextServerCyclicCounter = (currentServerCyclicCounter + 1) % serverCount;
            }
            if (INT_PAIRS.compareAndSet(currentVals, new IntPair(nextCycle, nextServerCyclicCounter))) {
                return nextServerCyclicCounter;
            }
        }
    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
    }
}
