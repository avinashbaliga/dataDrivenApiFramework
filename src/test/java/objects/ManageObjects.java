package objects;

import middleware.RequestApis;

public class ManageObjects {

    private RequestApis requestApis = null;

    public RequestApis getRequestApis() {
        return (requestApis == null) ? requestApis = new RequestApis() : requestApis;
    }
}
