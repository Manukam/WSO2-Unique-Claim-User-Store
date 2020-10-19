package org.wso2.sample.unique.claim.user.store.manager.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.wso2.carbon.user.api.UserStoreManager;
import org.wso2.carbon.user.core.service.RealmService;
import org.wso2.sample.unique.claim.user.store.manager.UniqueClaimUserStore;

public class UniqueClaimUserStoreDsComponent {

    private static Log log = LogFactory.getLog(UniqueClaimUserStoreDsComponent.class);
    private static RealmService realmService;

    protected void activate(ComponentContext ctxt) {
        try {
            UniqueClaimUserStore customUserStoreManager = new UniqueClaimUserStore();
            ctxt.getBundleContext().registerService(UserStoreManager.class.getName(), customUserStoreManager, null);
            log.info("CustomUserStoreManager bundle activated successfully..");
        } catch (Throwable storeError) {
            log.error("ERROR when activating Custom User Store", storeError);
        }
    }

    protected void deactivate(ComponentContext ctxt) {
        if (log.isDebugEnabled()) {
            log.debug("Custom User Store Manager is deactivated ");
        }
    }


    protected void setRealmService(RealmService relmService) {
        realmService = relmService;
    }

    protected void unsetRealmService(RealmService realmService) {
        realmService = null;
    }

    public static RealmService getRealmService() {
        if (log.isDebugEnabled()) {
            log.debug("CustomJDBCUserStore - get the Realm Service");
        }
        return realmService;
    }
}
