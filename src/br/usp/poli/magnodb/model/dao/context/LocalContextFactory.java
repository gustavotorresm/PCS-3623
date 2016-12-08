package br.usp.poli.magnodb.model.dao.context;

import javax.naming.spi.NamingManager;

/**
 * Created by gustavo on 28/11/16.
 */
public final class LocalContextFactory {
    public static LocalContext createLocalContext(String driver) {
        try {
            LocalContext lc = new LocalContext();
            Class.forName(driver);
            NamingManager.setInitialContextFactoryBuilder(lc);
            return lc;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
