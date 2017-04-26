package edu.hm.REST;

import edu.hm.Logic.MediumAdministartion;

public class RestInter {

    private MediaAdminAccess mAdm;

    public RestInter(MediumAdministartion mAdm){
        this.mAdm = mAdm;
    }

    public MediaAdminAccess getmAdm() {
        return mAdm;
    }
}