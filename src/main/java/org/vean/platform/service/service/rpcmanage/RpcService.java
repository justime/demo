package org.vean.platform.service.service.rpcmanage;

import org.springframework.stereotype.Service;
import org.vean.platform.client.service.IRpcService;

@Service("rpcService")
public class RpcService implements IRpcService{

    @Override
    public String service() {
        return "this is one test about security httpinvoker";
    }
}
